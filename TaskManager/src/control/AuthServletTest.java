package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AuthServletTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		if (action.equalsIgnoreCase("logout") || !user.equals("user") || !pass.equals("pass")) {
			if (request.getSession(false) != null) {
				request.getSession(false).invalidate();	
				request.setAttribute("message", "Not Authenticated");
			}				
		}
		else {
			request.getSession(true).setAttribute("auth", "true");
			request.setAttribute("message", "You are now authenticated.");
		}
		request.getRequestDispatcher("authTest.jsp").forward(request, response);
	}
}
