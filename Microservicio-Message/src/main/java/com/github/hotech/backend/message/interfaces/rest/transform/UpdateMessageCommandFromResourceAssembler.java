package com.github.hotech.backend.message.interfaces.rest.transform;

import com.github.hotech.backend.message.domain.model.commands.UpdateMessageCommand;
import com.github.hotech.backend.message.interfaces.rest.resources.UpdateMessageResource;

public class UpdateMessageCommandFromResourceAssembler {
    public static UpdateMessageCommand toCommandFromResource(Long messageId, UpdateMessageResource resource) {
        return new UpdateMessageCommand(messageId, resource.receptor(), resource.sender(),
                resource.content(), resource.notificationId(), resource.userAccountId());
    }
}
