package com.kitchenstory.request;

import lombok.Data;

@Data
public class AddFoodRequest {

    private String shortName;

    private String cuisine;

    private String description;

    private int stock;

    private String category;

    private String img;

}