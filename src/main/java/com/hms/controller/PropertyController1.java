package com.hms.controller;

import com.hms.entity.Property;
import com.hms.service.PropertyService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/properties")
public class PropertyController1 {
    @Autowired
    private PropertyService1 propertyService1;

    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody Map<String, Object> propertyData) {
        Property savedProperty = propertyService1.saveProperty(propertyData);
        return ResponseEntity.ok(savedProperty);
    }
}
