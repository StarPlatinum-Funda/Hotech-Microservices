package com.github.hotech.backend.message.interfaces.rest.transform;

import com.github.hotech.backend.message.domain.model.commands.CreateMessageCommand;
import com.github.hotech.backend.message.interfaces.rest.resources.CreateMessageResource;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CreateMessageCommandFromResourceAssembler {

    public CreateMessageCommand toCommandFromResource(CreateMessageResource resource) {
        return new CreateMessageCommand(
                resource.id(),
                resource.receptor(),
                resource.sender(),
                resource.content(),
                resource.notificationId(),
                resource.userAccountId()
        );
    }
}
