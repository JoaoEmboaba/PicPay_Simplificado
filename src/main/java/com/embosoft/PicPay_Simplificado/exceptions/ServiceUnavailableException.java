package com.embosoft.PicPay_Simplificado.exceptions;

public class ServiceUnavailableException extends RuntimeException {

    private static final Long serialVersionUID = 1L;

    public ServiceUnavailableException(String message) {
        super(message);
    }
}
