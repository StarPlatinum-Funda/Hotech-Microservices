package com.github.hotech.backend.message.application.internal.queryservives;

import com.github.hotech.backend.message.domain.model.aggregates.Message;
import com.github.hotech.backend.message.domain.model.queries.GetAllMessagesQuery;
import com.github.hotech.backend.message.domain.model.queries.GetMessageByIdQuery;
import com.github.hotech.backend.message.domain.model.queries.GetMessagesByNotificationIdAndUserAccountIdQuery;
import com.github.hotech.backend.message.domain.model.valueobjects.NotificationId;
import com.github.hotech.backend.message.domain.model.valueobjects.UserAccountId;
import com.github.hotech.backend.message.domain.services.MessageQueryService;
import com.github.hotech.backend.message.infrastructure.persistence.jpa.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageQueryServiceImpl implements MessageQueryService {
    private final MessageRepository messageRepository;

    public MessageQueryServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Optional<Message> handle(GetMessageByIdQuery query) {
        return messageRepository.findById(query.id());
    }

    @Override
    public List<Message> handle(GetMessagesByNotificationIdAndUserAccountIdQuery query) {
        return messageRepository.getMessageByNotificationIdAndUserAccountId(new NotificationId(query.notificationId()), new UserAccountId(query.userAccountId()));
    }

    @Override
    public List<Message> handle(GetAllMessagesQuery query) {
        return messageRepository.findAll();
    }
}
