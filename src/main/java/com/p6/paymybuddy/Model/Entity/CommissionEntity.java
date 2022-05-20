package com.p6.paymybuddy.Model.Entity;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "commission")
public class CommissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcommission;

    @Column(name="transactionid")
    private Long transactionid;

    @Column(name = "amount")
    private String amount;

}
