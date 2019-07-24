package com.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.Product;
import com.service.ProductService;

@RestController
public class ProductController {
	private static final Logger LOGGER = LogManager.getLogger(ProductController.class)  ;
	@Autowired
	ProductService productService;
	@RequestMapping("/getProducts")
//	@CrossOrigin(origins = "http://localhost:8080") //the specific request
	public ResponseEntity<Object> getProducts(){
//		return new ResponseEntity<Object>("######The CORS##########", HttpStatus.OK);
		LOGGER.debug("ssssssssssssss");
		return new ResponseEntity<Object>(productService.getProducts(), HttpStatus.OK);
	}
	
	/*
	 * the front end can use 'Postman' which is the plug-in of Chrome  ,and the Body select the 'raw' ,
	 * then select the content type 'JSON(application/json)'
	 */
	@RequestMapping("/updateProduct/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable String id,@RequestBody Product product){
		productService.updateProduct(id, product);
		return new ResponseEntity<Object>("Product is updated successsfully", HttpStatus.OK);
	}

	@GetMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		productService.deleteProduct(id);
		return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
	}

	@PostMapping("/createproducts")
	public ResponseEntity<Object> createProduct(@RequestBody Product product) {
		productService.createProduct(product);
		return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}
	@RequestMapping("/testProduct")
	public String testProduct(@RequestParam String testinfo) {
		System.out.println(testinfo);
		return testinfo;
	}
}
