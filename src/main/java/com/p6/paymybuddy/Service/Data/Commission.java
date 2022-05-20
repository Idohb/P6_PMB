package com.p6.paymybuddy.Service.Data;

import com.p6.paymybuddy.Model.Entity.CommissionEntity;
import lombok.Data;


@Data
public class Commission {

    private Long id_commission;
    private Long transactionid;
    private String amount;

    public Commission() {

    }

    public Commission(CommissionEntity cE) {
        setId_commission(cE.getIdcommission());
        setTransactionid(cE.getTransactionid());
        setAmount(cE.getAmount());
    }

}
