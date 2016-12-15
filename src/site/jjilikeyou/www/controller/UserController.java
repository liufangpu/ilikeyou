package site.jjilikeyou.www.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
	
	
	@RequestMapping("/checkName")
	public void checkUsername(@RequestParam("username")String username,HttpServletResponse response){
		System.out.println("hahahaha");
		System.out.println(username);
		doResponse(response, "true");
		
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
