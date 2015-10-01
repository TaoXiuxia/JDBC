package cn.itcast.jdbc.dao;

import cn.itcast.domain.User;

//用于业务逻辑层
public interface UserDao {

	public void addUser(User user);
	
	public User getUser(int userId);
	
	public User findUser(String loginNames,String password);
	
	public void update(User user);
	
	public void delete(User user);
	
}
