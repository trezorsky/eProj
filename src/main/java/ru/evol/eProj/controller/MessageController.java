package ru.evol.eProj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.evol.eProj.model.Message;
import ru.evol.eProj.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository repositoryMessages;

    @GetMapping("/messages")
    public Iterable<Message> getMessages() {
        return repositoryMessages.findAll();
    }

    @GetMapping("/messages/{id}")
    public Optional<Message> findMessageById(@PathVariable int id) {
        return repositoryMessages.findById(id);
    }

    @PostMapping("/messages")
    public Message addMessage(@RequestBody Message message) {
        repositoryMessages.save(message);
        return message;
    }

    @PutMapping("/messages/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody Message message) {
        if (repositoryMessages.existsById(id)) {
            message.setId(id);
            return new ResponseEntity(repositoryMessages.save(message), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/messages/{id}")
    public void deleteMessage(@PathVariable int id) {
        repositoryMessages.deleteById(id);
    }
}
