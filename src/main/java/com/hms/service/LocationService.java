package com.hms.service;

import com.hms.entity.Location;
import com.hms.repository.LocationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationService {

    private LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location addLocations(Location location) {

        Location save = locationRepository.save(location);

        return save;

    }

    @Transactional
    public void deleteLocationById(Long locationId) {
    locationRepository.deleteById(locationId);

    }
}
