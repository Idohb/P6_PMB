package com.p6.paymybuddy.service;

import com.p6.paymybuddy.controller.dto.person.PersonRequest;
import com.p6.paymybuddy.mapper.LoginConverter;
import com.p6.paymybuddy.mapper.PersonConverter;
import com.p6.paymybuddy.model.entity.LoginEntity;
import com.p6.paymybuddy.model.entity.PersonEntity;
import com.p6.paymybuddy.model.repository.PersonRepository;
import com.p6.paymybuddy.service.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonService {

    // Add constructor
    @Autowired
    private PersonConverter personConverter;
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private LoginConverter loginConverter;

    public PersonService() {
    }

    public List<Person> getPersons() {
        return personConverter.mapperPerson( personRepository.findAll());
    }

    public Person getPerson(final Long id) {
        PersonEntity personEntity = personRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        return personConverter.mapperPerson(personEntity);
    }


    public Person addPerson(PersonRequest personRequest) {
        PersonEntity personEntity = new PersonEntity(0L,
                personRequest.getFirstName(),
                personRequest.getLastName(),
                new ArrayList<>(),new ArrayList<>(),new LoginEntity(0L,personRequest.getUsername(),personRequest.getPassword()),new ArrayList<>());
        personEntity = personRepository.save(personEntity);
        return personConverter.mapperPerson(personEntity);

    }

    public Person updatePerson(final Long id, PersonRequest personRequest) {

        PersonEntity entity = personRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        updateEntity(entity, personRequest);
        entity = personRepository.save(entity);
        return personConverter.mapperPerson(entity);

    }

    public void deletePerson(final Long id) {
        personRepository.deleteById(id);
    }

    public void deletePersons() {
        personRepository.deleteAll();
    }

    private void updateEntity(PersonEntity personEntity, PersonRequest personRequest) {

        if (personRequest.getFirstName() != null)
            personEntity.setFirstName(personRequest.getFirstName());

        if (personRequest.getLastName() != null)
            personEntity.setLastName(personRequest.getLastName());

    }

    public Long setFriends(Long lCrediteur, Long lDebiteur) {
        List<PersonEntity> personEntityList;
        PersonEntity peCrediteur = personRepository.findById(lCrediteur).orElseThrow( () -> new NoSuchElementException("") );
        PersonEntity peDebiteur  = personRepository.findById(lDebiteur).orElseThrow( () -> new NoSuchElementException("") );
        personEntityList = peCrediteur.getFriends();
        personEntityList.add(peDebiteur);
        peCrediteur.setFriends(personEntityList);
        personRepository.save(peCrediteur);
        return 0L;
    }

//    public Person getPerson(final Long id) {
//        PersonEntity personEntity = personRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
//        return personConverter.mapperPerson(personEntity);
//    }

    public List<Person> getFriends(Long id) {
        PersonEntity personEntity = personRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        List<PersonEntity> friends = personEntity.getFriends();
        return personConverter.mapperPerson(friends);
    }
}
