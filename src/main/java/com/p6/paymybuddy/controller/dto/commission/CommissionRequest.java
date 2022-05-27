package com.p6.paymybuddy.controller.dto.commission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommissionRequest {
    private Long transactionId;
    private Double amount;
}
