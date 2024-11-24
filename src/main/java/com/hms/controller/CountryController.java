package com.hms.controller;

import com.hms.entity.AppUser;
import com.hms.entity.Country;
import com.hms.repository.CountryRepository;
import com.hms.service.CountryService;
import com.hms.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1/country")
public class CountryController {

    // http://localhost:8080/api/v1/country/addcountry

    @PostMapping("/add")
    public AppUser addCount(@AuthenticationPrincipal AppUser appUser){

        return appUser;
    }


    private CountryService countryService;
    private PropertyService propertyService;
    private CountryRepository repository;

    public CountryController(CountryService countryService, PropertyService propertyService, CountryRepository repository) {
        this.countryService = countryService;
        this.propertyService = propertyService;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Country> addCountry(
            @RequestBody Country country
    ){
        Country addedCountries = countryService.addCountries(country);

        return new ResponseEntity<>(addedCountries, HttpStatus.CREATED);
    }

    // Method to delete country by ID and its related properties
    @DeleteMapping("/{countryId}")
    public ResponseEntity<String> deleteCountry(@PathVariable Long countryId) {

        if(!repository.existsById(countryId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found");
        }

        

        // Delete all properties related to the country
        propertyService.deletePropertiesByCountryId(countryId);

        // Delete the country itself
        countryService.deleteCountryById(countryId);

        return new ResponseEntity<>("Country and related properties deleted successfully",HttpStatus.OK);
    }
}
