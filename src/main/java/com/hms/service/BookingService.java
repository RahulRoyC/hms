package com.hms.service;

import com.hms.entity.AppUser;
import com.hms.entity.Booking;
import com.hms.entity.Property;
import com.hms.entity.Room;
import com.hms.repository.BookingRepository;
import com.hms.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    private BookingRepository bookingRepository;
    private PropertyService propertyService;
    private PDFService pdfService;
    private  RoomRepository roomRepository;
    private SMSService smsService;



    public BookingService(BookingRepository bookingRepository, PropertyService propertyService, PDFService pdfService, RoomRepository roomRepository, SMSService smsService) {
        this.bookingRepository = bookingRepository;
        this.propertyService = propertyService;
        this.pdfService = pdfService;
        this.roomRepository = roomRepository;
        this.smsService = smsService;
    }


    public String createBooking(Long propertyId, Booking booking, String type, AppUser appUser) {

        Property property = propertyService.getProperty(propertyId);

        List<LocalDate> dates = getDatesBetweenCheckInAndCheckOut(booking.getCheckInDate(), booking.getCheckOutDate());
        List<Room> rooms = new ArrayList<>();
        Room room = null;

        // find available rooms for the given type and dates
        for(LocalDate date : dates) {
            room = roomRepository.findByTypeAndPropertyAndDate(type, property, date);
            if (room.getCount() == 0) {
                return "no rooms are available";
            }
            rooms.add(room);
        }

        Double totalPrice = rooms.stream()
                .map(Room::getPrice)
                .reduce(0.0, Double::sum);
        Double gstAmount = (totalPrice * 18) / 100;
        Double finalPrice = totalPrice + gstAmount;

        booking.setTotalPrice(finalPrice);
        booking.setType(type);
        booking.setProperty(property);
        booking.setAppUser(appUser);

        Booking save = bookingRepository.save(booking);

        if(save!=null){
            for (Room r : rooms) {
                r.setCount(r.getCount() - 1);
                roomRepository.save(r);
            }
        }

        pdfService.generateBookingPDF(booking);
        String toPhoneNumber = "+91" + booking.getMobile();
        smsService.sendSMS(toPhoneNumber,"Your booking have been done, your booking number is : "+booking.getId());
        //smsService.sendWhatsAppMessage(booking.getMobile(),"Your booking have been done, your booking number is : "+booking.getId());

        return "Booking generated";

    }

    public List<LocalDate> getDatesBetweenCheckInAndCheckOut(LocalDate checkIn, LocalDate checkOut) {
        // if(checkIn == null && checkOut == null && !checkIn.isAfter(checkOut)) {
        if (checkIn != null && checkOut != null && checkIn.isBefore(checkOut)) {
            return checkIn.datesUntil(checkOut.plusDays(1)).toList();
        } else {
            throw new RuntimeException("Invalid dates");
        }
    }
}
