package com.hms.controller;

import com.hms.entity.AppUser;
import com.hms.entity.Booking;
import com.hms.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/booking")
public class BookingController {
    
    private BookingService bookingService;


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    @PostMapping("/create")
    public ResponseEntity<?> createBooking(
            @RequestParam Long propertyId,
            @RequestBody Booking booking,
            @RequestParam String type,
            @AuthenticationPrincipal AppUser appUser
            ){

       String bookingCreated = bookingService.createBooking(propertyId, booking, type,appUser);

        return new ResponseEntity<>(bookingCreated, HttpStatus.OK);

    }
}
