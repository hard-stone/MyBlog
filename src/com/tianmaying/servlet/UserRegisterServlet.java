package com.tianmaying.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tianmaying.model.User;
import com.tianmaying.model.UserRepository;

@WebServlet("/register")
public class UserRegisterServlet extends HttpServlet  {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // your code here
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	String username = request.getParameter("username");
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	boolean flag = true;
    	User user = new User();
    	if (username==null||username.length()<6) {
			flag = false;
		}else if (password==null||password.length()<8) {
			flag = false;
		}else if (email==null||Isreason(email)) {
			flag = false;
		}else {
			user.setEmail(email);
			user.setUsername(username);
			user.setPassword(password);
			UserRepository.add(user);
		}
    	
    	if (flag) {
            response.getWriter().append("<html><head><meta charset='utf-8' /><title>天码营博客</title></head><body>")
	        .append(username+"，欢迎来到天码营！")
            .append("</body></html>");
		}else {
			response.getWriter().append("<html><head><meta charset='utf-8' /><title>天码营博客</title></head><body>")
	        .append("输入信息有误")
            .append("</body></html>");
		}
    	
    }

	private boolean Isreason(String email) {
		String format = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";		
		if (email.matches(format)) {
			return false;
		}else {
			return true;
		}
	}
}
