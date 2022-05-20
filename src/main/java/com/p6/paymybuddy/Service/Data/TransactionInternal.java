package com.p6.paymybuddy.Service.Data;

import lombok.Data;

@Data
public class TransactionInternal {
    private Long id;
    private String description;
    private Double amount;
    private String timeTransaction;
    private Person crediteur;
    private Person debiteur;

    public TransactionInternal() {
    }

    
}
