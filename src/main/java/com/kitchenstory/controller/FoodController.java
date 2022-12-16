package com.kitchenstory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kitchenstory.entity.Food;
import com.kitchenstory.service.FoodService;
import com.kitchenstory.utils.ExtractJWT;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/foods")
public class FoodController {

	
	private FoodService foodService;
	
	
	@Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }
	
	@GetMapping("/secure/ischeckedout/byuser")
    public Boolean checkoutBookByUser(@RequestHeader(value = "Authorization") String token,
                                      @RequestParam Long foodId) {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return foodService.checkoutFoodByUser(userEmail, foodId);
    }
	
	@PutMapping("/secure/checkout")
    public Food checkoutFood (@RequestHeader(value = "Authorization") String token,
                              @RequestParam Long foodId) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return foodService.checkoutFood(userEmail, foodId);
    }
	
	@GetMapping("/secure/currentorder/count")
    public int currentOrderCount(@RequestHeader(value = "Authorization") String token) {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return foodService.currentLoansCount(userEmail);
    }
	
}
