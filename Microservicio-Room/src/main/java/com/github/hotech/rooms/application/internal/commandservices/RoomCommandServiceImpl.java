package com.github.hotech.rooms.application.internal.commandservices;

import com.github.hotech.rooms.domain.model.aggregates.Room;
import com.github.hotech.rooms.domain.model.commands.CreateRoomCommand;
import com.github.hotech.rooms.domain.model.commands.DeleteRoomCommand;
import com.github.hotech.rooms.domain.model.commands.UpdateRoomCommand;
import com.github.hotech.rooms.domain.services.RoomCommandService;
import com.github.hotech.rooms.infrastructure.persistence.jpa.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class RoomCommandServiceImpl implements RoomCommandService {

    private final RoomRepository roomRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public RoomCommandServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Optional<Room> handle(CreateRoomCommand command) {
        if (roomRepository.existsByRoomNumber(command.roomNumber())) {
            throw new IllegalArgumentException("Room number with " + command.roomNumber()+ " already exists");
        }
        var room = new Room(command);
        roomRepository.save(room);

        //Sends notification for created room to kafka
        var message = Map.of(
                "roomNumber", room.getRoomNumber(),
                "id", room.getId(),
                "userId", command.userId()
        );
        kafkaTemplate.send("create-message", message);

        return Optional.of(room);
    }


    @Override
    public Optional<Room> handle(UpdateRoomCommand command) {
        if (roomRepository.existsByRoomNumberAndIdIsNot(command.roomNumber(), command.id()))
            throw new IllegalArgumentException("Room with same room number already exists");

        var result = roomRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Room does not exist");

        var roomToUpdate = result.get();
        try {
            var updatedRoom = roomRepository.save(roomToUpdate.updateInformation(command));
            return Optional.of(updatedRoom);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating room: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteRoomCommand command){
        if(!roomRepository.existsById(command.roomId())){
            throw new IllegalArgumentException("Room does not exist");
        }
        try{
            roomRepository.deleteById(command.roomId());
        } catch(Exception e){
            throw new IllegalArgumentException("Error while deleting room: " + e.getMessage());
        }
    }

}
