package com.example.store.model;

import lombok.Data;

@Data
public class CartDTO {
	
	private int cart_id; 
	private String userid; 
	private int product_code;
	
	private String name; 
	private String product_name; 
	private int price;
	private int money; 
	
	private int amount;

	private String cateCode;
}
