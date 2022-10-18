package com.example.store.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Data
public class ProductDTO {
	
	private int product_code;
	private String product_name;
	private String description;
	private int price;
	private String filename;
	private MultipartFile file1;
	private String cateCode;
	
	public ProductDTO() {
		
	}

	public ProductDTO(String product_name, String description, int price, MultipartFile file1, String cateCode) {
		
		this.product_name = product_name;
		this.description = description;
		this.price = price;
		this.file1 = file1;
		this.cateCode = cateCode;
	}
	
	
	
	

}
