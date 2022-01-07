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
        TransactionInternal transactionInternal = new TransactionInternal();
        transactionInternal.setIdTransactionInternal(transactionInternalEntity.getIdTransactionInternal());
        transactionInternal.setDescription(transactionInternalEntity.getDescription());
        transactionInternal.setAmount(transactionInternalEntity.getAmount());
        transactionInternal.setTimeTransaction(transactionInternalEntity.getTimeTransactionInternal());
        transactionInternal.setCrediteur(transactionInternalEntity.getCrediteur());
        transactionInternal.setDebiteur(transactionInternalEntity.getDebiteur());
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
