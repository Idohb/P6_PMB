package com.p6.paymybuddy.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.p6.paymybuddy.Model.Entity.PersonEntity;
import com.p6.paymybuddy.Service.Data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonConverter {

    @Autowired
    private ObjectMapper objectMapper;

    public Person mapperPerson(PersonEntity personEntity) {
        Person p = new Person();
        p.setId(personEntity.getId());
        p.setFirstName(personEntity.getFirstName());
        p.setLastName(personEntity.getLastName());
        return p;
    }

    public List<Person> mapperPerson(List<PersonEntity> personEntities) {
        return personEntities.stream().map(this::mapperPerson).collect(Collectors.toList());
    }
}
