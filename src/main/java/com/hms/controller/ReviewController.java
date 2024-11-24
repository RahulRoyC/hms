package com.hms.controller;

import com.hms.entity.AppUser;
import com.hms.entity.Property;
import com.hms.entity.Review;
import com.hms.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
//http:localhost:8080/api/v1/review/add
    @PostMapping("/add")
    public ResponseEntity<?> write(
            @RequestParam Long id,
            @RequestBody Review review,
            @AuthenticationPrincipal AppUser appUser)
    {

        Property property = reviewService.getProperty(id);

        if (reviewService.hasUserWrittenReview(appUser,property)){
            return new ResponseEntity<>("User has already written a review for this property", HttpStatus.BAD_REQUEST);
        }
        Review savedReview = reviewService.writeReview(id, review, appUser);

        return new ResponseEntity<>(savedReview, HttpStatus.OK);

    }


    //http:localhost:8080/api/v1/review/getusers

    @GetMapping("/getusers")
    public ResponseEntity<?> getUserReviews(@AuthenticationPrincipal AppUser user){

        List<Review> userReviews = reviewService.getUserReviews(user);

        return new ResponseEntity<>(userReviews, HttpStatus.OK);
    }


    //http://ocalhost:8080/api/v1/review/2
    @PutMapping("/{rId}")
    public ResponseEntity<?> updateReview(@PathVariable Long rId, @RequestBody Review updatedReview,@AuthenticationPrincipal AppUser appUser){

        Review existingReview  = reviewService.getReview(rId);
        if(existingReview  == null){
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }else{
            existingReview.setRating(updatedReview.getRating());
            existingReview.setDescription(updatedReview.getDescription());
            reviewService.updateReview(existingReview);
            return new ResponseEntity<>("Review updated", HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteReview(@RequestParam Long id){

        Review review = reviewService.getReview(id);
        if(review  == null){
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }else{
            reviewService.deleteReviews(id);
            return new ResponseEntity<>("Review deleted", HttpStatus.I_AM_A_TEAPOT);
        }


    }
}
