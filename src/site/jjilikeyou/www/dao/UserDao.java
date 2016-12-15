package site.jjilikeyou.www.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import site.jjilikeyou.www.pojo.User;
public interface UserDao {

	User checkName(@Param("username")String username);

}
