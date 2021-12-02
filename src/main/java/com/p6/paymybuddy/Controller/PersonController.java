package com.p6.paymybuddy.Controller;

import com.p6.paymybuddy.Controller.Dto.Person.PersonRequest;
import com.p6.paymybuddy.Service.Data.Person;
import com.p6.paymybuddy.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping("persons")
    public ResponseEntity<List<Person>> getPersons() {
        try {
            return ResponseEntity.ok(personService.getPersons());
        } catch (NoSuchElementException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("person/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id") final Long id) {
        try {
            return ResponseEntity.ok(personService.getPerson(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("person")
    public ResponseEntity<Person> createPerson(@RequestBody PersonRequest person) {
        return ResponseEntity.ok(personService.addPerson(person));
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") final Long id, @RequestBody PersonRequest person) {
        try {
            return ResponseEntity.ok(personService.updatePerson(id, person));
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable("id") final Long id) {
        try {
            personService.getPerson(id);
            personService.deletePerson(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/persons")
    public ResponseEntity<?> deletePersons() {
        personService.deletePersons();
        return ResponseEntity.noContent().build();
    }

}
