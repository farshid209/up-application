package org.example.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferDto {
    private Long fromAccountId;
    private Long toAccountId;
    private BigDecimal amount;
}
