package com.p6.paymybuddy.controller.dto.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BankResponse {
    private Long id;
    private String iban;
    private Double amount;
    private Long user_id;
}
