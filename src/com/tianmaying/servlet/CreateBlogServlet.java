package com.tianmaying.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tianmaying.model.Blog;
import com.tianmaying.model.BlogRepository;
import com.tianmaying.model.BlogRepositoryByList;
import com.tianmaying.model.User;
import com.tianmaying.model.UserRepository;

@WebServlet("/create")
public class CreateBlogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    BlogRepository blogRepository = BlogRepositoryByList.instance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         //如果不指定method，则默认是Get请求
//    	if (request.getSession().getAttribute("currentUser")!=null) {
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/create.jsp");
//            dispatcher.forward(request, response);
//		}
    	System.out.println("进入createServlet,说明CurrentUser不为Null,访问creat.jsp");
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/create.jsp");
        dispatcher.forward(request, response);
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        boolean flag = true;
        String output = new String();
        if (title==null||title.trim().length()==0) {
			output = "博客标题不能为空";
			flag = false;
		}else if (content==null||content.trim().length()==0) {
			output = "博客内容不能为空";
			flag = false;
		}else {
			flag = true;
		}
        
        if (flag) {
            Blog blog = new Blog(title, content);
            blogRepository.add(blog);
            response.sendRedirect(request.getContextPath() + "/blog?id="+blog.getId());
		}else {
			request.setAttribute("message", output);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/create.jsp");
	        dispatcher.forward(request, response);
		}

    }
}
