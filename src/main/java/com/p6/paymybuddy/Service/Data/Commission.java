package com.p6.paymybuddy.Service.Data;

import com.p6.paymybuddy.Model.Entity.CommissionEntity;
import com.p6.paymybuddy.Model.Entity.TransactionInternalEntity;
import lombok.Data;


@Data
public class Commission {

    private Long id;
    private TransactionInternal transaction;
    private Double amount;

    public Commission() {

    }

}
