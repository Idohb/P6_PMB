package com.p6.paymybuddy.Service;

import com.p6.paymybuddy.Mapper.PersonConverter;
import com.p6.paymybuddy.Model.Entity.PersonEntity;
import com.p6.paymybuddy.Model.Repository.PersonRepository;
import com.p6.paymybuddy.Service.Data.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonService {

    // Add constructor
    private PersonConverter personConverter;
    private PersonRepository personRepository;

    public PersonService() {
    }

    public List<Person> getPersons() {
        return personConverter.mapperPerson( personRepository.findAll());
    }

    public Person getPerson(final Long id) {
        PersonEntity personEntity = personRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        return personConverter.mapperPerson(personEntity);
    }



}
