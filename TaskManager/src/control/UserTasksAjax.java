package control;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataaccess.TaskManagerDAO;
@WebServlet("/UserTasksAjax")

public class UserTasksAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserTasksAjax() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String task = request.getParameter("task");	
		String status = request.getParameter("status");	
		if (status.equalsIgnoreCase("InProcess")) {
			status = "In Process";
		}
		int taskId = Integer.parseInt(task);
		TaskManagerDAO dao = new TaskManagerDAO();
		String message = "";
		if (dao.updateTaskStatus(taskId, status)) {
			message = "Status Update Successful.";
		}
		else {
			message = "Status Update Unsuccessful.";
		}
		DataOutputStream out = new DataOutputStream(response.getOutputStream());
		response.setContentType("text/plain");
		out.writeBytes(message);
	}
}
