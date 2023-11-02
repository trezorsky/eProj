package ru.evol.eProj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.evol.eProj.model.Message;
import ru.evol.eProj.model.Person;
import ru.evol.eProj.repository.PersonRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository repositoryPersonService;

    public Person addMessageToPerson(int personId, Message message) {
        Person person = repositoryPersonService.findById(personId).get();

        message.setPerson(person);
        message.setTime(LocalDateTime.now());
        person.addMessage(message);

        return repositoryPersonService.save(person);
    }

    public Person deleteMessageFromPerson(int personId, int messageId) {
        Person person = repositoryPersonService.findById(personId).get();
        person.getMessages().removeIf(message -> message.getId() == messageId);
        return repositoryPersonService.save(person);
    }

    public List<Message> getAllMessagesOfPerson(int personId) {
        Person person = repositoryPersonService.findById(personId).get();
        return person.getMessages();
    }

}
