package com.tianmaying.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tianmaying.model.Blog;
import com.tianmaying.model.BlogRepository;
import com.tianmaying.model.BlogRepositoryByList;
import com.tianmaying.model.User;

@WebServlet("/blogs")
public class BlogsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 按照格式打印所有博客
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Blog> blogs = BlogRepositoryByList.instance().getAll();
        request.setAttribute("blogs", blogs);
        User user = (User) request.getSession().getAttribute("currentUser");
        if (user==null) {
        	 System.out.println("浏览者");
		}else {
	        System.out.println("Blogs name:"+user.getUsername());
		}

        request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
	}
}
