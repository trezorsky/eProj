package ru.evol.eProj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.evol.eProj.PersonService;
import ru.evol.eProj.model.Message;
import ru.evol.eProj.model.Person;
import ru.evol.eProj.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository repository;
    @Autowired
    private PersonService service;

    @GetMapping("/persons")
    public Iterable<Person> getPersons() {
        return repository.findAll();
    }

    @GetMapping("/persons/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return repository.findById(id);
    }

    @GetMapping("/persons/{id}/messages")
    public ResponseEntity<List<Message>> getAllMessagesOfPerson(@PathVariable int id) {
        if (repository.existsById(id)) {
            return new ResponseEntity(service.getAllMessagesOfPerson(id), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/persons")
    public Person addPerson(@RequestBody Person person) {
        repository.save(person);
        return person;
    }

    @PostMapping("/persons/{id}/messages")
    public ResponseEntity<Person> addMessage(@PathVariable int id, @RequestBody Message message) {
        if (repository.existsById(id)) {
            return new ResponseEntity(service.addMessageToPerson(id, message), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
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

    @DeleteMapping("/persons/{id}/messages/{messageId}")
    public ResponseEntity<Person> deletePersonMessage(@PathVariable int id, @PathVariable int messageId) {
        if (repository.existsById(id)) {
            return new ResponseEntity(service.deleteMessageFromPerson(id, messageId), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public String hello() {
        return "Hello, World!";
    }

}
