package com.hms.controller;


import com.hms.entity.Country;
import com.hms.repo.CountryRepository;
import com.hms.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {
//    @PostMapping("/addCountry")
//    public String addCountry(){
//        return "added";
//    }

    @Autowired
    private CountryService countryService;
    @Autowired
    private CountryRepository countryRepository;

    @GetMapping
    public List<Country> getAllCountries(){
        return countryService.getAllCountries();
    }

    @PostMapping("/addCountry")
    public Country addCountry(
            @RequestBody Country country
            ){
        return countryService.addCountry(country);
    }

    //get a country by id
    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Long id){
        Country country = countryService.getCountryById(id);
        if (country!=null){
            return ResponseEntity.ok(country);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    //update country by id
    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry
        (@PathVariable Long id, Country countryDetails) {
        Country updatedCountry
                = countryService.updateCountry(id, countryDetails);
        if (updatedCountry != null) {
            return ResponseEntity.ok(updatedCountry);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //delete country by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id){
        boolean isDeleted = countryService.deleteCountry(id);
        if(isDeleted){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}