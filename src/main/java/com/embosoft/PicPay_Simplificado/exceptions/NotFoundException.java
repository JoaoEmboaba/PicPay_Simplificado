package com.embosoft.PicPay_Simplificado.exceptions;

public class NotFoundException extends RuntimeException {

    private static final Long serialVersionUID = 1L;

    public NotFoundException(String msg) {
        super(msg);
    }
}
