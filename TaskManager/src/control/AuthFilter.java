package control;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = httpRequest.getSession(false);
		String redirectURL = httpRequest.getContextPath() + "/authTest.jsp";
			
		if (httpRequest.getRequestURI().equals(redirectURL) || session.getAttribute("auth") != null) {
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

}
