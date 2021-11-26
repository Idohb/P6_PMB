package com.p6.paymybuddy.Controller;

import com.p6.paymybuddy.Service.Data.Person;
import com.p6.paymybuddy.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class PersonController {

    // Add constructor if you want to put a "final" in autowired
    @Autowired
    private PersonService personService;



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
}
