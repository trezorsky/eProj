package ru.evol.eProj.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.evol.eProj.model.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {
}
