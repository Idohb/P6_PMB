package com.p6.paymybuddy.controller.dto.Commission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommissionResponse {
    private Long idcommission;
    private String transactionid;
    private String Amount;
}
