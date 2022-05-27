package com.p6.paymybuddy.mapper;


import com.p6.paymybuddy.model.entity.TransactionInternalEntity;
import com.p6.paymybuddy.service.data.TransactionInternal;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionInternalConverter {
    public TransactionInternal mapperTransactionInternal(TransactionInternalEntity transactionInternalEntity) {
        PersonConverter personConverter = new PersonConverter();

        TransactionInternal transactionInternal = new TransactionInternal();
        transactionInternal.setId(transactionInternalEntity.getId());
        transactionInternal.setDescription(transactionInternalEntity.getDescription());
        transactionInternal.setAmount(transactionInternalEntity.getAmount());
        transactionInternal.setTimeTransaction(transactionInternalEntity.getTimeTransaction());

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
