package com.hms.service;

import com.hms.entity.City;
import com.hms.repo.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Transactional
    public City addCity(City city) {
        return cityRepository.save(city);
    }

    public City getCityById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    @Transactional
    public City updateCity(Long id, City cityDetails) {
        Optional<City> cityOptional = cityRepository.findById(id);
        if (cityOptional.isPresent()) {
            City city = cityOptional.get();
            city.setName(city.getName());
            return cityRepository.save(city);
        } else {
            return null;
        }
    }

    @Transactional
    public boolean deleteCity(Long id) {
        Optional<City> cityOptional = cityRepository.findById(id);
        if (cityOptional.isPresent()) {
            cityRepository.delete(cityOptional.get());
            return true;
        } else {
            return false;
        }
    }
}