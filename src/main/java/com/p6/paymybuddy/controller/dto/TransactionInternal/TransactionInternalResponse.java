package com.p6.paymybuddy.controller.dto.TransactionInternal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionInternalResponse {
    private Long idTransactionInternal;
    private String description;
    private String amount;
    private String timeTransaction;
    private String nomDebiteur;
    private String nomCrediteur; // ceci pour éviter le stackoverflow
}
