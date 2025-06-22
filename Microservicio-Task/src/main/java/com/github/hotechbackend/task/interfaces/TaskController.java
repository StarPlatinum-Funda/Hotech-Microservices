package com.github.hotechbackend.task.interfaces;

import com.github.hotechbackend.task.domain.model.aggregates.Task;
import com.github.hotechbackend.task.domain.model.commands.CompleteTaskCommand;
import com.github.hotechbackend.task.domain.model.commands.CreateTaskCommand;
import com.github.hotechbackend.task.domain.model.commands.DeleteTaskCommand;
import com.github.hotechbackend.task.domain.model.commands.StartTaskCommand;
import com.github.hotechbackend.task.domain.model.queries.GetAllTaskQuery;
import com.github.hotechbackend.task.domain.model.queries.GetTaskByIdQuery;
import com.github.hotechbackend.task.domain.services.TaskCommandService;
import com.github.hotechbackend.task.domain.services.TaskQueryService;
import com.github.hotechbackend.task.interfaces.resources.CreateTaskResource;
import com.github.hotechbackend.task.interfaces.resources.TaskResource;
import com.github.hotechbackend.task.interfaces.transform.CreateTaskCommandFromResourceAssembler;
import com.github.hotechbackend.task.interfaces.transform.TaskResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskCommandService taskCommandService;
    private final TaskQueryService taskQueryService;

    public TaskController(TaskCommandService taskCommandService, TaskQueryService taskQueryService) {
        this.taskCommandService = taskCommandService;
        this.taskQueryService = taskQueryService;
    }

    @PostMapping
    public ResponseEntity<TaskResource> createTask(@RequestBody CreateTaskResource resource) {
        CreateTaskCommand command = CreateTaskCommandFromResourceAssembler.toCommandFromResource(resource);
        Optional<Task> taskOpt = taskCommandService.handle(command);
        return taskOpt
                .map(task -> ResponseEntity.ok(TaskResourceFromEntityAssembler.toResourceFromEntity(task)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResource> getTaskById(@PathVariable Long id) {
        Optional<Task> taskOpt = taskQueryService.handle(new GetTaskByIdQuery(id));
        return taskOpt
                .map(task -> ResponseEntity.ok(TaskResourceFromEntityAssembler.toResourceFromEntity(task)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TaskResource>> getAllTasks() {
        List<Task> tasks = taskQueryService.handle(new GetAllTaskQuery());
        List<TaskResource> resources = tasks.stream()
                .map(TaskResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{id}/start")
    public ResponseEntity<Void> startTask(@PathVariable Long id) {
        taskCommandService.handle(new StartTaskCommand(id));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Void> completeTask(@PathVariable Long id) {
        taskCommandService.handle(new CompleteTaskCommand(id));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskCommandService.handle(new DeleteTaskCommand(id));
        return ResponseEntity.noContent().build();
    }
}