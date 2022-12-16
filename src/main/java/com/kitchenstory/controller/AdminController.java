package com.kitchenstory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kitchenstory.request.AddFoodRequest;
import com.kitchenstory.service.AdminService;
import com.kitchenstory.utils.ExtractJWT;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

	private AdminService adminService;
	
	@Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PutMapping("/secure/increase/food/quantity")
    public void increaseFoodQuantity(@RequestHeader(value="Authorization") String token,
                                     @RequestParam Long foodId) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        adminService.increaseFoodQuantity(foodId);
    }

    @PutMapping("/secure/decrease/food/quantity")
    public void decreaseFoodQuantity(@RequestHeader(value="Authorization") String token,
                                     @RequestParam Long foodId) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        adminService.decreaseFoodQuantity(foodId);
    }

    @PostMapping("/secure/add/food")
    public void postFood(@RequestHeader(value="Authorization") String token,
                         @RequestBody AddFoodRequest addFoodRequest) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        adminService.postFood(addFoodRequest);
    }

    @DeleteMapping("/secure/delete/food")
    public void deleteFood(@RequestHeader(value="Authorization") String token,
                           @RequestParam Long foodId) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        adminService.deleteFood(foodId);
    }
	
}
