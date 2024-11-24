package com.hms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "no_of_guest", nullable = false)
    private Integer noOfGuest;

    @Column(name = "no_of_bedrooms", nullable = false)
    private Integer noOfBedrooms;

    @Column(name = "no_of_beds", nullable = false)
    private String noOfBeds;

    @Column(name = "no_of_bathrooms", nullable = false)
    private String noOfBathrooms;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

}