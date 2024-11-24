package com.hms.service;

import com.hms.entity.Images;
import com.hms.entity.Property;
import com.hms.repository.ImagesRepository;
import com.hms.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PropertyService {

    private PropertyRepository propertyRepository;
    private final ImagesRepository imagesRepository;

    public PropertyService(PropertyRepository propertyRepository, CountryService countryService, StateService stateService, LocationService locationService,
                           ImagesRepository imagesRepository) {
        this.propertyRepository = propertyRepository;
        this.imagesRepository = imagesRepository;
    }


    public Property addProperties(Property property) {

        Property save = propertyRepository.save(property);

        return save;

    }

    @Transactional
    public void deletePropertyById(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }

    @Transactional
    public void deletePropertiesByCountryId(Long countryId) {
        propertyRepository.deleteByCountryId(countryId);
    }

    @Transactional
    public void deletePropertiesByStateId(Long stateId) {
        propertyRepository.deleteByStateId(stateId);
        System.out.println("Deleted");
    }

    @Transactional
    public void deletePropertiesByLocationId(Long locationId) {
        propertyRepository.deleteByLocationId(locationId);
    }

    public List<Property> searchHotelByLocation(String name) {

        List<Property> list = propertyRepository.searchHotels(name);

        return list;

    }

    public Property getProperty(Long id){
            return  propertyRepository.findById(id).get();
    }

    public List<Images> getAllImages(Property property) {
        List<Images> imagesList = imagesRepository.findByProperty(property);
        return imagesList;
    }
}
