package com.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Util.RedisUtil;
import com.model.Product;

@Controller
public class RedisTController {

	@Autowired
	RedisUtil redisUtil;
	@RequestMapping("/demoredis")
	public String demoindex(Model model) {
		model.addAttribute("name","<span style='color:green'>thymeleaf haha</span>");
		Product product = new Product();
		product.setId("id3423");
		product.setName("productName");
		model.addAttribute("product", product);
		model.addAttribute("date", new Date());
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
		redisUtil.set("date", new Date(), 30);
		redisUtil.set("product", product, 300);
		return "demoredis";
	}
	@RequestMapping("redis")
	public String testRedis(Model model) {
		model.addAttribute("teststr", redisUtil.getStr("str"));
		model.addAttribute("expire", redisUtil.getExpire("str1"));
		model.addAttribute("product", redisUtil.get("product"));
		return "demoredis";
	}

}
