package com.hms.service;

import com.hms.entity.AppUser;
import com.hms.entity.Property;
import com.hms.entity.Review;
import com.hms.repository.PropertyRepository;
import com.hms.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;

    public ReviewService(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }

    //public Boolean checkExistingReview()

    public Review writeReview(Long id, Review review, AppUser appUser){
        Property property = propertyRepository.findById(id).get();
        review.setProperty(property);
        review.setAppUser(appUser);

        Review saved = reviewRepository.save(review);

        return saved;

    }

    public List<Review> getUserReviews(AppUser user) {
        List<Review> byAppUser = reviewRepository.findByAppUser(user);
        return byAppUser;
    }

    public boolean hasUserWrittenReview(AppUser appUser, Property property) {
        return reviewRepository.existsByAppUserAndProperty(appUser, property);
    }

    public Property getProperty(Long id) {
        return propertyRepository.findById(id).get();
    }

    public Review updateReview(Review review) {
        Review saved = reviewRepository.save(review);
        return saved;
    }

    public Review getReview(Long rId) {
        return reviewRepository.findById(rId).get();
    }

    public void deleteReviews(Long id) {
        reviewRepository.deleteById(id);
    }
    @Transactional
    public void deleteReviewsByPropertyId(Long propertyId) {
        reviewRepository.deleteByPropertyId(propertyId);
    }
}
