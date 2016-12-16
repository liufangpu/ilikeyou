package site.jjilikeyou.www.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	public void addUser(HttpServletResponse response,@RequestParam("username") String username,@RequestParam("password")String password,@RequestParam("email")String email){
		System.out.println(username);
		User user=new User(username, password, email);
		int i=userSerivce.addUser(user);
		System.out.println("cha"+i);
		if (i>0) {
			doResponse(response, "true");
		}else {
			doResponse(response, "false");
		}
	}
	
	@RequestMapping("/login")
	public String login(HttpSession session,HttpServletResponse response,@RequestParam("username")String username,@RequestParam("password")String password){
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		User user2=userSerivce.login(user);
		session.setAttribute("user", user2);
		if (user2!=null) {
			return "redirect:/chat.html?username="+username;
		}else {
			return "redirect:/index.html";
		}
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
