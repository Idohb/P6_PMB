package com.p6.paymybuddy.controller.dto.transactionExternal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionExternalResponse {
    private Long id;
    private String description;
    private Double amount;
    private String timeTransaction;
    private Long user_id;
}
