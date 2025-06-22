package com.github.hotechbackend.task.interfaces.transform;


import com.github.hotechbackend.task.domain.model.aggregates.Task;
import com.github.hotechbackend.task.interfaces.resources.TaskResource;

public class TaskResourceFromEntityAssembler {

    public static TaskResource toResourceFromEntity(Task task) {
        return new TaskResource(
                task.getId(),
                task.getTaskInformation().getTitle(),
                task.getTaskInformation().getDescription(),
                task.getStatus().name(),
                task.getNiceDueDate(),
                task.getRoom().getRoomId()
        );
    }
}