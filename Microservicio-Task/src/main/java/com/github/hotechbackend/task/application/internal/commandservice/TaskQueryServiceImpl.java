package com.github.hotechbackend.task.application.internal.commandservice;

import com.github.hotechbackend.task.domain.model.aggregates.Task;
import com.github.hotechbackend.task.domain.model.queries.GetAllTaskForRoomQuery;
import com.github.hotechbackend.task.domain.model.queries.GetAllTaskQuery;
import com.github.hotechbackend.task.domain.model.queries.GetTaskByIdQuery;
import com.github.hotechbackend.task.domain.services.TaskQueryService;
import com.github.hotechbackend.task.infrastructure.persistence.jpa.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskQueryServiceImpl implements TaskQueryService {

    private final TaskRepository taskRepository;

    public TaskQueryServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<Task> handle(GetTaskByIdQuery query) {
        return taskRepository.findById(query.taskId());
    }

    @Override
    public List<Task> handle(GetAllTaskQuery query) {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> handle(GetAllTaskForRoomQuery query) {
        return taskRepository.findAllByEmployee(query.roomIdentifier());
    }
}
