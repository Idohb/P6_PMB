package com.p6.paymybuddy.Service;

import com.p6.paymybuddy.Controller.Dto.Person.PersonRequest;
import com.p6.paymybuddy.Mapper.PersonConverter;
import com.p6.paymybuddy.Model.Entity.PersonEntity;
import com.p6.paymybuddy.Model.Repository.PersonRepository;
import com.p6.paymybuddy.Service.Data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonService {

    // Add constructor
    @Autowired
    private PersonConverter personConverter;
    @Autowired
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


    public Person addPerson(PersonRequest personRequest) {
        PersonEntity personEntity = new PersonEntity(0L,
                personRequest.getFirstName(),
                personRequest.getLastName(),
                null,null);
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

    //     public void attributeCrediteurToPerson(List<PersonEntity> globalPersonEntityList, List<CrediteurEntity> globalCrediteurEntityList) {
//
//        for (FireStationEntity fireStationEntity : globalFireStationEntityList) {
//
//            String addressFireStation = fireStationEntity.getAddress();
//            List<PersonEntity> personEntityList = new ArrayList<>();
//
//            for (PersonEntity personEntity : globalPersonEntityList) {
//                if (personEntity.getAddress().equals(addressFireStation)) {
//                    personEntityList.add(personEntity);
//                }
//            }
//
//            fireStationEntity.setPersonFireStation(personEntityList);
//
//        }
//
//    }

}
