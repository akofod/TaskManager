package control;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Project;
import dataaccess.TaskManagerDAO;

@WebServlet("/DeleteProjectAjax")

public class DeleteProjectAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public DeleteProjectAjax() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int project_id = Integer.parseInt(request.getParameter("project"));
		String message = "";
		TaskManagerDAO dao = new TaskManagerDAO();
		Project project = dao.retrieveProject(project_id);
		if (dao.deleteProject(project)) {
			message = "Project deleted.";
		}
		else {
			message = "Project delete error.";
		}
		DataOutputStream out = new DataOutputStream(response.getOutputStream());
		response.setContentType("text/plain");
		out.writeBytes(message);
	}

}
