package site.jjilikeyou.www.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import site.jjilikeyou.www.pojo.User;
import site.jjilikeyou.www.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userSerivce;
	
	@RequestMapping("/checkName")
	public void checkUsername(@RequestParam("username")String username,HttpServletResponse response){
		System.out.println(username);
		User user=userSerivce.checkName(username);
		System.out.println(user);
		if (user==null) {
			doResponse(response, "true");
		}else {
			doResponse(response, "false");
		}
	}
	@RequestMapping("/addUser")
	public String addUser(@RequestParam("username") String username,@RequestParam("password")String password,@RequestParam("email")String email){
		System.out.println(username);
		User user=new User(username, password, email);
		int i=userSerivce.addUser(user);
		System.out.println(i);
		return "success";
	}
	public static void doResponse(HttpServletResponse response,Object object){
		response.setCharacterEncoding("utf-8");
		
		try {
			PrintWriter writer = response.getWriter();
			writer.print(object);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
