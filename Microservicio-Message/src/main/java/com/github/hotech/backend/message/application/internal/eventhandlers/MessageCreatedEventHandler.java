package com.github.hotech.backend.message.application.internal.eventhandlers;

import com.github.hotech.backend.message.domain.model.event.MessageCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class MessageCreatedEventHandler {

    @EventListener(MessageCreatedEvent.class)
    public void on(MessageCreatedEvent event) {
        // TODO: Implement this
    }
}
