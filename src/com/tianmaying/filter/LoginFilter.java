package com.tianmaying.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tianmaying.model.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(filterName="/LoginFilter",urlPatterns={"/create","/about","/blogs"})
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
    	RequestDispatcher dispatcher = null;
    	HttpServletRequest req = (HttpServletRequest) request;//ServletRequest
    	HttpServletResponse res =  (HttpServletResponse) response;
    	String pathString =req.getContextPath()+"/login?next="+req.getRequestURI();
    	Cookie[] cookies = req.getCookies();
    	if (cookies!=null) {
	    	/*当currentUser不为空的时候，login页面跳转到blogs*/
	    	if (req.getSession().getAttribute("currentUser")!=null) {
	    		System.out.println("currentUser说明不为空");
	        	for (int i = 0; i < cookies.length; i++) {
	    			Cookie cookie = cookies[i];
	    			if (cookie.getName().equals("user")) {
//	    				String username = cookie.getValue();
	    				String token = cookie.getValue();
	    				res.sendRedirect(pathString);
	    				return;
	    			}
	    		}
			}
		}
		
		// place your code here
		User user = (User) req.getSession().getAttribute("currentUser");
		if (user==null) {
			res.sendRedirect(pathString);
			System.out.println("进入Filter，redirect到："+pathString);
			return;
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
