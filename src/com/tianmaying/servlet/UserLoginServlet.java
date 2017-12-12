package com.tianmaying.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tianmaying.model.User;
import com.tianmaying.model.UserRepository;
import com.tianmaying.utils.StringUtils;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet  {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // your code here
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String value[] = request.getParameterValues("remember-me");
        String output = new String();
        Boolean flag = false;
        if (username==null||username.trim().length()==0) {
			output="用户名不能为空";
			flag = false;
		}else if (UserRepository.getByUsername(username)==null) {
			output="该用户不存在";
			flag = false;
		}else if (password==null||!password.equals(UserRepository.
				getByUsername(username).getPassword())) {
			output="密码不正确";
			flag = false;
		}else{
			if (value != null && !value[0].isEmpty()) {
				output="登陆成功（下次将自动登陆）";
				User user = UserRepository.getByUsername(username);
				String token = null;
				try {
					token = StringUtils.encodeByMd5(username+user.getEmail()+password);
					token = StringUtils.encodeByBase64(username+"|"+token);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//设置cookie
				Cookie cookie = new Cookie("user", token);
				cookie.setMaxAge(2*60);
				response.addCookie(cookie);
				flag=true;
			}else {
				output="登陆成功";
				flag=true;
			}
		}
        
        /*重定向*/
        if (flag) {//登录成功
        	String next = request.getParameter("next");
        	String next1 = (String) request.getSession().getAttribute("next");
        	request.getSession().setAttribute("currentUser", UserRepository.getByUsername(username));
        	System.out.println("next:"+next);
        	if (next1!=null) {
				System.out.println("next:"+next1);
			}
        	if (next==null||next.isEmpty()) {
        		System.out.println("next为空访问/blogs");
        		
    			response.sendRedirect(request.getContextPath()+"/blogs");
    		}else {
    			System.out.println("next不为空访问 next");
    			response.sendRedirect(next);
    		}
        	
//			response.sendRedirect(request.getContextPath() +"/blogs");
		}else {
			request.setAttribute("message", output);
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		}
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String next = request.getParameter("next");
    	System.out.println("next:"+next+",参数传到get方法里");
    	
    	RequestDispatcher dispatcher = null;
//    	Cookie[] cookies = request.getCookies();
//    	if (cookies!=null) {
//    		System.out.println("cookie为空");
//    		dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
//            dispatcher.forward(request, response);
//            return;
//		}else {
//	    	/*当currentUser不为空的时候，login页面跳转到blogs*/
//	    	if (request.getSession().getAttribute("currentUser")!=null) {
//	    		System.out.println("currentUser说明不为空");
//	        	for (int i = 0; i < cookies.length; i++) {
//	    			Cookie cookie = cookies[i];
//	    			if (cookie.getName().equals("user")) {
////	    				String username = cookie.getValue();
//	    				String token = cookie.getValue();
//	    				dispatcher = request.getRequestDispatcher("/blogs");
//	    				dispatcher.forward(request, response);
//	    				return;
//	    			}
//	    		}
//			}
//		}
    	
    	dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
    	request.getSession().setAttribute("next", next);
        dispatcher.forward(request, response);
	}
}
