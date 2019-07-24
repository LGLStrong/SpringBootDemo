package com.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.UserDao;
import com.model.User;

@Controller
@RequestMapping("/users")
public class UserController {
	private static final Logger LOGGER = LogManager.getLogger(UserController.class)  ;
	@Autowired
	UserDao userDao;
	
	/*
	 * @EnableSpringDataWebSupport
	 * the method receives a User instance directly, and no further lookup is necessary,
	 *  The instance can be resolved by letting Spring MVC 
	 *  convert the path variable into the id type of the domain class 
	 *  first and eventually access the instance through calling findById(…) 
	 *  on the repository instance registered for the domain type.
	 *  ---by DomainClassConverter
	 */
	@RequestMapping("/{dm}")
	  String showUserForm(@PathVariable("dm") User user, Model model) {
		LOGGER.info("User Controller");
	    model.addAttribute("user", user);
	    return "locale";
	  }
	
	
	@Bean 
	PageableHandlerMethodArgumentResolverCustomizer customize() {
		Pageable customPage = PageRequest.of(2, 10);
		return pageableResolver -> pageableResolver.setFallbackPageable(customPage);
	}
	/*
	 * you can customize the Pageable and Sort by  
	 * implementing the PageableHandlerMethodArgumentResolverCustomizer interface 
	 * or the SortHandlerMethodArgumentResolverCustomizer interface
	 * @PageableDefault
	 * /users/showUsers?size=15&page=2&sort=dm&sort=mc,asc
	 */
	@RequestMapping("/showUsers")
	String showUsers(Model model,   Pageable pageable ) {
		LOGGER.info("User Controller showUsers ");
		model.addAttribute("users", userDao.findAll(pageable));
		return "locale";
	}

//	@RequestMapping(value = "/pageresourse")
//	HttpEntity<PagedResources<User>> persons(Pageable pageable, PagedResourcesAssembler assembler) {
//
//		Page<User> users =userDao.findAll(pageable);
//		assembler.toResource(users);
//		return new ResponseEntity(assembler.toResource(users), HttpStatus.OK);
//	}
}
