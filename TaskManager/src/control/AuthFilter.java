package control;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataaccess.TaskManagerDAO;
import model.User;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter("/admin/*")
public class AuthFilter implements Filter {
	
	@SuppressWarnings("unused")
	private FilterConfig filterConfig = null;

    public AuthFilter() {
    }

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;	
		HttpServletResponse httpResponse = (HttpServletResponse) response;		
		HttpSession session = httpRequest.getSession(true);
		String redirectURL = httpRequest.getContextPath() + "/login.jsp";
		User user = null;
		
		//check to see if user session attribute is set
		if (session.getAttribute("user") == null)
		{
			//not set, try getting user from cookies
			user = getUserFromCookies(httpRequest);			
			if (user.getId() != null) {
				//if found in cookies, set session attribute
				session.setAttribute("user", user);
			}
		}
		else
		{
			//System.out.println("User attribute set");
		}

		if (httpRequest.getRequestURI().equals(redirectURL) || session.getAttribute("user") != null) {
		    chain.doFilter(request, response);
		} 
		else {
			session.invalidate();
		    httpResponse.sendRedirect(redirectURL);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.filterConfig = fConfig;
	}
	
	public User getUserFromCookies (HttpServletRequest request) {
		User user = null;
		TaskManagerDAO dao = new TaskManagerDAO();
		String userName = "";
		String hashPass = "";
		Cookie cookie = null;
		Cookie[] cookies = null;
		cookies = request.getCookies();
		if(cookies != null) {
			for (int i = 0; i < cookies.length; i++){
				cookie = cookies[i];
				if (cookie.getName().equals("user_id_TaskManager_Su14")) {
					userName = cookie.getValue();
					//System.out.println(cookie.getValue());
					//System.out.println(cookie.getMaxAge());
					user = dao.retrieveUser(userName);
				}
				if (cookie.getName().equals("user_pass_TaskManager_Su14")) {
					hashPass = cookie.getValue();
					//System.out.println(cookie.getValue());
					//System.out.println(cookie.getMaxAge());
				}
			}
		}
		if (user != null && user.getPassword().equals(hashPass))
		{
			return user;
		}
		return new User();
	}

}
