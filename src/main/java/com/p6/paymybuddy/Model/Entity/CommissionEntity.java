package com.p6.paymybuddy.Model.Entity;

import com.p6.paymybuddy.Service.Data.TransactionInternal;
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
    private Long id;

    @ManyToOne
    private TransactionInternalEntity transaction;

    private Double amount;

}
