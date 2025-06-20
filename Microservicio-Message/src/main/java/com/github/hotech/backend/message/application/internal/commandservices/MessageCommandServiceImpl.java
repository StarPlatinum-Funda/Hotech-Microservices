package com.github.hotech.backend.message.application.internal.commandservices;

import com.github.hotech.backend.message.domain.model.aggregates.Message;
import com.github.hotech.backend.message.domain.model.commands.CreateMessageCommand;
import com.github.hotech.backend.message.domain.model.commands.DeleteMessageCommand;
import com.github.hotech.backend.message.domain.model.commands.MarkMessageAsReadCommand;
import com.github.hotech.backend.message.domain.model.commands.UpdateMessageCommand;
import com.github.hotech.backend.message.domain.services.MessageCommandService;
import com.github.hotech.backend.message.infrastructure.persistence.jpa.repositories.MessageRepository;

import java.util.Optional;

public class MessageCommandServiceImpl implements MessageCommandService {

    private final MessageRepository messageRepository;

    public MessageCommandServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Optional<Message> handle(CreateMessageCommand command) {
        if(messageRepository.existsByMessage(command.id())) {
            throw new IllegalArgumentException("Message with " + command.id() + " already exists");
        }
        var message = new Message(command);
        messageRepository.save(message);
        return Optional.of(message);
    }

    @Override
    public Optional<Message> handle(UpdateMessageCommand command) {
        var result = messageRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Inventory does not exist");
        var messageToUpdate = result.get();
        try {
            var updatedMessage = messageRepository.save(messageToUpdate.updateInformation(command.receptor(), command.sender(), command.content(), command.notificationId(), command.userAccountId()));
            return Optional.of(updatedMessage);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating Message: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteMessageCommand command) {
        if(messageRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Message does not exist");
        }

        try {
            messageRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting message: " + e.getMessage());
        }
    }

    @Override
    public void handle(MarkMessageAsReadCommand command) {
        var message = messageRepository.findById(command.messageId());
        if (message.isEmpty()) {
            throw new IllegalArgumentException("Message not found");
        }
        message.get().markAsRead();
    }
}
