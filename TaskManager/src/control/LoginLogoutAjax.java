package control;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import dataaccess.TaskManagerDAO;
@WebServlet("/LoginLogoutAjax")
public class LoginLogoutAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginLogoutAjax() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");	
		String pass = request.getParameter("password");	
		String action = request.getParameter("reqAction");	
		String remem = request.getParameter("rememberMe");	//still need to implement this portion
		String message = "";
		TaskManagerDAO dao = new TaskManagerDAO();
		if (action.equalsIgnoreCase("login"))
		{
			if (dao.authenticateUser(user, pass)==1){
				User authUser = dao.retrieveUser(user);
				request.getSession(true).setAttribute("user", authUser);
				message = "You have successfully logged in, " + user;
			}
			else
			{
				request.getSession(true).invalidate();
				message = "Log in unsuccessful, please try again.";
			}
		}
		else
		{
			request.getSession(true).invalidate();
			message = "You have successfully logged out.";
		}
		DataOutputStream out = new DataOutputStream(response.getOutputStream());
		response.setContentType("text/plain");
		out.writeBytes(message);
	}
}
