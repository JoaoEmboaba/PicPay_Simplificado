package com.embosoft.PicPay_Simplificado.DTO;

import com.embosoft.PicPay_Simplificado.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO (String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {
}
