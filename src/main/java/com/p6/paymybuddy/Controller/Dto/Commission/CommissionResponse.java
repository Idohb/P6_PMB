package com.p6.paymybuddy.Controller.Dto.Commission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommissionResponse {
    private Long id_transaction;
    private Long transaction_id;
    private Long Amount;
}
