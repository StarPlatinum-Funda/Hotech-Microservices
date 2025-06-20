package com.github.hotech.backend.message.infrastructure.listeners;

import com.github.hotech.backend.message.infrastructure.persistence.jpa.repositories.MessageRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MessageListener {

    private final MessageRepository mesageRepository;

    public MessageListener(MessageRepository mesageRepository) {
        this.mesageRepository = mesageRepository;
    }

    private Map<String, Object> item;

    /*
    {

    }
    */

    @KafkaListener(topics = "item-topic", groupId = "message", containerFactory = "kafkaListenerContainerFactory")
    public void reciveItem(Map<String, Object> val){
        item = val;
        var id = (Number)item.get("id");
        System.out.println(item.toString());
    }
}
