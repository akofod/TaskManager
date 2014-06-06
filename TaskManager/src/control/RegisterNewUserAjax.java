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
		System.out.println("Entered registration servlet");
		RequestDispatcher rd;
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String password = request.getParameter("password");
		TaskManagerDAO dao = new TaskManagerDAO();
		User user = new User();
		
		System.out.println("Recieved new user");
		System.out.println("email = " + email);
		System.out.println("nickname = " + nickname);
		System.out.println("firstname = " + firstname);
		System.out.println("lastname = " + lastname);
		System.out.println("password = " + password);
		
		user.setId(email);
		user.setNickname(nickname);
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setPassword(password);
		
		User search = dao.retrieveUser(email);
		if (email.equals(search.getId())) {
			request.setAttribute("error", "This email address is already registered. Please log in to your account or enter a different email address.");
			rd = request.getRequestDispatcher("registration.jsp");
			System.out.println("Email was already registered");
		}
		else if(dao.isNicknameUsed(nickname)) {
			request.setAttribute("error", "That nickname is already being used. Please select a different nickname.");
			rd = request.getRequestDispatcher("registration.jsp");
			System.out.println("Nickname was already registered");
		}
		else {
			System.out.println("User not found. Adding to database");
			dao.createUser(user);
			rd = request.getRequestDispatcher("login.jsp");
		}
		
		rd.forward(request, response);
	}
}
