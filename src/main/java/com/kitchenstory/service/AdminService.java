package com.kitchenstory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kitchenstory.entity.Food;
import com.kitchenstory.repository.CheckoutRepository;
import com.kitchenstory.repository.FoodRepository;
import com.kitchenstory.repository.ReviewRepository;
import com.kitchenstory.request.AddFoodRequest;

import java.util.Optional;

@Service
@Transactional
public class AdminService {

	private FoodRepository foodRepository;
    private ReviewRepository reviewRepository;
    private CheckoutRepository checkoutRepository;
    
    
    @Autowired
    public AdminService (FoodRepository foodRepository,
                         ReviewRepository reviewRepository,
                         CheckoutRepository checkoutRepository) {
        this.foodRepository = foodRepository;
        this.reviewRepository = reviewRepository;
        this.checkoutRepository = checkoutRepository;
    }

    public void increaseFoodQuantity(Long foodId) throws Exception {

        Optional<Food> food = foodRepository.findById(foodId);

        if (!food.isPresent()) {
            throw new Exception("Food not found");
        }

        food.get().setStockAvailable(food.get().getStockAvailable() + 1);
        food.get().setStock(food.get().getStock() + 1);

        foodRepository.save(food.get());
    }

    public void decreaseFoodQuantity(Long foodId) throws Exception {

        Optional<Food> food = foodRepository.findById(foodId);

        if (!food.isPresent() || food.get().getStockAvailable() <= 0 || food.get().getStock() <= 0) {
            throw new Exception("Book not found or quantity locked");
        }

        food.get().setStockAvailable(food.get().getStockAvailable() - 1);
        food.get().setStock(food.get().getStock() - 1);

        foodRepository.save(food.get());
    }

    public void postFood(AddFoodRequest addFoodRequest) {
        Food food = new Food();
        food.setShortName(addFoodRequest.getShortName());
        food.setCuisine(addFoodRequest.getCuisine());
        food.setDescription(addFoodRequest.getDescription());
        food.setStock(addFoodRequest.getStock());
        food.setStockAvailable(addFoodRequest.getStock());
        food.setCategory(addFoodRequest.getCategory());
        food.setImg(addFoodRequest.getImg());
        foodRepository.save(food);
    }

    public void deleteFood(Long foodId) throws Exception {

        Optional<Food> food = foodRepository.findById(foodId);

        if (!food.isPresent()) {
            throw new Exception("Food not found");
        }

        foodRepository.delete(food.get());
        checkoutRepository.deleteAllByFoodId(foodId);
        reviewRepository.deleteAllByFoodId(foodId);
    }
    
}
