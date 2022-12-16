package com.kitchenstory.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.kitchenstory.entity.Food;

@CrossOrigin("http://localhost:4200")
public interface FoodRepository extends JpaRepository<Food, Long> {

	Page<Food> findByShortNameContaining(@RequestParam("shortName") String shortName, Pageable pageable);
	
	Page<Food> findByCuisine(@RequestParam("cuisine") String cuisine, Pageable pageable);
	
}

