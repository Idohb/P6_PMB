package com.p6.paymybuddy.Mapper;

import com.p6.paymybuddy.Model.Entity.CommissionEntity;
import com.p6.paymybuddy.Service.Data.Commission;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommissionConverter {
    public Commission mapperCommission(CommissionEntity commissionEntity) {
        Commission commission = new Commission();
        commission.setId_commission(commissionEntity.getId_commission());
        commission.setTransaction_id(commissionEntity.getTransaction_id());
        commission.setAmount(commissionEntity.getAmount());

        return commission;
    }

    public List<Commission> mapperCommission(List<CommissionEntity> commissionEntities) {
        return commissionEntities.stream().map(this::mapperCommission).collect(Collectors.toList());
    }
}
