package com.example.store.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.store.service.CategoryService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/restCat")
public class CatRestController {
	
	private final CategoryService service;
	
	
	@PostMapping("/list")
	public Map<String, Object> getList() {
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			result.put("catList", service.getCategoryList());
			result.put("status", "ok");
		} catch (Exception e) {
			result.put("status", "false");
		}
		
		return result;
	}
	

}
