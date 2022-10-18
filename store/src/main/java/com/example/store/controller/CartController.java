package com.example.store.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.store.dao.CartDAO;
import com.example.store.model.CartDTO;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartDAO cartDao;
	
	
	@RequestMapping("/delete.do")
	public String delete(int cart_id) { 
		
		cartDao.delete(cart_id); // 레코드 삭제
		return "redirect:/cart/list.do"; // 리스트로 이동
	}
	
	
	@RequestMapping("/deleteAll.do")
	public String deleteAll(HttpSession session) {
		String userid = (String)session.getAttribute("userid"); // 세션 변수
		if (userid != null) { // 세션값이 있으면 
			cartDao.delete_all(userid); // 장바구니 비우기
		}
		return "redirect:/cart/list.do"; 
	}


	@PostMapping("/update.do")
	public String update(int[] amount, int[] cart_id, HttpSession session) {
		String userid = (String) session.getAttribute("userid");

		if (userid == null) { // 로그아웃 상태이면
			return "redirect:/login.do"; // 로그인 페이지로 이동
		}
		for (int i = 0; i < cart_id.length; i++) { 
			if (amount[i] == 0) { // 수량이 0이면
				cartDao.delete(cart_id[i]); // 레코드 삭제
			} else {
				CartDTO dto = new CartDTO(); 
				dto.setUserid(userid); 
				dto.setCart_id(cart_id[i]); 
				dto.setAmount(amount[i]); 
				cartDao.modify(dto); // 수량 변경
			}
		}
		
		return "redirect:/cart/list.do";
	}

	
	@RequestMapping("/list.do")
	public ModelAndView list(HttpSession session, ModelAndView mav) {
		Map<String, Object> map = new HashMap<>();
		String userid = (String) session.getAttribute("userid");

		if (userid != null) {
			List<CartDTO> list = cartDao.list(userid); 
			int sumMoney = cartDao.sum_money(userid);
			int fee = sumMoney >= 30000 ? 0 : 2500; // 3만원 이상이면 배송료 면제
			
			map.put("sumMoney", sumMoney); 
			map.put("fee", fee); 
			map.put("sum", sumMoney + fee); 
			map.put("list", list); 
			map.put("count", list.size());
	
			mav.setViewName("cart/cartList"); // 출력 페이지 이름
			mav.addObject("map", map); // 해시맵 저장 
			return mav;
		} else {
			return new ModelAndView("member/login"); // 로그인페이지로 이동
		}
		
	}

	
	@PostMapping("/insert.do")
	public String insert(CartDTO dto, HttpSession session) {
		String userid = (String) session.getAttribute("userid");
		if (userid == null) {
			return "redirect:/member/login.do";
		}
		
		dto.setUserid(userid); 
		cartDao.insert(dto);
		return "redirect:/cart/list.do";
	}


	

}
