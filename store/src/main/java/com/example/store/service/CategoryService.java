package com.example.store.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.store.dao.CategoryDAO;
import com.example.store.model.CategoryDTO;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class CategoryService {
	
	private final CategoryDAO categoryDao;
	
	
	public List<CategoryDTO> getCategoryList() throws Exception {
		return categoryDao.getCategList();
	}


	public void saveCategory(CategoryDTO dto) throws Exception {
		categoryDao.saveCategory(dto);
		
	}


	public void updateCategory(CategoryDTO dto) throws Exception {
		categoryDao.updateCateg(dto);
		
	}


	public void deleteCategory(String code) throws Exception {
		categoryDao.deleteCateg(code);
		
	}

	

}
