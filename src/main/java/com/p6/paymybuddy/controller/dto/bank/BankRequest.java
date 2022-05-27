package com.p6.paymybuddy.controller.dto.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BankRequest {
    String iban;
    Double amount;
    String user_id;
}
