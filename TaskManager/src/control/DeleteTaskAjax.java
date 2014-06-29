package control;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataaccess.TaskManagerDAO;
@WebServlet("/DeleteTaskAjax")

public class DeleteTaskAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteTaskAjax() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int task = Integer.parseInt(request.getParameter("task"));
		String message = "";	
		TaskManagerDAO dao = new TaskManagerDAO();
		if (dao.deleteTask(task)) {
			message = "Task deleted.";
		}
		else {
			message = "Task delete error.";
		}
		DataOutputStream out = new DataOutputStream(response.getOutputStream());
		response.setContentType("text/plain");
		out.writeBytes(message);
	}
}
