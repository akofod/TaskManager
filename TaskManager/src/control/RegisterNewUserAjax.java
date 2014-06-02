package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import dataaccess.TaskManagerDAO;

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
		//TODO: DAO connection is not working
		//Throws error: javax.naming.NameNotFoundException: Name [jdbc/TaskManager] is not bound in this Context. Unable to find [jdbc].
		//Error originates at TaskManagerDAO line 46.
		TaskManagerDAO dao = new TaskManagerDAO();
		User user = new User();
		user.setId(email);
		user.setNickname(nickname);
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setPassword(password);
		
		dao.createUser(user);
		
		User search = dao.retrieveUser(email);
		if (search.getId().equals(email)) {
			request.setAttribute("error", "This email address is already registered.");
			rd = request.getRequestDispatcher("registration.jsp");
		}
		else {
			rd = request.getRequestDispatcher("authTest.jsp");
		}
		
		rd.forward(request, response);
	}
}
