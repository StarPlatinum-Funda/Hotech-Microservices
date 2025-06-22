package com.github.hotechbackend.task.infrastructure.listeners;

import com.github.hotechbackend.task.infrastructure.persistence.jpa.repositories.TaskRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TaskListener {

    private final TaskRepository taskRepository;

    public TaskListener(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private Map<String, Object> item;

    /*
    {

    }
    */

    @KafkaListener(topics = "task-topic", groupId = "task", containerFactory = "kafkaListenerContainerFactory")
    public void reciveItem(Map<String, Object> val){
        item = val;
        var id = (Number)item.get("id");
        System.out.println(item.toString());
    }
}
