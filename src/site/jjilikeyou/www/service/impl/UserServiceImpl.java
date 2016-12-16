package site.jjilikeyou.www.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.jjilikeyou.www.dao.UserDao;
import site.jjilikeyou.www.pojo.User;
import site.jjilikeyou.www.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public User checkName(String username) {
		User user=userDao.checkName(username);
		return user;
	}
	@Override
	public int addUser(User user) {
		int i=userDao.addUser(user);
		return i;
	}
	@Override
	public User login(User user) {
		
		return userDao.login(user);
	}

}
