package com.p6.paymybuddy.Service.Data;

import com.p6.paymybuddy.Mapper.PersonConverter;
import com.p6.paymybuddy.Service.Data.Person;
import com.p6.paymybuddy.Model.Entity.TransactionInternalEntity;
import lombok.Data;

import java.util.List;

@Data
public class TransactionInternal {
    private Long idTransactionInternal;
    private String description;
    private String amount;
    private String timeTransaction;
    private Person crediteur;
    private Person debiteur;

    public TransactionInternal() {
    }

    public TransactionInternal(TransactionInternalEntity p) {
        PersonConverter personConverter = new PersonConverter();

        setIdTransactionInternal(p.getIdTransactionInternal());
        setDescription(p.getDescription());
        setAmount(p.getAmount());
        setTimeTransaction(p.getTimeTransactionInternal());
        setCrediteur(personConverter.mapperPerson(p.getCrediteur()));
        setDebiteur(personConverter.mapperPerson(p.getDebiteur()));
    }
    
    
}
