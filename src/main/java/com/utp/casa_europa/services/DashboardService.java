package com.utp.casa_europa.services;

import java.util.ArrayList;
import com.utp.casa_europa.dtos.DashboardChartDto;
import com.utp.casa_europa.dtos.DashboardKpisDto;
import com.utp.casa_europa.repositories.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    @Autowired
    private DashboardRepository dashboardRepository;

    public DashboardKpisDto getKpis() {
        DashboardKpisDto dto = new DashboardKpisDto();
        dto.setProducto(dashboardRepository.findProductoMasVendido());
        dto.setClientes(dashboardRepository.findTotalClientes());
        dto.setCategoria(dashboardRepository.findCategoriaMasVendida());
        return dto;
    }

    public DashboardChartDto getChart() {
        List<Object[]> ventasPorDia = dashboardRepository.findVentasPorDia();
        List<String> labels = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
        for (Object[] row : ventasPorDia) {
            labels.add(row[0].toString());
            data.add(((Number) row[1]).intValue());
        }
        DashboardChartDto.ChartDataset dataset = new DashboardChartDto.ChartDataset();
        dataset.setLabel("Ventas");
        dataset.setData(data);
        dataset.setBackgroundColor("rgba(75, 192, 192, 0.6)");

        DashboardChartDto chartDto = new DashboardChartDto();
        chartDto.setLabels(labels);
        chartDto.setDatasets(List.of(dataset));
        return chartDto;
    }
}
