package com.p6.paymybuddy.Mapper;

import com.p6.paymybuddy.Model.Entity.PersonEntity;
import com.p6.paymybuddy.Service.Data.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonConverter {

    public Person mapperPerson(PersonEntity personEntity) {
        Person person = new Person();
        person.setIdPerson(personEntity.getIdPerson());
        person.setFirstName(personEntity.getFirstName());
        person.setLastName(personEntity.getLastName());

        return person;
    }

    public List<Person> mapperPerson(List<PersonEntity> personEntities) {
        return personEntities.stream().map(this::mapperPerson).collect(Collectors.toList());
    }
}
