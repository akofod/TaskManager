package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import dataaccess.TaskManagerDAO;

/**
 * Servlet implementation class SearchUsersAjax
 */
@WebServlet("/searchusers.do")
public class SearchUsersAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchUsersAjax() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entered doPost() in SearchUsersAjax");
		RequestDispatcher rd;
		ArrayList<String> userList = new ArrayList<String>();
		String searchName = request.getParameter("user");
		TaskManagerDAO dao = new TaskManagerDAO();
		User user = new User();
		
		userList = dao.getUserList(searchName);
		for (String name : userList) {
			System.out.println(name);
		}
	}

}
