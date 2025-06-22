package com.github.hotechbackend.task.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class RoomIdentifier {
    private Long roomId;

    public RoomIdentifier() {}

    public RoomIdentifier(Long roomId) {
        this.roomId = roomId;
    }

    public Long getRoomId() {
        return roomId;
    }
}

