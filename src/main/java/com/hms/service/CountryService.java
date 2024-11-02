package com.hms.service;

import com.hms.entity.Country;
import com.hms.repo.CityRepository;
import com.hms.repo.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CityRepository cityRepository;

    @Transactional
    public Country addCountry(Country country){
        return countryRepository.save(country);
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country getCountryById(Long id) {
        return countryRepository.findById(id).orElse(null);
    }

    @Transactional
    public Country updateCountry(Long id, Country countryDetails) {
        Optional<Country> countryOptional= countryRepository.findById(id);
        if(countryOptional.isPresent()){
            Country country = countryOptional.get();
            country.setName(country.getName());
            return countryRepository.save(country);
        }else {
            return null;
        }
    }

    @Transactional
    public boolean deleteCountry(Long id) {
        Optional<Country> countryOptional = countryRepository.findById(id);
        if (countryOptional.isPresent()){
          countryRepository.delete(countryOptional.get());
            return true;
        }else{
            return false;
        }
    }
}
