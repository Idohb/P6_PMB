package com.p6.paymybuddy.service.data;

import lombok.Data;

@Data
public class TransactionExternal {
    private Long id;
    private String description;
    private Double amount;
    private String timeTransaction;
    private Person user;
}
