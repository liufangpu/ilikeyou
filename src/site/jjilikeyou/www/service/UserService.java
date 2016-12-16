package site.jjilikeyou.www.service;

import site.jjilikeyou.www.pojo.User;

public interface UserService {

	User checkName(String username);

	int addUser(User user);

	User login(User user);
  
}
