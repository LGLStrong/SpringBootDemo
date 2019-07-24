package com.dao.customdao;

import org.springframework.stereotype.Repository;

import com.model.User;
@Repository
public interface UserDaoCustom{
	String test(User user);
}
