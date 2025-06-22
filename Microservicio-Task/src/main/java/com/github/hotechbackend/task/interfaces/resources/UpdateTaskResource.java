package com.github.hotechbackend.task.interfaces.resources;

import java.util.Date;

public record UpdateTaskResource(
        String title,
        String description,
        Date dueDate,
        Long roomId
) {
}