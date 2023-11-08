package ru.evol.eProj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.evol.eProj.model.Person;
import ru.evol.eProj.repository.PersonRepository;

import java.util.Optional;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/persons")
    public Iterable<Person> getPersons() {
        return repository.findAll();
    }

    @GetMapping("/persons/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return repository.findById(id);
    }

    @PostMapping("/persons")
    public Person addPerson(@RequestBody Person person) {
        repository.save(person);
        return person;
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        if (repository.existsById(id)) {
            person.setId(id);
            return new ResponseEntity(repository.save(person), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/persons/{id}")
    public void deletePerson(@PathVariable int id) {
        repository.deleteById(id);
    }

    @GetMapping
    public String hello() {
        return "Hello, World!";
    }

}
