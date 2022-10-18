package com.example.store.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.store.model.ProductDTO;


@Repository
public class ProductDao {
	
	@Autowired
	SqlSession sqlSession;
	
	
	public List<ProductDTO> search(String product_name) {
		return sqlSession.selectList("product.list", product_name);
	}
	
	
	public List<ProductDTO> list(String product_name) {
		return sqlSession.selectList("product.list", product_name);
	}
	
	
	public void insert(ProductDTO productDto) {
		sqlSession.insert("product.insert", productDto);
	}
	
	
	public ProductDTO detail(int product_code) {
		return sqlSession.selectOne("product.detail", product_code);
	}
	
	
	public void update(ProductDTO productDto) {
		sqlSession.update("product.update", productDto);
	}
	
	
	public void delete(int product_code) {
		sqlSession.delete("product.delete", product_code);
	}

	
	public String filename(int product_code) {
		return sqlSession.selectOne("product.filename", product_code);
	}
	
}
