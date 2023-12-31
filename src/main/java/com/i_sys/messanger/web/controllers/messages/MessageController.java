package com.i_sys.messanger.web.controllers.messages;

import com.i_sys.messanger.core.domains.messages.services.IMessageService;
import com.i_sys.messanger.data.messages.Message;
import com.i_sys.messanger.web.controllers.messages.dto.MessageDtoGet;
import com.i_sys.messanger.web.controllers.messages.dto.MessageDtoPostOrPut;
import com.i_sys.messanger.web.exceptions.ValidationException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/message")
@SecurityRequirement(name = "Bearer Authentication")
public class MessageController {
    private final IMessageService messageService;
    private static final Logger log = LoggerFactory.getLogger(MessageController.class.getName());

    public MessageController(IMessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<MessageDtoGet> getAllMessages() throws Exception {
        log.info("Call method of MessageController: getAllMessages()");
        List<MessageDtoGet> result = new ArrayList<>();

        for (Message message : messageService.getAllMessages()) {
            result.add(new MessageDtoGet(message));
        }

        log.info("Method of MessageController: getAllMessages(): HttpStatus 200");
        return result;
    }

    @GetMapping({"id"})
    public MessageDtoGet getMessageById(@RequestParam UUID id) throws Exception {
        log.info("Call method of MessageController: getMessageById(" + id + ")");

        if (id == null) {
            throw new ValidationException("Id is null!");
        }

        MessageDtoGet result = new MessageDtoGet(messageService.getMessageById(id));
        log.info("Method of MessageController: getMessageById(" + id + ") HttpStatus 200");
        return result;
    }

    @PostMapping
    public Message createMessage(@RequestBody MessageDtoPostOrPut message) throws Exception {
        log.info("Call method of MessageController: createMessage(" + message + ")");

        if (message == null) {
            throw new ValidationException("Message is null!");
        }

        Message result = messageService.createMessage(message);
        log.info("Call method of MessageController: createMessage(" + message + ") HttpStatus 200");

        return result;
    }

    @PutMapping({"id"})
    public Message updateMessage(@RequestParam UUID id, @RequestBody MessageDtoPostOrPut message) throws Exception {
        log.info("Call Method of MessageController: updateMessage(" + id + "," + message + ")");

        if (id == null) {
            throw new ValidationException("Id is null!");
        }

        if (message == null) {
            throw new ValidationException("Message is null!");
        }

        Message result = messageService.updateMessage(id, message);
        log.info("Call method of MessageController: updateMessage(" + message + ") HttpStatus 200");

        return result;
    }

    @DeleteMapping({"id"})
    public void deleteMessage(@RequestParam UUID id) throws Exception {
        log.info("Call method of MessageController: deleteMessage(" + id + ")");

        if (id == null) {
            throw new ValidationException("Id is null!");
        }

        messageService.deleteMessage(id);
        log.info("Method of MessageController: deleteMessage(" + id + ") HttpStatus 200");
    }

    @PatchMapping
    public void doRead(@RequestParam UUID id) throws Exception {
        log.info("Call method of MessageController: doRead(" + id + ")");

        if (id == null) {
            throw new ValidationException("Id is null!");
        }

        log.info("Method of MessageController: doRead(" + id + ") HttpStatus 200");
    }
}
