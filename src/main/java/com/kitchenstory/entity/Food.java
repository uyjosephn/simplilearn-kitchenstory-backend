package com.kitchenstory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "food")
public class Food {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "short_name")
	private String shortName;
	
	@Column(name = "cuisine")
	private String cuisine;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "stock")
	private int stock;
	
	@Column(name = "stock_available")
	private int stockAvailable;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "img")
	private String img;
	

}
