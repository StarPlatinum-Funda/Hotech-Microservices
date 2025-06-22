package com.github.hotechbackend.task.interfaces.transform;

import com.github.hotechbackend.task.domain.model.commands.CreateTaskCommand;
import com.github.hotechbackend.task.interfaces.resources.CreateTaskResource;

public class CreateTaskCommandFromResourceAssembler {

    public static CreateTaskCommand toCommandFromResource(CreateTaskResource resource) {
        return new CreateTaskCommand(
                resource.title(),
                resource.description(),
                resource.roomId(),
                resource.dueDate()
                );
    }
}