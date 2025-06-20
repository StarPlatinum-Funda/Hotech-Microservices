package com.github.hotech.rooms.interfaces.rest.transform;

import com.github.hotech.rooms.domain.model.commands.UpdateRoomCommand;
import com.github.hotech.rooms.interfaces.rest.resources.UpdateRoomResource;

public class UpdateRoomCommandFromResourceAssembler {
    public static UpdateRoomCommand toCommandFromResource(Long roomId, UpdateRoomResource resource){
        return new UpdateRoomCommand(roomId, resource.roomNumber(), resource.type(), resource.status(), resource.userId());
    }
}
