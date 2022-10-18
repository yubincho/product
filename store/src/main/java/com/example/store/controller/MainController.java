package com.example.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.store.dao.ProductDao;
import com.example.store.model.ProductDTO;

@RestController
public class MainController {
	
	@Autowired
	ProductDao productDao;
	
//	@GetMapping
//	public String main() {
//		return "hello";
//	}
	
//	@GetMapping("/list")
//	public List<ProductDTO> lists() {
//		
//		List<ProductDTO> list = productDao.list();
//		return list;
//		
//	}

}
