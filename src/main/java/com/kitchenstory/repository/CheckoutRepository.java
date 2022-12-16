package com.kitchenstory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.kitchenstory.entity.Checkout;

import java.util.List;

@CrossOrigin("http://localhost:4200")
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

    Checkout findByUserEmailAndFoodId(String userEmail, Long foodId);

    List<Checkout> findBooksByUserEmail(String userEmail);

    @Modifying
    @Query("delete from Checkout where food_id in :food_id")
    void deleteAllByFoodId(@Param("food_id") Long foodId);
}