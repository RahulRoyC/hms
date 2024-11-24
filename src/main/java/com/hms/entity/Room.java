package com.hms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "count", nullable = false)
    private Integer count;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Version
    private Long version;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

}