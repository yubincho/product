package com.example.store.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.store.model.CategoryDTO;

@Repository 
public class CategoryDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	
	public List<CategoryDTO> getCategList() throws Exception {
		return sqlSession.selectList("category.getCategList");
	}


	//  카테고리 등록
	public int saveCategory(CategoryDTO categoryDto) throws Exception {
		return sqlSession.insert("category.saveCategory", categoryDto);
	}


	public int updateCateg(CategoryDTO categoryDto) throws Exception {
		return sqlSession.update("category.updateCateg", categoryDto);
	}


	public int deleteCateg(String code) throws Exception {
		return sqlSession.delete("category.deleteCateg", code);
	}


}
