package com.github.hotech.backend.message.domain.services;

import com.github.hotech.backend.message.domain.model.aggregates.Message;
import com.github.hotech.backend.message.domain.model.queries.GetAllMessagesQuery;
import com.github.hotech.backend.message.domain.model.queries.GetMessageByIdQuery;
import com.github.hotech.backend.message.domain.model.queries.GetMessagesByNotificationIdAndUserAccountIdQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MessageQueryService {
    Optional<Message> handle(GetMessageByIdQuery query);
    List<Message> handle(GetMessagesByNotificationIdAndUserAccountIdQuery query);
    List<Message> handle(GetAllMessagesQuery query);
}
