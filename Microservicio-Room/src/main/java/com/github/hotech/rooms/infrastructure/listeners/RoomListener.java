package com.github.hotech.rooms.infrastructure.listeners;

import com.github.hotech.rooms.infrastructure.persistence.jpa.repositories.RoomRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RoomListener {

    private final RoomRepository roomRepository;

    public RoomListener(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    private Map<String, Object> item;

    /*
    {

    }
    */

    @KafkaListener(topics = "item-topic", groupId = "room", containerFactory = "kafkaListenerContainerFactory")
    public void reciveItem(Map<String, Object> val){
        item = val;
        var id = (Number)item.get("id");
        System.out.println(item.toString());
    }
}
