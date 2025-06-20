package com.github.hotech.rooms.interfaces.rest.resources;

import java.util.Date;

public record CreateRoomResource(
        int roomNumber,
        String type,
        Long userId
) {
}
