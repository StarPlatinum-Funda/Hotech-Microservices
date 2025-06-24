package com.github.hotech.backend.message.infrastructure.listeners;

import com.github.hotech.backend.message.domain.model.aggregates.Message;
import com.github.hotech.backend.message.domain.model.commands.CreateMessageCommand;
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

    @KafkaListener(topics = "message-topic", groupId = "message", containerFactory = "kafkaListenerContainerFactory")
    public void reciveItem(Map<String, Object> val){
        item = val;
        var id = (Number)item.get("id");
        System.out.println(item.toString());
    }

    /*{
        "roomNumber":"101",
        "id":1,
        "userId": 1
    }*/
    @KafkaListener(topics = "create-message", groupId = "message", containerFactory = "kafkaListenerContainerFactory")
    public void createMessage(Map<String, Object> val){
        var roomNumber = val.get("roomNumber");
        var id = ((Number)val.get("id")).longValue();
        var userId = ((Number)val.get("userId")).longValue();

        System.out.println("Creating message with room number: " + roomNumber + " id: " + id + " userId: " + userId);

        //Se crea el mensaje
        var command = new CreateMessageCommand(id, "everyone", "system", "Room created with number: " + roomNumber,
                id, userId);
        var messsage = new Message(command);
        mesageRepository.save(messsage);
        System.out.println("Message created");
    }
}
