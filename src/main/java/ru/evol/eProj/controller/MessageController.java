package ru.evol.eProj.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.evol.eProj.model.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
// путь для запросов к сообщениям
@RequestMapping("/messages")
public class MessageController {

    private List<Message> messages = new ArrayList<>(Arrays.asList(
            new Message(1, "Заголовок 1", "Текст 1", LocalDateTime.now()),
            new Message(2, "Заголовок 2", "Текст 2", LocalDateTime.now()),
            new Message(3, "Заголовок 3", "Текст 3", LocalDateTime.now())
    ));

    @PostMapping("/create")
    public Message createMessage(@RequestBody Message message) {
        messages.add(message);
        return message;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody Message message) {
        Optional<Message> existingMessage = messages.stream()
                .filter(m -> m.getId() == id)
                 .findFirst();

        if (existingMessage.isPresent()) {
            Message updatedMessage = existingMessage.get();
            updatedMessage.setId(message.getId());
            updatedMessage.setTitle(message.getTitle());
            updatedMessage.setText(message.getText());
            updatedMessage.setTime(LocalDateTime.now());
            return new ResponseEntity<>(updatedMessage, HttpStatus.OK);
        } else {
            messages.add(message);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMessage(@PathVariable int id) {
        messages.removeIf(m -> m.getId() == id);
    }

    @GetMapping("/list")
    public Iterable<Message> getAllMessages() {
        return messages;
    }

    @GetMapping("/{id}")
    public Optional<Message> getMessageById(@PathVariable int id) {
        return messages.stream().filter(m -> m.getId() == id).findFirst();
    }
}
