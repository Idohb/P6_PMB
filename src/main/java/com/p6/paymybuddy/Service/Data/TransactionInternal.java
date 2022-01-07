package com.p6.paymybuddy.Service.Data;

import com.p6.paymybuddy.Model.Entity.TransactionInternalEntity;
import lombok.Data;

@Data

public class TransactionInternal {
    private Long idTransactionInternal;
    private String description;
    private String amount;
    private String timeTransaction;
    private Long crediteur;
    private Long debiteur;

    public TransactionInternal() {
    }

    public TransactionInternal(TransactionInternalEntity p) {
        setIdTransactionInternal(p.getIdTransactionInternal());
        setDescription(p.getDescription());
        setAmount(p.getAmount());
        setTimeTransaction(p.getTimeTransactionInternal());
        setCrediteur(p.getCrediteur());
        setDebiteur(p.getDebiteur());
    }
    
    
}
