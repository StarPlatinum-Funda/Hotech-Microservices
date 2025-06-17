package com.github.hotech.rooms.infrastructure.persistence.jpa.repositories;

import com.github.hotech.rooms.domain.model.aggregates.Room;
import com.github.hotech.rooms.domain.model.valueobjects.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<List<Room>> findAllByUserId(Long userId);

    boolean existsByRoomNumber(int roomNumber);
    boolean existsByRoomNumberAndIdIsNot(int roomNumber, Long id);

    Optional<List<Room>> findAllByType(RoomType enumOption);
}
