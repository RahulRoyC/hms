package com.hms.repository;

import com.hms.entity.Property;
import com.hms.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findByTypeAndPropertyAndDate(String type, Property property, LocalDate date);
}