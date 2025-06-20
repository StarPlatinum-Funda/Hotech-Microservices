package com.github.hotech.rooms.domain.model.aggregates;

import com.github.hotech.rooms.domain.model.commands.CreateRoomCommand;
import com.github.hotech.rooms.domain.model.commands.UpdateRoomCommand;
import com.github.hotech.rooms.domain.model.valueobjects.RoomStatus;
import com.github.hotech.rooms.domain.model.valueobjects.RoomType;
import com.github.hotech.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
public class Room extends AuditableAbstractAggregateRoot<Room> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int roomNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomType type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    @Column(nullable = false)
    private Long userId;

    public Room(String roomType, int roomNumber, Long userId) {
        this.roomNumber = roomNumber;
        this.type = RoomType.valueOf(roomType);
        this.status = RoomStatus.VACANT;
        this.userId = userId;
    }

    public Room(CreateRoomCommand command) {
        this.roomNumber = command.roomNumber();
        this.type = RoomType.valueOf(command.type());
        this.status = RoomStatus.VACANT;
        this.userId = command.userId();
    }

    public Room() {
    }

    public Room updateInformation(String roomType, String roomStatus, int roomNumber, Long userId){
        this.roomNumber = roomNumber;
        this.type = RoomType.valueOf(roomType);
        this.status = RoomStatus.valueOf(roomStatus);
        this.userId = userId;
        return this;
    }

    public Room updateInformation(UpdateRoomCommand command){
        this.roomNumber = command.roomNumber();
        this.type = RoomType.valueOf(command.type());
        this.status = RoomStatus.valueOf(command.status());
        this.userId = command.userId();
        return  this;
    }

    /*public void updateGuestName(String firstName, String lastName) {
        this.guest = new GuestName(firstName, lastName);
    }*/

    /*public String getGuestFullName() {
        return guest.getFullName();
    }*/

    public String getType() {
        return this.type.name().toLowerCase();
    }

    public String getStatus() {
        return this.status.name().toLowerCase();
    }

    /*public String getReservationDate() {return reservation.getFullReservationDate();}*/

}
