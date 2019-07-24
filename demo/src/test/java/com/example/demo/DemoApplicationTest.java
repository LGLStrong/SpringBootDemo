package com.example.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.Util.RedisUtil;
import com.controller.ProductController;
import com.dao.UserDao;
import com.model.User;

import ch.qos.logback.core.net.SyslogOutputStream;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTest {
	private static final Logger LOGGER = LogManager.getLogger(DemoApplicationTest.class)  ;

//	@LocalServerPort
//	private int port;
//
//	@Value("${management.server.port}")
//	private int mgt;
//
//	@Autowired
//	private TestRestTemplate testRestTemplate;
//
//	@Test
//	public void shouldReturn200WhenSendingRequestToController() throws Exception {
//		@SuppressWarnings("rawtypes")
//		ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
//				"http://localhost:" + this.port + "/hello-world", Map.class);
//
//		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
//	}
//
//	@Test
//	public void shouldReturn200WhenSendingRequestToManagementEndpoint() throws Exception {
//		@SuppressWarnings("rawtypes")
//		ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
//				"http://localhost:" + this.mgt + "/actuator/info", Map.class);
//
//		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
//	}
//
//	@Test
//	public void contextLoads() {
//	}

	@Autowired
	RedisUtil redisUtil;
	@Autowired
	UserDao userDao;
	@Test
	public void oracle() {
		System.out.println(11111111);
		List<User> list = (List<User>) userDao.findAll();
//		for (User user : list) {
//			System.out.println(user);
//		}
		list.forEach(System.out :: println);
		
	}
	public void logList(List<User> list) {
		for (User user : list) {
			LOGGER.info(user);
		}
	}
	@Test
	public void JPAApply() {
		System.out.println("FindById:"+userDao.findById("12"));
		System.out.println("Count"+userDao.count());
		User user = new User();
		user.setDm("test");
		user.setMc("FORTEST");
		LOGGER.info("exists"+userDao.existsById("test"));
		userDao.deleteById("test");
		LOGGER.info("Save"+userDao.save(user));
		LOGGER.info("exists"+userDao.existsById("test"));
		Page<User> p = userDao.findAll(PageRequest.of(1, 10));
//		Iterable<User> i = userDao.findAll(new Sort(Sort.DEFAULT_DIRECTION,"dm"));
//		for (User user2 : i) {
//			LOGGER.info(user2);
//		}
		logList(userDao.findByMc("军队学兵"));
		LOGGER.info(p);
		 Page<User> page =userDao.findByBz("bz", Pageable.unpaged());
		 LOGGER.info(userDao.findTopByOrderByDmDesc());
		 LOGGER.info(userDao.findFirstByOrderByDmAsc());
		 Future<User> f =userDao.findByDm("111");
		 LOGGER.info(f);
		 LOGGER.info(userDao.test(user));
		 List<User> l =userDao.findByAndSort("b", Sort.by("dm"));
		 LOGGER.info(userDao.findBy_Dm("111"));
		 LOGGER.info("#####################################");
		 LOGGER.info(userDao.testNativeQuery("customize"));
		 LOGGER.info(userDao.findByDmAndBz("111", "bz"));
		 LOGGER.info(userDao.findBy_Bz("bz"));
		 LOGGER.info("#####################################");
		 
	}
	@Test
	public void hasKey() {
		System.out.println(redisUtil.hasKey("d"));
	}
	
	@Test
	public void set() {
		List<String> list = new ArrayList<String>();
		list.add("ha");
		list.add("heh");
		redisUtil.set("test",list );
	}
}
