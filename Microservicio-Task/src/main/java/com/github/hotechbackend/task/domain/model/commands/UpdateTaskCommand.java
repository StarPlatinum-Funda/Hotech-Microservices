package com.github.hotechbackend.task.domain.model.commands;

import java.util.Date;

public class UpdateTaskCommand {
    private final Long taskId;
    private final String title;
    private final String description;
    private final Date dueDate;
    private final Long roomId;

    public UpdateTaskCommand(Long taskId, String title, String description, Date dueDate, Long roomId) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.roomId = roomId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Long getRoomId() {
        return roomId;
    }
}