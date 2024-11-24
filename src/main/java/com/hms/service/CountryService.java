package com.hms.service;

import com.hms.entity.Country;
import com.hms.repository.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CountryService {


    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country addCountries(Country country) {

        Country save = countryRepository.save(country);

        return save;
    }

    @Transactional
    public void deleteCountryById(Long countryId) {
        countryRepository.deleteById(countryId);
    }

}
