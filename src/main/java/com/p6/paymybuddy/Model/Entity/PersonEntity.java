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
@Table(value = "person")
public class PersonEntity {

    //see for this warning JPA and @Data power consumption

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPerson;

    @Column(name="FirstName")
    private String firstName;

    @Column(name="LastName")
    private String lastName;
}

