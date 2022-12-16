package com.kitchenstory.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kitchenstory.entity.Checkout;
import com.kitchenstory.entity.Food;
import com.kitchenstory.repository.CheckoutRepository;
import com.kitchenstory.repository.FoodRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@Service
@Transactional
public class FoodService {

	private FoodRepository foodRepository;
	
	private CheckoutRepository checkoutRepository;
	
	public FoodService(FoodRepository foodRepository, CheckoutRepository checkoutRepository) {
		this.foodRepository = foodRepository;
		this.checkoutRepository = checkoutRepository;
	}
	
	public Food checkoutFood (String userEmail, Long foodId) throws Exception {

        Optional<Food> food = foodRepository.findById(foodId);

        Checkout validateCheckout = checkoutRepository.findByUserEmailAndFoodId(userEmail, foodId);

        if (!food.isPresent() || validateCheckout != null || food.get().getStockAvailable() <= 0) {
            throw new Exception("We don't have any of that food left.");
        }

        food.get().setStockAvailable(food.get().getStockAvailable() - 1);
        foodRepository.save(food.get());

        Checkout checkout = new Checkout(
            userEmail,
            LocalDate.now().toString(),
            LocalDate.now().plusDays(7).toString(),
            food.get().getId()
        );

        checkoutRepository.save(checkout);

        return food.get();
    }
	
	
	public Boolean checkoutFoodByUser(String userEmail, Long foodId) {
        Checkout validateCheckout = checkoutRepository.findByUserEmailAndFoodId(userEmail, foodId);
        if (validateCheckout != null) {
            return true;
        } else {
            return false;
        }
    }
	
	public int currentLoansCount(String userEmail) {
        return checkoutRepository.findBooksByUserEmail(userEmail).size();
    }
	
	
}
