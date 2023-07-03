package com.i_sys.messanger.core.domains.messages.services;

import com.i_sys.messanger.data.messages.Message;
import com.i_sys.messanger.web.controllers.messages.dto.MessageDtoPostOrPut;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IMessageService {
    List<Message> getAllMessages() throws Exception;

    Message getMessageById(UUID id) throws Exception;

    Message createMessage(MessageDtoPostOrPut message) throws Exception;

    Message updateMessage(UUID id, MessageDtoPostOrPut message) throws Exception;

    void deleteMessage(UUID id) throws Exception;

    void doRead(UUID id);

    void doUnRead(UUID id);
}
