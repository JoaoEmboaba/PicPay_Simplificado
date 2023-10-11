package com.embosoft.PicPay_Simplificado.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StandardError implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer status;
    private String error;
    private String message;
    private String path;

}
