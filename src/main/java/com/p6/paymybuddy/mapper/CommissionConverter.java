package com.p6.paymybuddy.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.p6.paymybuddy.model.entity.CommissionEntity;
import com.p6.paymybuddy.service.data.Commission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommissionConverter {
    @Autowired
    TransactionInternalConverter transactionInternalConverter;

    public Commission mapperCommission(CommissionEntity commissionEntity) {
        Commission p = new Commission();
        p.setId(commissionEntity.getId());
        p.setAmount(commissionEntity.getAmount());
        p.setTransaction(transactionInternalConverter.mapperTransactionInternal(commissionEntity.getTransaction()));
        return p;
    }

    public List<Commission> mapperCommission(List<CommissionEntity> commissionEntities) {
        return commissionEntities.stream().map(this::mapperCommission).collect(Collectors.toList());
    }
}
