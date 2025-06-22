package com.github.hotechbackend.task.interfaces.resources;

public record TaskResource(
        Long id,
        String title,
        String description,
        String status,
        String dueDateFormatted,
        Long roomId
) {
}
