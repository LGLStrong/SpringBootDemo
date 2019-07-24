package com.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.Product;

@Controller
public class WebController {

	@RequestMapping("/demoindex")
	public String demoindex(Model model) {
		model.addAttribute("name","<span style='color:green'>thymeleaf haha</span>");
		Product product = new Product();
		product.setId("id3423");
		product.setName("productName");
		model.addAttribute("product", product);
		model.addAttribute("date", new Date());
//		int[] array = {1,2,2,2,5};
//		model.addAttribute("array", array);
		List<Product> products = new ArrayList<Product>();
		for (int i = 0; i < 5; i++) {
			Product p = new Product(); 
			p.setId("id"+i);
			p.setName("Name"+i);
			products.add(p);
		}
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		model.addAttribute("list", list);
		model.addAttribute("products", products);
		return "demo";
	}

	@RequestMapping("/locale")
	public String locale() {
		return "locale";
	}
	@RequestMapping("/show")
	public String show() {
		return "show";
		
	}
}
