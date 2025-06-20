package com.github.hotech.backend.message.interfaces.rest.transform;

import com.github.hotech.backend.message.domain.model.aggregates.Message;
import com.github.hotech.backend.message.interfaces.rest.resources.MessageResource;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageResourceFromEntityAssembler {

    public MessageResource toResourceFromEntity(Message message) {
        return new MessageResource(
                message.getId(),
                message.getNotificationId().notificationId(),
                message.getUserAccountId().userAccountId(),
                message.getReceptor(),
                message.getSender(),
                message.getContent(),
                message.getStatus().toString()
        );
    }
}
