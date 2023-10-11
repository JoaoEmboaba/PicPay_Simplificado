package com.embosoft.PicPay_Simplificado.DTO;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionDTO(BigDecimal value, UUID senderId, UUID receiverId) {
}
