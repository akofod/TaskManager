package control;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataaccess.TaskManagerDAO;

@WebServlet("/UpdateProjectSettings")
public class UpdateProjectSettings extends HttpServlet {

	private static final long serialVersionUID = 5787735958799956539L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int project_id = Integer.parseInt(request.getParameter("project"));
		String action = request.getParameter("action");
		String message = "";
		
		if ("getdescription".equalsIgnoreCase(action)) {
			message = getDescription(project_id);
		}
		
		else if ("updateDescription".equalsIgnoreCase(action)) {
			String description = request.getParameter("description");
			message = updateDescription(project_id, description);
		}
		
		else if ("getDueDate".equalsIgnoreCase(action)) {
			message = getDueDate(project_id);
		}
		
		else if ("updateDueDate".equalsIgnoreCase(action)) {
			String newDate = request.getParameter("newDate");
			message = updateDueDate(project_id, newDate);
		}
		
		DataOutputStream out = new DataOutputStream(response.getOutputStream());
		out.writeBytes(message);
	}

	private String updateDescription(int project_id, String description) {
		String message = "";
		if ("".equals(description)) {
			message = "Description cannot be blank";
		}
		else {
			TaskManagerDAO dao = new TaskManagerDAO();
			int count = dao.updateProjectDescription(project_id, description);
			if (count != 1) {
				message = "There was an error with your request.";
			}
			else {
				message = "Description Successfully Updated.";
			}
			
		}
		return message;
	}

	private String getDescription(int project_id) {
		TaskManagerDAO dao = new TaskManagerDAO();
		String message = dao.getProjectDescription(project_id);
		return message;
	}
	
	private String updateDueDate(int project_id, String newDate) {
		String message = "";
		if ("".equals(newDate)) {
			message = "Description cannot be blank";
		}
		else {
			TaskManagerDAO dao = new TaskManagerDAO();
			int count = dao.updateProjectDueDate(project_id, newDate);
			if (count != 1) {
				message = "There was an error with your request.";
			}
			else {
				message = "Due Date Successfully Updated.";
			}
			
		}
		return message;
	}
	
	private String getDueDate(int project_id) {
		TaskManagerDAO dao = new TaskManagerDAO();
		String message = dao.getDueDate(project_id);
		return message;
	}
	
}
