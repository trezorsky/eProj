package ru.evol.eProj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.evol.eProj.model.Person;
import ru.evol.eProj.model.Weather;
import ru.evol.eProj.repository.PersonRepository;

import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("{id}/weather")
    public ResponseEntity<Weather> getWeather(@PathVariable int id) {
        if (repository.existsById(id)) {
            String location = repository.findById(id).get().getLocation();
            Weather weather = restTemplate.getForObject("http://localhost:8083/weather?location=" + location, Weather.class);
            return new ResponseEntity(weather, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public Iterable<Person> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> findById(int id) {
        return repository.findById(id);
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person) {
        return repository.findById(person.getId()).isPresent()
                ? new ResponseEntity(repository.findById(person.getId()), HttpStatus.BAD_REQUEST)
                : new ResponseEntity(repository.save(person), HttpStatus.CREATED);
    }

//    @PostMapping("/person")
//    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
//        Person savedPerson = repository.save(person);
//        return new ResponseEntity(savedPerson, HttpStatus.CREATED);
//    }


//    @PutMapping("/persons/{id}")
//    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
//        if (repository.existsById(id)) {
//            person.setId(id);
//            return new ResponseEntity(repository.save(person), HttpStatus.OK);
//        } else {
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @DeleteMapping("/persons/{id}")
//    public void deletePerson(@PathVariable int id) {
//        repository.deleteById(id);
//    }

}
