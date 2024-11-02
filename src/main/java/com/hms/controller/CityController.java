package com.hms.controller;

import com.hms.entity.City;
import com.hms.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/city")
public class CityController {

    @Autowired
    private CityService cityService;
    //get all the cities
    @GetMapping("/getAllCities")
    public List<City> getAllCities(){
        return cityService.getAllCities();
    }
    //add city
    @PostMapping("/addCity")
    public City addCity(
            @RequestBody City city
    ){
        return cityService.addCity(city);
    }
    //get city by id
    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Long id){
        City city = cityService.getCityById(id);
        if (city != null){
            return ResponseEntity.ok(city);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    //updating the city by id
    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity
            (@PathVariable Long id,
             @RequestBody City cityDetails
             ){
        City updatedCity= cityService.updateCity(id, cityDetails);
        if (updatedCity!=null){
            return ResponseEntity.ok(updatedCity);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    //delete city by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(
            @PathVariable Long id
    ){
        boolean isDeleted = cityService.deleteCity(id);
        if(isDeleted){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}