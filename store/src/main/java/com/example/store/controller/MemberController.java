package com.example.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.store.dao.MemberDAO;
import com.example.store.model.MemberDTO;

@Controller
public class MemberController {

	
	@Autowired
	MemberDAO memberDao;
	
	
	@GetMapping("/login.do")
	public String login() {
		return "member/login";
	}
	
	
	@RequestMapping("/login_check.do")
	public ModelAndView loginCheck(MemberDTO dto, HttpSession session) {
		
		String name = memberDao.login(dto);
		if (name != null) {
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", name);
		}
		ModelAndView mav = new ModelAndView();
		if (name != null) {
			mav.setViewName("main");
		} else {
			mav.setViewName("member/login");
			mav.addObject("message", "error");
		}
		return mav;
	}
	
	
	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpSession session, ModelAndView mav) {
		session.invalidate();
		mav.setViewName("member/login");
		mav.addObject("message", "logout");
		return mav;
		
	}
	
	
	
}
