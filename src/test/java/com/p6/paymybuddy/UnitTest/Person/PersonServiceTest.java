package com.p6.paymybuddy.UnitTest.Person;

import com.p6.paymybuddy.mapper.PersonConverter;
import com.p6.paymybuddy.model.repository.PersonRepository;
import com.p6.paymybuddy.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonConverter personConverter;

    @InjectMocks
    private PersonService personService;

    @Test
    void getPerson_ShouldThrowExceptionOnMissingPerson() {
        when(personRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> personService.getPerson(1L));
    }

//    @Test
//    void addPerson() {
//        PersonRequest personRequest = new PersonRequest("prénomRequest", "nomRequest");
//        PersonEntity personEntity = new PersonEntity(1L, "prénomEntity", "nomEntity");
//        Person person = new Person();
//        when(personConverter.mapperPerson(personEntity)).thenReturn(person);
//
//        personService.addPerson(personRequest);
//        assertThat(personEntity.getFirstName()).isEqualTo(personRequest.getFirstName());
//    }

}
