package com.github.hotechbackend.task.domain.model.queries;

import com.github.hotechbackend.task.domain.model.valueobjects.RoomIdentifier;

public record GetAllTaskInWeekForRoomQuery(
        RoomIdentifier roomIdentifier
) {
}
