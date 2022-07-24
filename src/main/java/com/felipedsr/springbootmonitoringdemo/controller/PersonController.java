package com.felipedsr.springbootmonitoringdemo.controller;

import com.felipedsr.springbootmonitoringdemo.model.Person;
import com.felipedsr.springbootmonitoringdemo.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
        logger.debug("Inside findAll method"  );
        return new ResponseEntity<>(personRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Person> post(@RequestBody Person person) {
        return new ResponseEntity<>(personRepository.save(person), HttpStatus.CREATED);
    }

    @GetMapping(path = "/firstName/{firstName}")
    public ResponseEntity<List<Person>> findByFirstName(@PathVariable String firstName) {
        logger.debug("Inside findFirstName method"  );
        return new ResponseEntity<>(personRepository.findByFirstNameIgnoringCase(firstName), HttpStatus.OK);
    }

    @GetMapping(path = "/lastName/{lastName}")
    public ResponseEntity<List<Person>> findByLastName(@PathVariable String lastName) {
        logger.debug("Inside findLastName");
        return new ResponseEntity<>(personRepository.findByLastNameIgnoringCase(lastName
        ), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(String id) {
        personRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
