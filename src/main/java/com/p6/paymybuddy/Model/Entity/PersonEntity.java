package com.p6.paymybuddy.Model.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="person")
public class PersonEntity {

    //see for this warning JPA and @Data power consumption

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPerson;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @OneToMany(mappedBy = "debiteur")
    private List<TransactionInternalEntity> debiteur;

    @OneToMany(mappedBy = "crediteur")
    private List<TransactionInternalEntity> crediteur;



}

