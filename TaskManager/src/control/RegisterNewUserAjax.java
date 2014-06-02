package control;

import java.io.IOException;

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
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String password = request.getParameter("password");
		//TaskManagerDAO dao = new TaskManagerDAO();
		User user = new User();
		user.setId(email);
		user.setNickname(nickname);
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setPassword(password);
		
		//dao.createUser(user);
		
		request.getRequestDispatcher("authTest.jsp").forward(request, response);
	}
}
