package com.p6.paymybuddy.Model.Entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "commission")
public class CommissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_commission", nullable = false)
    private Long id_commission;

    @Column(name="transaction_id")
    private Long transaction_id;

    @Column(name = "amount")
    private Long amount;

}
