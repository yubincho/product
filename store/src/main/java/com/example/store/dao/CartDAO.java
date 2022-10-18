package com.example.store.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.store.model.CartDTO;

@Repository
public class CartDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	
	// 품목별 금액
	public List<CartDTO> cart_money() {
		return sqlSession.selectList("cart.cart_money");
	}
	
	// 총 금액
	public int sum_money(String userid) {
		return sqlSession.selectOne("cart.sum_money", userid);
	}


	public void insert(CartDTO dto) { 
		sqlSession.insert("cart.insert", dto);
	}


	public List<CartDTO> list(String userid) {
		return sqlSession.selectList("cart.list", userid);
	}


	public void delete(int cart_id) { 
		sqlSession.delete("cart.delete", cart_id);
	}

	
	public void delete_all(String userid) { 
		sqlSession.delete("cart.delete_all", userid);
	}
	
	
	//	수량 변경
	public void modify(CartDTO dto) {
		sqlSession.update("cart.modify", dto);
	}

	
	
}
