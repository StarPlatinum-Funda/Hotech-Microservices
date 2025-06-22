package com.github.hotechbackend.task.domain.services;

import com.github.hotechbackend.task.domain.model.aggregates.Task;
import com.github.hotechbackend.task.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface TaskQueryService {

    Optional<Task > handle(GetTaskByIdQuery query);

    List<Task> handle(GetAllTaskQuery query);

    List<Task> handle(GetAllTaskForRoomQuery query);

}
