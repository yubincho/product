package com.example.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.store.dao.AdminDAO;
import com.example.store.model.MemberDTO;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	
	@Autowired
	AdminDAO admindao;
	
	
	@GetMapping("/login")
	public String login() {
		return "admin/login";
	}
	
	
	@PostMapping("/login_check")
	public ModelAndView loginCheck(MemberDTO dto, HttpSession session, ModelAndView mav) {
		String name = admindao.login(dto);
		if (name != null) {
			session.setAttribute("admin_userid", dto.getUserid());
			session.setAttribute("admin_name", name);
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", name);
			
			mav.addObject("message", "success");
			mav.setViewName("admin/admin");
			
		} else {
			mav.addObject("message", "error");
			mav.setViewName("admin/login");
			
		}
		return mav;
	}
	
		
	@PostMapping("/logout")
	public String memLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/admin/login";
	}
	

}
