package com.p6.paymybuddy.controller.dto.TransactionInternal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionInternalRequest {
    private String description;
    private Double amount;
    private String timeTransaction;
    private Long crediteur;
    private Long debiteur;
}