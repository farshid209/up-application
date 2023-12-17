package org.example.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class PayDto {
    private Long accountId;
    private BigDecimal amount;
}
