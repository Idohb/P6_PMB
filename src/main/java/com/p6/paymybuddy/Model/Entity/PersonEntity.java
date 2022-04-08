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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "login_id")
    private LoginEntity login;

    @ManyToMany
    @JoinTable(
            name="social_networks",
            joinColumns = @JoinColumn(name="is_related_to"),
            inverseJoinColumns = @JoinColumn(name="this_person")
    )
    private List<PersonEntity> friends;

    public void addFriend(PersonEntity personEntity) {
        friends.add(personEntity);
    }


}

