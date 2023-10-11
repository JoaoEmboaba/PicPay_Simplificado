package com.embosoft.PicPay_Simplificado.exceptions;

public class UnauthorizedException extends RuntimeException {

    private static final Long serialVersionUID = 1L;

    public UnauthorizedException(String msg) {
        super(msg);
    }
}
