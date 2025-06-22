package com.github.hotechbackend.task.application.internal.commandservice;

import com.github.hotechbackend.task.domain.model.aggregates.Task;
import com.github.hotechbackend.task.domain.model.commands.CompleteTaskCommand;
import com.github.hotechbackend.task.domain.model.commands.CreateTaskCommand;
import com.github.hotechbackend.task.domain.model.commands.DeleteTaskCommand;
import com.github.hotechbackend.task.domain.model.commands.StartTaskCommand;
import com.github.hotechbackend.task.domain.services.TaskCommandService;
import com.github.hotechbackend.task.infrastructure.persistence.jpa.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskCommandServiceImpl implements TaskCommandService {

    private final TaskRepository taskRepository;

    @Override
    public Optional<Task> handle(CreateTaskCommand command) {
        return Optional.empty();
    }

    @Override
    public void handle(StartTaskCommand command) {

    }

    @Override
    public void handle(CompleteTaskCommand command) {

    }

    @Override
    public void handle(DeleteTaskCommand command) {

    }
}
