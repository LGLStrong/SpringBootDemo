package com.dao.customdaoimpl;
import com.dao.customdao.UserDaoCustom;
import com.model.User;

public class UserDaoCustomImpl implements UserDaoCustom{

	@Override
	public String test(User user) {
		return "From test ComtomDao";
	}

}
/*
 * Resolution of Ambiguity:If multiple implementations are found in different packages ,
 * for example：com.dao.'anotherPackage'.UserDaoCustomImpl;
 * first,you should annotate the original interface--UserDaoCustom with @Component("specialCustom"),
 * then annotate this which you want to use with @Component("specialCustomImpl")
 */
