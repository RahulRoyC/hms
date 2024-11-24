package com.hms.repository;

import com.hms.entity.AppUser;
import com.hms.entity.Property;
import com.hms.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

        //deleteByPropertyId
        @Transactional
        void deleteByPropertyId(Long id);

        public List<Review> findByAppUser(AppUser user);


        public boolean existsByAppUserAndProperty(AppUser appUser, Property property);
}

