package com.example.store.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.store.dao.CategoryDAO;
import com.example.store.dao.ProductDao;
import com.example.store.model.CategoryDTO;
import com.example.store.model.ProductDTO;
import com.example.store.service.CategoryService;

import lombok.RequiredArgsConstructor;
import net.sf.json.JSONArray;

@RequiredArgsConstructor
@Controller
public class ProductController {
	
	ProductDao productDao;
	private final CategoryService service;
	private final CategoryDAO categoryDao;
	
	
	@GetMapping("/")
	public String home() {
		return "redirect:/list.do";
	}
	
	
//	@GetMapping("/list")
//	public ModelAndView list(@RequestParam(defaultValue="") String product_name, ModelAndView mav) {
//		
//		mav.setViewName("list");
//		mav.addObject("list", productDao.list(product_name));
//		mav.addObject("product_name", product_name);
//		return mav;
//	}
	
	
	
	@GetMapping("/list.do")
	public String search(@RequestParam(defaultValue="") String product_name, Model model) {
		
		List<ProductDTO> list = productDao.search(product_name);
		System.out.println(list);
		
		try {
			model.addAttribute("list", list);
			model.addAttribute("product_name", product_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "product/productList";
		
	}
	
//	@GetMapping("list.do")
//	public ModelAndView list(ModelAndView mav) {
//		mav.setViewName("product/productList");
//		mav.addObject("list", productDao.list());
//		return mav;
//		
//	}
	
	
	@GetMapping("/write.do")
	public String write(Model model) {
		
		List<CategoryDTO> category = null;
		try {
			category = service.getCategoryList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("listCategory", JSONArray.fromObject(category));
		return "product/productWrite";
	}
	
	
//	@PostMapping("/insert.do")
//	public String insert(ProductDTO productDto, @RequestParam MultipartFile img, 
//			HttpServletRequest request) {
//		
//		String filename = "-";
//		if (img != null && !img.isEmpty()) {
//			filename = img.getOriginalFilename();
//			try {
//				ServletContext application = request.getSession().getServletContext();
//				
//				// 배포 디렉토리 
//				String path = application.getRealPath("/resources/images/");
//				
//				img.transferTo(new File(path + filename));
//				System.out.println(path); 
//				// D:\worky\store\src\main\webapp\resources\images\
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		productDto.setFilename(filename);
//		productDao.insert(productDto);
////		map.put("filename", filename);
////		productDao.insert(map);
//		
//		return "redirect:/list";
//		
//	}
	
	@PostMapping("/insert.do")
	public String insert(ProductDTO productDto, HttpServletRequest request) throws Exception {
		
		String filename = "-";
		if (!productDto.getFile1().isEmpty()) {
			filename = productDto.getFile1().getOriginalFilename(); 
			try {
				ServletContext application = request.getSession().getServletContext();
				String path = application.getRealPath("/resources/images/");
				new File(path).mkdir(); 
				productDto.getFile1().transferTo(new File(path + filename));
				} catch (Exception e) {
					e.printStackTrace();
			}
		}
		
//		String cateCode = request.getParameter("category");
//		productDto.setCateCode(cateCode);
//		System.out.println(cateCode);
		
		
	
		productDto.setFilename(filename); 
		
		productDao.insert(productDto);
		
		return "redirect:/list.do";

	}
	
//	@GetMapping("/detail/{product_code}")
//	public ModelAndView detail(@PathVariable Integer product_code, ModelAndView mav) {
//		mav.setViewName("detail");
//		mav.addObject("product", productDao.detail(product_code));
//		return mav;
//	}
	
	@GetMapping("/detail/{product_code}")
	public String detail(@PathVariable int product_code, Model model) {
		
		ProductDTO dto = productDao.detail(product_code);
		
		model.addAttribute("dto", dto);
		
		return "product/productDetail";
	}
	
	
	@GetMapping("/edit/{product_code}")
	public String edit(@PathVariable int product_code, Model model) {
		
		ProductDTO dto = productDao.detail(product_code);
		
		model.addAttribute("dto", dto);
		
		return "product/productEdit";
	}
	
	
	@PostMapping("/update.do")
	public String update(ProductDTO productDto, HttpServletRequest request) {
		
		String filename = "-";
		if (!productDto.getFile1().isEmpty()) {
			filename = productDto.getFile1().getOriginalFilename(); 
			try {
				ServletContext application = request.getSession().getServletContext();
				String path = application.getRealPath("/resources/images/");
				new File(path).mkdir(); 
				productDto.getFile1().transferTo(new File(path + filename));
				} catch (Exception e) {
					e.printStackTrace();
			}
			
			productDto.setFilename(filename);
		} else {
			ProductDTO dto2 = productDao.detail(productDto.getProduct_code());
			productDto.setFilename(dto2.getFilename());
		}
		
		productDao.update(productDto);
		
		return "redirect:/list.do";
	}
	
	
	@PostMapping("/delete.do")
	public String delete(int product_code, HttpServletRequest request) {
		String filename = productDao.filename(product_code);
		if (filename != null && !filename.equals("-")) {
			ServletContext application = request.getSession().getServletContext();
			String path = application.getRealPath("/resources/images/");
			File file = new File(path + filename);
			if (file.exists()) {
				file.delete();
			}
			
		}
			productDao.delete(product_code);
			return "redirect:/list.do";
	}
	
	
	


}
