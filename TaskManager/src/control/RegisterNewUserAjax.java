package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import dataaccess.TaskManagerDAO;
	
@WebServlet("/register.do")
public class RegisterNewUserAjax extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public RegisterNewUserAjax() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd;
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String password = request.getParameter("password");
		TaskManagerDAO dao = new TaskManagerDAO();
		User user = new User();
		
		user.setId(email);
		user.setNickname(nickname);
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setPassword(password);
		
		User search = dao.retrieveUser(email);
		if (email.equals(search.getId())) {
			request.setAttribute("error", "This email address is already registered. Please log in to your account or enter a different email address.");
			rd = request.getRequestDispatcher("registration.jsp");
		}
		else if(dao.isNicknameUsed(nickname)) {
			request.setAttribute("error", "That nickname is already being used. Please select a different nickname.");
			rd = request.getRequestDispatcher("registration.jsp");
		}
		else {
			dao.createUser(user);
			request.setAttribute("regSuccess", "You are now registered! Please login below.");
			rd = request.getRequestDispatcher("login.jsp");
		}
		
		rd.forward(request, response);
	}
}
