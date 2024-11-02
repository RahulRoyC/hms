package com.hms.service;

import com.hms.entity.Property;
import com.hms.repo.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    //get all the properties
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();// call the repo layer to get all properties

    }

    //get all the properties by ID
    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id).orElse(null);
    }

    @Transactional
    //create property
    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    @Transactional
    //Update the property details
    public Property updateProperty(Long id, Property propertyDetails ){
        Optional<Property> propertyOptional = propertyRepository.findById(id);
        if (propertyOptional.isPresent()){
            Property property = propertyOptional.get();
            property.setName(propertyDetails.getName());
            property.setNo_of_guests(propertyDetails.getNo_of_guests());
            property.setNo_of_bedrooms(propertyDetails.getNo_of_bedrooms());
            property.setNo_of_bathrooms(propertyDetails.getNo_of_bathrooms());
            property.setNo_of_beds(propertyDetails.getNo_of_beds());
            property.setCountry(property.getCountry());
            property.setCity(property.getCity());
            return propertyRepository.save(property);
        }else {
            return null;
        }
    }

    @Transactional
    //deleteProperty
    public boolean deleteProperty(Long id){
        Optional<Property> propertyOptional = propertyRepository.findById(id);
        if (propertyOptional.isPresent()){
            Property property = propertyOptional.get();
             propertyRepository.delete(property);
             return true;
        }else {
            return false;
        }
    }
}
