package com.tianmaying.servlet;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tianmaying.model.Blog;
import com.tianmaying.model.BlogRepository;
import com.tianmaying.model.BlogRepositoryByList;

@WebServlet("/blog")
public class BlogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
//        String idString = request.getParameter("id");
//        int id = -1;
//        try {
//        	id = Integer.parseInt(idString);
//		} catch (Exception e) {
//			// TODO: handle exception
//			request.getRequestDispatcher("/WEB-INF/jsp/404.jsp").forward(request, response);
//			return;
//		}
//        Blog blog = BlogRepositoryByList.instance().getBlogById(id);
//        if (blog!=null) {
//        	 request.setAttribute("blog", blog);
//             request.getRequestDispatcher("/WEB-INF/jsp/item.jsp").forward(request, response);
////        	response.sendRedirect(request.getContextPath() +"/blog?id="+idString);
//		}else {
//			request.getRequestDispatcher("/WEB-INF/jsp/404.jsp").forward(request, response);
//		}
    	BlogRepository blogRepository = BlogRepositoryByList.instance();
    	try {
			long id = Long.parseLong(request.getParameter("id"));
			Blog blog = blogRepository.getBlogById(id);
	        if (blog!=null) {
	        	request.getSession().setAttribute("blog", blog);
	        	request.getSession().setAttribute("currentAuthor", blog.getAuthor());
	            request.getRequestDispatcher("/WEB-INF/jsp/item.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("/WEB-INF/jsp/404.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.getRequestDispatcher("/WEB-INF/jsp/404.jsp").forward(request, response);
		}
    }
}
