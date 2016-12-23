package site.jjilikeyou.www.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import site.jjilikeyou.www.pojo.User;
/**
 * 
* @Title: UserIterceptor.java 
* @Package com.feinno.upublish.statistics.utils 
* @Description: TODO(登录拦截器，用户为空自动跳转login.jsp) 
* @author liufangpu  
* @date 2016年9月19日 下午3:44:26 
* @version V1.0
 */
public class UserIterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		User user = (User) request.getSession().getAttribute("user"); 
	        if(user==null){  
	        	response.sendRedirect(request.getContextPath() + "/login.jsp");
	           
	        }  
	        return true;   
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	      
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
