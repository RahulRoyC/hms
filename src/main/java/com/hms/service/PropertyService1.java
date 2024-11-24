package com.hms.service;

import com.hms.entity.Country;
import com.hms.entity.Location;
import com.hms.entity.Property;
import com.hms.entity.State;
import com.hms.repository.CountryRepository;
import com.hms.repository.LocationRepository;
import com.hms.repository.PropertyRepository;
import com.hms.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PropertyService1 {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private LocationRepository locationRepository;

    public Property saveProperty(Map<String, Object> propertyData) {
        Map<String, Object> countryData = (Map<String, Object>) propertyData.get("country");
        Map<String, Object> stateData = (Map<String, Object>) propertyData.get("state");
        Map<String, Object> locationData = (Map<String, Object>) propertyData.get("location");

        Country country = findOrCreateCountry(countryData);
        State state = findOrCreateState(stateData);
        Location location = findOrCreateLocation(locationData);

        Property property = new Property();
        property.setName((String) propertyData.get("name"));
        property.setNoOfGuest((Integer) propertyData.get("noOfGuest"));
        property.setNoOfBedrooms((Integer) propertyData.get("noOfBedrooms"));
        property.setNoOfBeds((String) propertyData.get("noOfBeds"));
        property.setNoOfBathrooms((String) propertyData.get("noOfBathrooms"));
        property.setCountry(country);
        property.setState(state);
        property.setLocation(location);

        return propertyRepository.save(property);
    }


    private Country findOrCreateCountry(Map<String, Object> countryData) {
        Long id = Long.parseLong((String) countryData.get("id"));
        return countryRepository.findById(id).orElseGet(() -> {
            Country newCountry = new Country();
            newCountry.setName((String) countryData.get("name"));
            return countryRepository.save(newCountry);
        });
    }

    private State findOrCreateState(Map<String, Object> stateData) {
        Long id = Long.parseLong((String) stateData.get("id"));
        return stateRepository.findById(id).orElseGet(() -> {
            State newState = new State();
            newState.setName((String) stateData.get("name"));
            return stateRepository.save(newState);
        });
    }

    private Location findOrCreateLocation(Map<String, Object> locationData) {
        Long id = Long.parseLong((String) locationData.get("id"));
        return locationRepository.findById(id).orElseGet(() -> {
            Location newLocation = new Location();
            newLocation.setName((String) locationData.get("name"));
            return locationRepository.save(newLocation);
        });
    }
}

