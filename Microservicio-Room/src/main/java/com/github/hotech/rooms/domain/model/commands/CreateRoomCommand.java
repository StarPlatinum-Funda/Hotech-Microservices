package com.github.hotech.rooms.domain.model.commands;

import java.util.Date;

public record CreateRoomCommand(int roomNumber, String type, Long userId) {
}
