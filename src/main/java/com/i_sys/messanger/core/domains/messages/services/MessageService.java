package com.i_sys.messanger.core.domains.messages.services;

import com.i_sys.messanger.core.domains.messages.repositories.IMessageRepository;
import com.i_sys.messanger.core.domains.users.repositories.IUserRepository;
import com.i_sys.messanger.data.messages.Message;
import com.i_sys.messanger.data.users.User;
import com.i_sys.messanger.web.controllers.messages.dto.MessageDtoPostOrPut;
import com.i_sys.messanger.web.exceptions.NotFoundException;
import com.i_sys.messanger.web.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MessageService implements IMessageService {

    private static final Logger log = LoggerFactory.getLogger(MessageService.class.getName());
    private final IMessageRepository messageRepository;
    private final IUserRepository userRepository;

    @Autowired
    public MessageService(IMessageRepository messageRepository, IUserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public List<Message> getAllMessages() throws Exception {
        log.info("Call Method of MessageService: getAllMessages()");

        List<Message> result = messageRepository.findAll();

        log.info("Method of MessageService: getAllMessages() successfully completed");

        return result;
    }

    public Message getMessageById(UUID id) throws Exception {
        log.info("Call Method of MessageService: getMessageById(" + id + ")");
        Message message = messageRepository.findById(id).orElse(null);

        if (message == null) {
            throw new NotFoundException("The message with the specified id was not found in the system!");
        }

        log.info("Method of MessageService: getMessageById(" + id + ") successfully completed");

        return message;
    }

    public Message createMessage(MessageDtoPostOrPut message) throws Exception {
        log.info("Call Method of MessageService: createMessage(" + message + ")");

        if (message.text == null) {
            throw new ValidationException("The text message is null!");
        }

        User sender = userRepository.findById(message.senderId).orElse(null);

        if (sender == null) {
            throw new NotFoundException("The sender with the specified id was not found in the system!");
        }

        Message result = messageRepository.save(new Message(message.text, sender));

        log.info("Call Method of MessageService: createMessage(" + message + ") successfully completed");

        return result;
    }

    public Message updateMessage(UUID id, MessageDtoPostOrPut message) throws Exception {
        log.info("Call Method of MessageService: updateMessage(" + id + "," + message + ")");

        Message entity = messageRepository.findById(id).orElse(null);

        if (entity == null) {
            throw new NotFoundException("The message with the specified id was not found in the system!");
        }

        User sender = userRepository.findById(message.senderId).orElse(null);

        if (sender == null) {
            throw new NotFoundException("The sender with the specified id was not found in the system!");
        }

        entity.setText(message.text);
        entity.setSender(sender);

        Message result = messageRepository.save(entity);

        log.info("Call Method of MessageService: updateMessage(" + id + "," + message + ") successfully completed");

        return result;
    }

    public void deleteMessage(UUID id) throws Exception {
        log.info("Call Method of MessageService: deleteMessage(" + id + ")");
        Message result = messageRepository.findById(id).orElse(null);

        if (result == null) {
            throw new NotFoundException("The message with the specified id was not found in the system!");
        }

        messageRepository.delete(result);
        log.info("Method of MessageService: deleteMessage(" + id + ") successfully completed");
    }

    public void changeMessageReadFlag(UUID id, boolean readFlag) {

    }

    public void doRead(UUID id) {

    }

    public void doUnRead(UUID id) {

    }
}
