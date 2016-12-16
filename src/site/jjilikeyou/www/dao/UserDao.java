package site.jjilikeyou.www.dao;

import org.apache.ibatis.annotations.Param;

import site.jjilikeyou.www.pojo.User;
public interface UserDao {

	User checkName(@Param("username")String username);

	int addUser(User user);

	User login(User user);

}
