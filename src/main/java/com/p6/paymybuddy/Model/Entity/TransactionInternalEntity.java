package com.p6.paymybuddy.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(value = "login")
public class TransactionInternalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransactionInternal;

    @Column(name="Description")
    private String description;

    @Column(name="Amount")
    private String amount;

    @Column(name="TimeTransactionInternal")
    private String timeTransactionInternal;

    @Column(name="Crediteur")
    private Long Crediteur;

    @Column(name="Debiteur")
    private Long debiteur;
}
//
//
//
//package com.p6.paymybuddy.Model.Entity;
//
//public class TransactionInternalEntity {
//}
