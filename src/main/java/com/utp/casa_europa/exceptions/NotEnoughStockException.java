package com.utp.casa_europa.exceptions;

public class NotEnoughStockException extends RuntimeException {
    public NotEnoughStockException(String producto) {
        super("El producto: "+ producto + " no cuenta con el stock suficiente");
    }
}
