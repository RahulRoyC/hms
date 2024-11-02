package com.hms.controller;

import com.hms.entity.Property;
import com.hms.repo.PropertyRepository;
import com.hms.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController//takes the HTTP request
@RequestMapping("/api/v1/properties")
public class PropertyController {

    @Autowired//to call the service layer
    private PropertyService propertyService;

    @Autowired
    private PropertyRepository propertyRepository;

    @GetMapping// for getting the list of properties
    public List<Property> getAllProperties(){
        return propertyService.getAllProperties();//call the Service layer
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id){
        Property property = propertyService.getPropertyById(id);
        if(property != null){
            return ResponseEntity.ok(property);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    //Create Property
    @PostMapping
    public Property createProperty(
            @RequestBody Property property
    ){
         return propertyService.createProperty(property);
    }

    //Update Property By Id
    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(
            @PathVariable Long id, @RequestBody Property propertyDetails
    ){
        Property propertyUpdated = propertyService.updateProperty(id,propertyDetails);
        if (propertyUpdated != null){
            return ResponseEntity.ok(propertyUpdated);
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    //Delete property by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropertyById(@PathVariable Long id){
       boolean deleted= propertyService.deleteProperty(id);
       if(deleted){
           return  ResponseEntity.noContent().build();
       }else {
           return ResponseEntity.notFound().build();
       }
    }
}