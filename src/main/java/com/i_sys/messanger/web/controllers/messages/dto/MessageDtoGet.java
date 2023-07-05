package com.i_sys.messanger.web.controllers.messages.dto;

import com.i_sys.messanger.data.messages.Message;

import java.util.UUID;

public class MessageDtoGet {
    public UUID id;
    public String text;
    public UUID senderId;
    public boolean isRead;

    public MessageDtoGet(Message message) {
        id = message.getId();
        text = message.getText();
        senderId = message.getSender().getId();
        isRead = message.isRead();
    }
}
