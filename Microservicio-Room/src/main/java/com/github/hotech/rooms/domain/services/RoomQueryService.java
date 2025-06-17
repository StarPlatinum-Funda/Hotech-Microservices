package com.github.hotech.rooms.domain.services;

import com.github.hotech.rooms.domain.model.aggregates.Room;
import com.github.hotech.rooms.domain.model.queries.GetAllRoomsQuery;
import com.github.hotech.rooms.domain.model.queries.GetRoomByIdQuery;
import com.github.hotech.rooms.domain.model.queries.GetRoomByTypeQuery;
import com.github.hotech.rooms.domain.model.queries.GetRoomsByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface RoomQueryService {
    Optional<Room> handle(GetRoomByIdQuery query);
    List<Room> handle(GetRoomByTypeQuery query);
    List<Room> handle(GetRoomsByUserIdQuery query);
    List<Room> handle(GetAllRoomsQuery query);
}
