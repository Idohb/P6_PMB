package com.p6.paymybuddy.service.data;

import lombok.Data;


@Data
public class Commission {

    private Long id;
    private TransactionInternal transaction;
    private Double amount;

    public Commission() {

    }

}
