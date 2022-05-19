package com.p6.paymybuddy.Service.Data;

import com.p6.paymybuddy.Model.Entity.PersonEntity;
import lombok.Data;

import java.util.List;

@Data
public class Person {
    private Long idPerson;
    private String firstName;
    private String lastName;

    public Person() {
    }

    public Person(PersonEntity p) {
        setIdPerson(p.getIdPerson());
        setFirstName(p.getFirstName());
        setLastName(p.getLastName());
    }

}
