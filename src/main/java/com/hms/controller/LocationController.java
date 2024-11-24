package com.hms.controller;

import com.hms.entity.Location;
import com.hms.repository.LocationRepository;
import com.hms.service.LocationService;
import com.hms.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {

    private LocationService locationService;
    private PropertyService propertyService;
    private LocationRepository repository;

    public LocationController(LocationService locationService, PropertyService propertyService, LocationRepository repository) {
        this.locationService = locationService;
        this.propertyService = propertyService;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Location> addLocation(@RequestBody Location location){

        Location addedLocations = locationService.addLocations(location);

        return new ResponseEntity<>(addedLocations, HttpStatus.CREATED);


    }

    @DeleteMapping("/{loactionId}")
    public ResponseEntity<String> deleteLocation(@PathVariable Long locationId){

//        if(!repository.existsById(locationId)){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found");
//        }

        //delete all properties related to this location id
        propertyService.deletePropertiesByLocationId(locationId);

        //delete location itself
        locationService.deleteLocationById(locationId);

        return new ResponseEntity<>("Location and related properties deleted successfully",HttpStatus.OK);
    }
}
