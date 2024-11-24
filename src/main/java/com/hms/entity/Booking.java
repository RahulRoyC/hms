package com.hms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "no_of_guest", nullable = false)
    private Integer noOfGuest;

    @Column(name = "guest_name", nullable = false)
    private String guestName;

    @Column(name = "mobile", nullable = false)
    private String mobile;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "email_id", nullable = false)
    private String emailId;

    @Column(name = "check_in_date", nullable = false)
    private LocalDate checkInDate;

    @Column(name = "check_out_date", nullable = false)
    private LocalDate checkOutDate;

    @Column(name = "type", nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

}