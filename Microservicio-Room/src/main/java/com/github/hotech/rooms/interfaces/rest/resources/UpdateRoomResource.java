package com.github.hotech.rooms.interfaces.rest.resources;

import java.util.Date;

public record UpdateRoomResource(int roomNumber, String type, String status, Long userId) {
}
