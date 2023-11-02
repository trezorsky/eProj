package ru.evol.eProj.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.evol.eProj.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

}

