package com.p6.paymybuddy.Controller.Dto.TransactionInternal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionInternalResponse {
    private String description;
    private String amount;
    private String timeTransaction;
    private Long crediteur;
    private Long debiteur;
}
