package control;

import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Task;
import dataaccess.TaskManagerDAO;

/**
 * Servlet implementation class UpdateTaskAjax
 */
@WebServlet("/UpdateTaskAjax")
public class UpdateTaskAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTaskAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int task_id = Integer.parseInt(request.getParameter("task_id"));
		String desc = request.getParameter("desc");	
		String priority = request.getParameter("priority");	
		String status = request.getParameter("status");	
		String estimate = request.getParameter("estimate");
		String completed = request.getParameter("completed");
		String due = request.getParameter("due");
		int project = Integer.parseInt(request.getParameter("project"));
		String message = "";
		double dblEstimate = 0;
		double dblCompleted = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
		TaskManagerDAO dao = new TaskManagerDAO();
		boolean goodDate = true;
		Date due_date = null;
		
		try {
			due_date = formatter.parse(due);
		}
		catch (Exception e) {
			goodDate = false;
		}
		if (!goodDate){
			try {
				due_date = formatter2.parse(due);
				goodDate = true;
			}
			catch (Exception e) {
				goodDate = false;
			}
		}
		try {
			dblEstimate = Double.parseDouble(estimate);
		}
		catch (Exception e) {
			dblEstimate = -1;
		}
		
		try {
			dblCompleted = Double.parseDouble(completed);
		}
		catch (Exception e) {
			dblCompleted = -1;
		}
		
		if (desc.trim().length() == 0) {
			message = "Please enter a task description.";
		}
		else if (dblEstimate < 0) {
			message = "Please enter a valid time (in hours) estimate.";
		}
		else if (dblCompleted < 0) {
			message = "Please enter a valid time (in hours) completed.";		
		}
		else if (!goodDate) {
			message = "Please select a date due.";
		}
		else {
			Task task = new Task();
			task.setId(task_id);
			task.setDescription(desc);
			task.setDueDate(due_date);
			task.setPriority(priority);
			task.setStatus(status);
			task.setProjectId(project);
			task.setTimeEstimate(dblEstimate);
			task.setTimeCompcompleted(dblCompleted);
			if (dao.updateTask(task) == 1) {
				message = "Task has been updated.";
			}
			else {
				message = "Task addition unsuccessful.";
			}		
		}
		DataOutputStream out = new DataOutputStream(response.getOutputStream());
		response.setContentType("text/plain");
		out.writeBytes(message);
	}

}
