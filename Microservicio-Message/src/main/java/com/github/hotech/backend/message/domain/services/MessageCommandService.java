package com.github.hotech.backend.message.domain.services;

import com.github.hotech.backend.message.domain.model.aggregates.Message;
import com.github.hotech.backend.message.domain.model.commands.CreateMessageCommand;
import com.github.hotech.backend.message.domain.model.commands.DeleteMessageCommand;
import com.github.hotech.backend.message.domain.model.commands.MarkMessageAsReadCommand;
import com.github.hotech.backend.message.domain.model.commands.UpdateMessageCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface MessageCommandService {
    Optional<Message> handle(CreateMessageCommand command);
    Optional<Message> handle(UpdateMessageCommand command);
    void handle(DeleteMessageCommand command);
    void handle(MarkMessageAsReadCommand command);
}
