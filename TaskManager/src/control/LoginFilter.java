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

import model.User;
import dataaccess.TaskManagerDAO;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/index.jsp")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {

    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String redirectURL = httpRequest.getContextPath() + "/admin/userHome.jsp";
		User user = getUserFromCookies(httpRequest);
		if (user.getId() != null) {
			//if found in cookies, set session attribute
			HttpSession session = httpRequest.getSession(true);
			session.setAttribute("user", user);
			httpResponse.sendRedirect(redirectURL);
		}
		else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

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
					user = dao.retrieveUser(userName);
				}
				if (cookie.getName().equals("user_pass_TaskManager_Su14")) {
					hashPass = cookie.getValue();
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
