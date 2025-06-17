package com.github.hotech.rooms.interfaces.rest.resources;

public record RoomResource(
        Long id,
        int roomNumber,
        String roomType,
        String roomStatus,
        Long userId
) {
}
