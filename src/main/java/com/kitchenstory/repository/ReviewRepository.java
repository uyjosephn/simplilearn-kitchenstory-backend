package com.kitchenstory.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.kitchenstory.entity.Review;

@CrossOrigin("http://localhost:4200")
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findByFoodId(@RequestParam("foodId") Long foodId,
                              Pageable pageable);

    Review findByUserEmailAndFoodId(String userEmail, Long foodId);

    @Modifying
    @Query("Delete from Review where food_id in :foodId")
    void deleteAllByFoodId(@Param("foodId") Long foodId);
}
