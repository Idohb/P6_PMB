package com.p6.paymybuddy.mapper;

import com.p6.paymybuddy.model.entity.TransactionExternalEntity;
import com.p6.paymybuddy.service.data.TransactionExternal;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class TransactionExternalConverter {
    
        public TransactionExternal mapperTransactionExternal(TransactionExternalEntity transactionExternalEntity) {
            PersonConverter personConverter = new PersonConverter();

            TransactionExternal transactionExternal = new TransactionExternal();
            transactionExternal.setId(transactionExternalEntity.getId());
            transactionExternal.setDescription(transactionExternalEntity.getDescription());
            transactionExternal.setAmount(transactionExternalEntity.getAmount());
            transactionExternal.setTimeTransaction(transactionExternalEntity.getTimeTransaction());

            if (transactionExternalEntity.getUser() != null) {
                transactionExternal.setUser(personConverter.mapperPerson(transactionExternalEntity.getUser()));
            }

            return transactionExternal;
        }

        public List<TransactionExternal> mapperTransactionExternal(List<TransactionExternalEntity> transactionExternalEntities) {
            return transactionExternalEntities.stream().map(this::mapperTransactionExternal).collect(Collectors.toList());
        }
}
