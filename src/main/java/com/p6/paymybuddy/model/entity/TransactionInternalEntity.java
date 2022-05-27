package com.p6.paymybuddy.model.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "transaction_internal")
public class TransactionInternalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Double amount;

    private String timeTransaction;

    @ManyToOne
    private PersonEntity crediteur;

    @ManyToOne
    private PersonEntity debiteur;
}
