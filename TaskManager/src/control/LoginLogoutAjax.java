package control;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
		String remem = request.getParameter("rememberMe");
		String message = "";
		TaskManagerDAO dao = new TaskManagerDAO();
		if (action.equalsIgnoreCase("login"))
		{
			if (dao.authenticateUser(user, pass)==1){
				User authUser = dao.retrieveUser(user);
				request.getSession(true).setAttribute("user", authUser);
				String hashPass = authUser.getPassword();
				//cookie clean up
				deleteCookies(request, response);
				//create new cookies
				if (remem.equalsIgnoreCase("true")) {
					//set cookie for remember me functionality on next visit
					Cookie userId = new Cookie("user_id_TaskManager_Su14", user);
					Cookie userPass = new Cookie("user_pass_TaskManager_Su14", hashPass);
					//30 day life
					userId.setMaxAge(60*60*24*30); 
				    userPass.setMaxAge(60*60*24*30);
				    //add cookie
				    response.addCookie(userId);
					response.addCookie(userPass);
				}
				 
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
			deleteCookies(request, response);
			request.getSession(true).invalidate();
			message = "You have successfully logged out.";
		}
		DataOutputStream out = new DataOutputStream(response.getOutputStream());
		response.setContentType("text/plain");
		out.writeBytes(message);
	}
	
	public void deleteCookies (HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = null;
		Cookie[] cookies = null;
		cookies = request.getCookies();
		if(cookies != null) {
			for (int i = 0; i < cookies.length; i++){
				cookie = cookies[i];
				if (cookie.getName().equals("user_id_TaskManager_Su14")) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				if (cookie.getName().equals("user_pass_TaskManager_Su14")) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
	}
}
