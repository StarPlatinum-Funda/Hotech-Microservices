package com.github.hotechbackend.task.domain.model.commands;

import java.util.Date;


public record CreateTaskCommand(
        String title,
        String description,
        Long roomId,
        Date dueDate
) {}