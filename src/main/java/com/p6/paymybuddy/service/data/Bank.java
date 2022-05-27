package com.p6.paymybuddy.service.data;

import lombok.Data;

@Data
public class Bank {
    Long id;
    String iban;
    Double amount;
    Person user;
}
