package com.hms.controller;

import com.hms.entity.Country;
import com.hms.entity.Location;
import com.hms.entity.Property;
import com.hms.entity.State;
import com.hms.repository.CountryRepository;
import com.hms.repository.LocationRepository;
import com.hms.repository.PropertyRepository;
import com.hms.repository.StateRepository;
import com.hms.service.PropertyService;
import com.hms.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {

    private PropertyService service;
    private LocationRepository locationRepository;
    private CountryRepository countryRepository;
    private StateRepository stateRepository;
    private PropertyRepository repository;
    private ReviewService reviewService;

    public PropertyController(PropertyService service, LocationRepository locationRepository, CountryRepository countryRepository, StateRepository stateRepository, PropertyRepository repository, ReviewService reviewService) {
        this.service = service;
        this.locationRepository = locationRepository;
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
        this.repository = repository;
        this.reviewService = reviewService;
    }

    //for adding properties
    @PostMapping
    public ResponseEntity<Property> addProperty(
            @RequestParam Long locationId,
            @RequestParam Long countryId,
            @RequestParam Long stateId,
            @RequestBody Property property
    ){
        Location location = locationRepository.findById(locationId).get();
        Country country = countryRepository.findById(countryId).get();
        State state = stateRepository.findById(stateId).get();
        property.setCountry(country);
        property.setLocation(location);
        property.setState(state);

        Property added = service.addProperties(property);

        return new ResponseEntity<>(added, HttpStatus.CREATED);

    }

    //method to delete properties by proerties id
    @DeleteMapping("/{propertyId}")
    public ResponseEntity<?> deleteProperty(@PathVariable Long propertyId){

        if (!repository.existsById(propertyId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found");
        }
        //delete all reviews related to this property id
        reviewService.deleteReviewsByPropertyId(propertyId);

        //delete all properties by propertyId
        service.deletePropertyById(propertyId);

        return new ResponseEntity<>("PropertyDeleted",HttpStatus.OK);
    }

    @GetMapping("/searchHotels")
    public List<Property> searchHotels(@RequestParam String name){

        List<Property> list = service.searchHotelByLocation(name);

        return list;
    }

}
