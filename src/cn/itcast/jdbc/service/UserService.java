package cn.itcast.jdbc.service;

import cn.itcast.domain.User;
import cn.itcast.jdbc.dao.UserDao;

public class UserService {
	
	private UserDao userDao;
	public void regist(User user){
		this.userDao.addUser(user);
		
	}

}
