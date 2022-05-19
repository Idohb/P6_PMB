package com.p6.paymybuddy.Service.Data;

import com.p6.paymybuddy.Model.Entity.CommissionEntity;
import lombok.Data;


@Data
public class Commission {

    private Long id_commission;
    private Long transaction_id;
    private Long amount;

    public Commission() {

    }

    public Commission(CommissionEntity cE) {
        setId_commission(cE.getId_commission());
        setTransaction_id(cE.getTransaction_id());
        setAmount(cE.getAmount());
    }

}
