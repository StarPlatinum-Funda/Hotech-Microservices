package com.github.hotech.rooms.domain.services;

import com.github.hotech.rooms.domain.model.aggregates.Room;
import com.github.hotech.rooms.domain.model.commands.CreateRoomCommand;
import com.github.hotech.rooms.domain.model.commands.DeleteRoomCommand;
import com.github.hotech.rooms.domain.model.commands.UpdateRoomCommand;

import java.util.Optional;

public interface RoomCommandService {
    Optional<Room> handle(CreateRoomCommand command);
    Optional<Room> handle(UpdateRoomCommand command);
    void handle(DeleteRoomCommand command);
}
