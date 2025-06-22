package com.github.hotechbackend.task.interfaces.resources;


import java.util.Date;

public record CreateTaskResource(
        String title,
        String description,
        Long roomId,
        Date dueDate

) {
}