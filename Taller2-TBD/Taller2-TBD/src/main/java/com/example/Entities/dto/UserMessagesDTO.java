package com.example.Entities.dto;

import java.util.ArrayList;
import java.util.List;

public class UserMessagesDTO extends UserDataDTO{

    private List<MessageDTO> messages = new ArrayList<>();

    public List<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDTO> messages) {
        this.messages = messages;
    }
}
