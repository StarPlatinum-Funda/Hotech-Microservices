package com.github.hotech.rooms.application.internal.queryservices;

import com.github.hotech.rooms.domain.model.aggregates.Room;
import com.github.hotech.rooms.domain.model.queries.GetAllRoomsQuery;
import com.github.hotech.rooms.domain.model.queries.GetRoomByIdQuery;
import com.github.hotech.rooms.domain.model.queries.GetRoomByTypeQuery;
import com.github.hotech.rooms.domain.model.queries.GetRoomsByUserIdQuery;
import com.github.hotech.rooms.domain.services.RoomQueryService;
import com.github.hotech.rooms.domain.model.valueobjects.RoomType;
import com.github.hotech.rooms.infrastructure.persistence.jpa.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomQueryServiceImpl implements RoomQueryService {
    private final RoomRepository roomRepository;

    public RoomQueryServiceImpl(RoomRepository roomRepository){
        this.roomRepository=roomRepository;
    }

    @Override
    public Optional<Room> handle(GetRoomByIdQuery query){
        return roomRepository.findById(query.id());
    }

    @Override
    public List<Room> handle(GetRoomByTypeQuery query){
        var enumOption = RoomType.valueOf(query.roomType());
        return roomRepository.findAllByType(enumOption).get();
    }

    @Override
    public List<Room> handle(GetRoomsByUserIdQuery query) {
        return roomRepository.findAllByUserId(query.userId()).get();
    }

    @Override
    public List<Room> handle(GetAllRoomsQuery query){
        return roomRepository.findAll();
    }
}
