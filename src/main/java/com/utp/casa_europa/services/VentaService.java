package com.utp.casa_europa.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.utp.casa_europa.dtos.payment.CardPaymentDto;
import com.utp.casa_europa.exceptions.NotEnoughStockException;
import com.utp.casa_europa.models.*;
import com.utp.casa_europa.repositories.IPromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utp.casa_europa.dtos.DetalleVentaDto;
import com.utp.casa_europa.dtos.VentaPagoDto;
import com.utp.casa_europa.dtos.payment.ResponsePaymentDto;
import com.utp.casa_europa.exceptions.EntityNotFoundException;
import com.utp.casa_europa.exceptions.PaymentNotApprovedException;
import com.utp.casa_europa.mappers.DetalleVentaMapper;
import com.utp.casa_europa.repositories.IUsuarioRepository;
import com.utp.casa_europa.repositories.IVentaRepository;
import com.utp.casa_europa.repositories.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class VentaService {

    @Autowired
    private IVentaRepository ventaRepository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private IPromocionRepository promocionRepository;


    @Transactional
    public ResponsePaymentDto saveVenta(VentaPagoDto request, Long userId) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        BigDecimal total = BigDecimal.ZERO;
        List<DetalleVenta> listDetalle = new ArrayList<>();

        // Guardar promoción global si hay cupón
        Promocion promocionGlobal = null;
        if (request.getVenta().getCuponDescuento() != null && !request.getVenta().getCuponDescuento().isEmpty()) {
            promocionGlobal = promocionRepository.findPromocionByCupon(request.getVenta().getCuponDescuento());
        }

        for (DetalleVentaDto dto : request.getVenta().getDetalleVenta()) {
            Producto producto = productoRepository.findById(dto.getIdProducto())
                    .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID " + dto.getIdProducto()));

            if (producto.getStock() < dto.getCantidad()) {
                throw new NotEnoughStockException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            BigDecimal precioOriginal = producto.getPrecio();
            BigDecimal precioConDescuento = precioOriginal;

            // Aplicar descuento por producto si existe promoción específica
            Promocion promocion = promocionRepository.findPromocionByProductId(producto.getId());
            if (promocion != null) {
                BigDecimal descuento = precioOriginal.multiply(promocion.getValor()
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP));
                precioConDescuento = precioOriginal.subtract(descuento);
            }

            DetalleVenta detalle = DetalleVentaMapper.dtoToEntity(dto);
            detalle.setPrecioUnitario(precioOriginal);
            detalle.setPrecioConDescuento(precioConDescuento);
            total = total.add(precioConDescuento.multiply(BigDecimal.valueOf(dto.getCantidad())));
            listDetalle.add(detalle);
        }

        // Aplicar descuento global si existe cupón válido
        BigDecimal totalConDescuento = total;
        if (promocionGlobal != null) {
            BigDecimal descuentoGlobal = total.multiply(promocionGlobal.getValor()
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP));
            totalConDescuento = total.subtract(descuentoGlobal);
        }

        Venta venta = Venta.builder()
                .usuario(usuario)
                .fechaHora(LocalDateTime.now())
                .detalleVentas(listDetalle)
                .promocion(promocionGlobal)
                .montoDescuento(total.subtract(totalConDescuento))
                .total(total)
                .totalConDescuento(totalConDescuento)
                .build();

        for (DetalleVenta detalle : listDetalle) {
            detalle.setVenta(venta);
        }

        ventaRepository.save(venta);

        CardPaymentDto cardPDto = request.getPago();
        cardPDto.setTransactionAmount(totalConDescuento);

        ResponsePaymentDto paymentResponse = paymentService.proccessPayment(cardPDto);

        if (!"approved".equals(paymentResponse.getStatus())) {
            throw new PaymentNotApprovedException();
        }

        return paymentResponse;
    }


}
