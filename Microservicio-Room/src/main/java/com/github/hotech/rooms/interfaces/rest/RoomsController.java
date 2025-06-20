package com.github.hotech.rooms.interfaces.rest;

import com.github.hotech.rooms.domain.model.commands.DeleteRoomCommand;
import com.github.hotech.rooms.domain.model.queries.GetAllRoomsQuery;
import com.github.hotech.rooms.domain.model.queries.GetRoomByIdQuery;
import com.github.hotech.rooms.domain.model.queries.GetRoomByTypeQuery;
import com.github.hotech.rooms.domain.model.queries.GetRoomsByUserIdQuery;
import com.github.hotech.rooms.domain.model.valueobjects.RoomType;
import com.github.hotech.rooms.domain.services.RoomCommandService;
import com.github.hotech.rooms.domain.services.RoomQueryService;
import com.github.hotech.rooms.interfaces.rest.resources.CreateRoomResource;
import com.github.hotech.rooms.interfaces.rest.resources.RoomResource;
import com.github.hotech.rooms.interfaces.rest.resources.UpdateRoomResource;
import com.github.hotech.rooms.interfaces.rest.transform.CreateRoomCommandFromResourceAssembler;
import com.github.hotech.rooms.interfaces.rest.transform.RoomResourceFromEntityAssembler;
import com.github.hotech.rooms.interfaces.rest.transform.UpdateRoomCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/v1/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Rooms", description = "Rooms Management Endpoints")
public class RoomsController {
    private final RoomCommandService roomCommandService;
    private final RoomQueryService roomQueryService;

    public RoomsController(RoomCommandService roomCommandService, RoomQueryService roomQueryService){
        this.roomCommandService = roomCommandService;
        this.roomQueryService = roomQueryService;
    }


    @Operation(summary = "Creates a new room with the given details")
    @PostMapping
    public ResponseEntity<RoomResource> createRoom(@RequestBody CreateRoomResource resource){
        var createRoomCommand = CreateRoomCommandFromResourceAssembler.toCommandFromResource(resource);
        var room = roomCommandService.handle(createRoomCommand);
        if (room.isEmpty()) return ResponseEntity.badRequest().build();
        var roomResource = RoomResourceFromEntityAssembler.toResourceFromEntity(room.get());
        return new ResponseEntity<>(roomResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Returns the room with the given id")
    @GetMapping("/{roomId}")
    public ResponseEntity<RoomResource> getRoomById(@PathVariable Long roomId){
        var getRoomByIdQuery = new GetRoomByIdQuery(roomId);
        var room = roomQueryService.handle(getRoomByIdQuery);
        if (room.isEmpty()) return ResponseEntity.notFound().build();
        var roomResource = RoomResourceFromEntityAssembler.toResourceFromEntity(room.get());
        return ResponseEntity.ok(roomResource);
    }

    @Operation(summary = "Returns all rooms")
    @GetMapping
    public ResponseEntity<List<RoomResource>> getAllRooms(){
        var getAllRoomsQuery = new GetAllRoomsQuery();
        var rooms = roomQueryService.handle(getAllRoomsQuery);
        var roomResources = rooms.stream()
                .map(RoomResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(roomResources);
    }

    @Operation(summary = "Updates a room´s data")
    @PutMapping("/{roomId}")
    public ResponseEntity<RoomResource> updateRoom(@PathVariable Long roomId, @RequestBody UpdateRoomResource updateRoomResource){
        var updateRoomCommand = UpdateRoomCommandFromResourceAssembler.toCommandFromResource(roomId,updateRoomResource);
        var updatedRoom = roomCommandService.handle(updateRoomCommand);
        if(updatedRoom.isEmpty()) return ResponseEntity.badRequest().build();
        var roomResource = RoomResourceFromEntityAssembler.toResourceFromEntity(updatedRoom.get());
        return ResponseEntity.ok(roomResource);
    }

    @Operation(summary = "Deletes the room with the given id")
    @DeleteMapping("/{roomId}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long roomId) {
        var deleteRoomCommand = new DeleteRoomCommand(roomId);
        roomCommandService.handle(deleteRoomCommand);
        return ResponseEntity.ok("Room with given id successfully deleted");
    }

    @Operation(summary = "Returns all rooms asociated with the given id")
    @GetMapping("/{userId}")
    public ResponseEntity<List<RoomResource>> getAllRoomsByUserId(@PathVariable Long userId) {
        if (userId == null || userId <= 0) {
            return ResponseEntity.badRequest().build();
        }

        var query = new GetRoomsByUserIdQuery(userId);
        var rooms = roomQueryService.handle(query);
        var roomResources = rooms.stream()
                .map(RoomResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(roomResources);
    }

    @Operation(summary = "Returns all rooms with the given type")
    @GetMapping("/{type}")
    public ResponseEntity<List<RoomResource>> getAllRoomsByType(@PathVariable String type){

        try {
            RoomType.valueOf(type);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(List.of());
        }

        var query = new GetRoomByTypeQuery(type);
        var rooms = roomQueryService.handle(query);
        var roomResources = rooms.stream()
                .map(RoomResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(roomResources);
    }
}
