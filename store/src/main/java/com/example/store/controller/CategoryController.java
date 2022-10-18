package com.example.store.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.store.dao.CategoryDAO;
import com.example.store.model.CategoryDTO;
import com.example.store.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/category")
public class CategoryController {
	
	private final CategoryService service;
	
	
	@GetMapping("/get")
	public String getList(Model model) {
		model.addAttribute("categoryVO", new CategoryDTO());
		return "category/category";
	}
	
	
	@ResponseBody
	@GetMapping("/list")
	public ResponseEntity<List<CategoryDTO>> getList() {
		
		List<CategoryDTO> list = null;
		
		try {
			list = service.getCategoryList();
			return new ResponseEntity<List<CategoryDTO>>(list, HttpStatus.OK); // 200

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<CategoryDTO>>(list, HttpStatus.BAD_REQUEST); // 400
		}
		
	}
	
	
	@ResponseBody
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody CategoryDTO dto) {
		
		try {
			service.saveCategory(dto);
			return new ResponseEntity<>("WRITE_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("WRITE_ERROR", HttpStatus.BAD_REQUEST);
		}

	}
	
	
	@ResponseBody
	@PostMapping("/edit/{idx}")
	public ResponseEntity<String> edit(@PathVariable Integer idx, @RequestBody CategoryDTO dto) {
		
		dto.setIdx(idx);
		try {
			service.updateCategory(dto);
			return new ResponseEntity<>("EDIT_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("EDIT_ERROR", HttpStatus.BAD_REQUEST);
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
