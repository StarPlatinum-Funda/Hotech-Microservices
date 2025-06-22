package com.github.hotechbackend.task.interfaces.transform;

import com.github.hotechbackend.task.domain.model.commands.UpdateTaskCommand;
import com.github.hotechbackend.task.interfaces.resources.UpdateTaskResource;

public class UpdateTaskCommandFromResourceAssembler {

    public static UpdateTaskCommand toCommandFromResource(UpdateTaskResource resource, Long taskId) {
        return new UpdateTaskCommand(
                taskId,
                resource.title(),
                resource.description(),
                resource.dueDate(),
                resource.roomId()
        );
    }
}