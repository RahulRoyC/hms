package com.hms.repository;

import com.hms.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Transactional
    void deleteByCountryId(Long countryId);

    @Transactional
    void deleteByStateId(Long stateId);

    @Transactional
    void deleteByLocationId(Long locationId);

    @Query("select p from Property p JOIN p.location l JOIN p.country c JOIN p.state s where l.name =:name or c.name=:name or s.name=:name")
    List<Property> searchHotels(@Param("name") String name);

}


