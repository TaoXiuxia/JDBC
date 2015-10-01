package cn.itcast.jdbc.dao;

import java.util.Date;

import cn.itcast.dao.Impl.UserDaoJdbcImpl;
import cn.itcast.domain.User;

public class UserDaoTest {

	public static void main(String[]args){
		UserDao userDao=new UserDaoJdbcImpl();
		
		User user=new User();
		user.setBirthday(new Date());
		user.setName("dao name1");
		user.setMoney(1000f);
		
//		userDao.addUser(user);
//		User u=userDao.findUser(user.getName(), null);
//		System.out.println(u.getId());
		
//		User u=userDao.getUser(6);
//		u.setMoney(20000f);
//		userDao.update(u);

		User u1=userDao.getUser(6);
		userDao.delete(u1);
		
	}
}
