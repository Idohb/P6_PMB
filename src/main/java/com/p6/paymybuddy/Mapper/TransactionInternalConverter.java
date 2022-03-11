package com.p6.paymybuddy.Mapper;


import com.p6.paymybuddy.Model.Entity.LoginEntity;
import com.p6.paymybuddy.Model.Entity.TransactionInternalEntity;
import com.p6.paymybuddy.Service.Data.TransactionInternal;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionInternalConverter {
    public TransactionInternal mapperTransactionInternal(TransactionInternalEntity transactionInternalEntity) {
        PersonConverter personConverter = new PersonConverter();

        TransactionInternal transactionInternal = new TransactionInternal();
        transactionInternal.setIdTransactionInternal(transactionInternalEntity.getIdTransactionInternal());
        transactionInternal.setDescription(transactionInternalEntity.getDescription());
        transactionInternal.setAmount(transactionInternalEntity.getAmount());
        transactionInternal.setTimeTransaction(transactionInternalEntity.getTimeTransactionInternal());

        if (transactionInternalEntity.getCrediteur() != null) {
            transactionInternal.setCrediteur(personConverter.mapperPerson(transactionInternalEntity.getCrediteur()));
        }

        if (transactionInternalEntity.getDebiteur() != null) {
            transactionInternal.setDebiteur(personConverter.mapperPerson(transactionInternalEntity.getDebiteur()));
        }

        return transactionInternal;
    }

    public List<TransactionInternal> mapperTransactionInternal(List<TransactionInternalEntity> transactionInternalEntities) {
        return transactionInternalEntities.stream().map(this::mapperTransactionInternal).collect(Collectors.toList());
    }
}

//package com.p6.paymybuddy.Mapper;
//
//public class TransactionInternalConverter {
//}
