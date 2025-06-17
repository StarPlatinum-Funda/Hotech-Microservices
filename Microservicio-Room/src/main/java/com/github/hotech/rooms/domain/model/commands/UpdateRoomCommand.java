package com.github.hotech.rooms.domain.model.commands;

import java.util.Date;

public record UpdateRoomCommand(Long id, int roomNumber, String type, String status, Long userId) {
}
