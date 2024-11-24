package com.hms.controller;

import com.hms.entity.Property;
import com.hms.entity.Room;
import com.hms.service.PropertyService;
import com.hms.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private RoomService roomService;
    private PropertyService propertyService;

    public RoomController(RoomService roomService, PropertyService propertyService) {
        this.roomService = roomService;
        this.propertyService = propertyService;
    }

    @PostMapping("/addrooms")
    public ResponseEntity addRoom(@RequestParam Long propertyId,@RequestBody Room room){
        Property property = propertyService.getProperty(propertyId);

        Room addedRoom = roomService.addRoom(property,room);

        return new ResponseEntity<>(room,HttpStatus.OK);
    }

}
