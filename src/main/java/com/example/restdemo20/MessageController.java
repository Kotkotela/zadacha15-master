package com.example.restdemo20;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private List<Message> messages = new ArrayList<>();

    @GetMapping
    public List<Message> getAllMessages() {
        return messages;
    }

    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable int id) {
        for (Message message : messages) {
            if (message.getId() == id) {
                return message;
            }
        }
        return null;
    }

    @PostMapping
    public Message createMessage(@RequestBody Message message) {
        messages.add(message);
        return message;
    }

    @PutMapping("/{id}")
    public Message updateMessage(@PathVariable int id, @RequestBody Message updatedMessage) {
        for (Message message : messages) {
            if (message.getId() == id) {
                message.setTitle(updatedMessage.getTitle());
                message.setText(updatedMessage.getText());
                message.setTime(updatedMessage.getTime());
                return message;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public boolean deleteMessage(@PathVariable int id) {
        Message messageToRemove = null;
        for (Message message : messages) {
            if (message.getId() == id) {
                messageToRemove = message;
                break;
            }
        }
        if (messageToRemove != null) {
            messages.remove(messageToRemove);
            return true;
        }
        return false;
    }
}
