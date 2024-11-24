package com.hms.service;

import com.hms.entity.Property;
import com.hms.entity.Room;
import com.hms.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room addRoom(Property property, Room room) {
        room.setProperty(property);
        return roomRepository.save(room);
    }
}
