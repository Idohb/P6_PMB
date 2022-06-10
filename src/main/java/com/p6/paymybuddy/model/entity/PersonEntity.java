package com.p6.paymybuddy.model.entity;

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
    private Long id;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "debiteur")
    private List<TransactionInternalEntity> debiteur;

    @OneToMany(mappedBy = "crediteur")
    private List<TransactionInternalEntity> crediteur;

    @OneToMany(mappedBy = "user")
    private List<TransactionExternalEntity> user;

    @OneToOne(cascade = CascadeType.ALL)
    private BankEntity bank;

    @OneToOne(cascade = CascadeType.ALL)
    private LoginEntity login;

    @ManyToMany
    @JoinTable(
            name="social_networks",
            joinColumns = @JoinColumn(name="is_related_to"),
            inverseJoinColumns = @JoinColumn(name="this_person")
    )
    private List<PersonEntity> friends;

}

