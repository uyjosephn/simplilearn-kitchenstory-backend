package com.kitchenstory.request;

import lombok.Data;

import java.util.Optional;

@Data
public class ReviewRequest {

    private double rating;

    private Long foodId;

    private Optional<String> reviewDescription;
}