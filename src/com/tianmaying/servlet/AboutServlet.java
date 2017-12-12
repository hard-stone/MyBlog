package com.tianmaying.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tianmaying.model.Project;
import com.tianmaying.model.ProjectRepository;

@WebServlet("/about")
public class AboutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// your code here，获取项目信息，渲染about.jsp页面
		List<Project> projects = ProjectRepository.getAll();
		request.setAttribute("projects", projects);
		request.getRequestDispatcher("/WEB-INF/jsp/about.jsp").forward(request, response);
	}
}
