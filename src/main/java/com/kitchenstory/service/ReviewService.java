package com.kitchenstory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kitchenstory.entity.Review;
import com.kitchenstory.repository.ReviewRepository;
import com.kitchenstory.request.ReviewRequest;

import java.sql.Date;
import java.time.LocalDate;

@Service
@Transactional
public class ReviewService {

	private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void postReview(String userEmail, ReviewRequest reviewRequest) throws Exception {
        Review validateReview = reviewRepository.findByUserEmailAndFoodId(userEmail, reviewRequest.getFoodId());
        if (validateReview != null) {
            throw new Exception("Review already created");
        }

        Review review = new Review();
        review.setFoodId(reviewRequest.getFoodId());
        review.setRating(reviewRequest.getRating());
        review.setUserEmail(userEmail);
        if (reviewRequest.getReviewDescription().isPresent()) {
            review.setReviewDescription(reviewRequest.getReviewDescription().map(
                    Object::toString
            ).orElse(null));
        }
        review.setDate(Date.valueOf(LocalDate.now()));
        reviewRepository.save(review);
    }

    public Boolean userReviewListed(String userEmail, Long foodId) {
        Review validateReview = reviewRepository.findByUserEmailAndFoodId(userEmail, foodId);
        if (validateReview != null) {
            return true;
        } else {
            return false;
        }
    }
    
}
