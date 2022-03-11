package com.p6.paymybuddy.Model.Entity;

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
    private Long idTransactionInternal;

    @Column(name="description")
    private String description;

    @Column(name="amount")
    private String amount;

    @Column(name="time_transaction")
    private String timeTransactionInternal;

    @ManyToOne
    @JoinColumn(name="crediteur")
    private PersonEntity crediteur;

    @ManyToOne
    @JoinColumn(name="debiteur")
    private PersonEntity debiteur;
}
//
//
//
//package com.p6.paymybuddy.Model.Entity;
//
//public class TransactionInternalEntity {
//}
