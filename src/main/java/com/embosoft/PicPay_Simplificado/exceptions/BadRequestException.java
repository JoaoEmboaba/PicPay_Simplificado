package com.embosoft.PicPay_Simplificado.exceptions;

public class BadRequestException extends RuntimeException {

    private static final Long serialVersionUID = 1L;

    public BadRequestException(String msg) {
        super(msg);
    }
}
