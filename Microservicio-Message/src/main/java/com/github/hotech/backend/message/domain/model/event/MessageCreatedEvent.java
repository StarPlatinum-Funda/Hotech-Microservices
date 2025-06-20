package com.github.hotech.backend.message.domain.model.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class MessageCreatedEvent extends ApplicationEvent {

    private final Long messageId;

    public MessageCreatedEvent(Object source, Long messageId) {
        super(source);
        this.messageId = messageId;
    }
}
