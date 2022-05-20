package com.p6.paymybuddy.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.p6.paymybuddy.Model.Entity.CommissionEntity;
import com.p6.paymybuddy.Service.Data.Commission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommissionConverter {

    @Autowired
    private ObjectMapper objectMapper;
    public Commission mapperCommission(CommissionEntity commissionEntity) {
        commissionEntity.getTransaction().getCrediteur().setFriends(null);
        commissionEntity.getTransaction().getDebiteur().setFriends(null);
        return objectMapper.convertValue(commissionEntity, Commission.class);
    }

    public List<Commission> mapperCommission(List<CommissionEntity> commissionEntities) {
        return commissionEntities.stream().map(this::mapperCommission).collect(Collectors.toList());
    }
}
