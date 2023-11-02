package ru.evol.eProj.repository;

import org.springframework.data.repository.CrudRepository;
import ru.evol.eProj.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

}

