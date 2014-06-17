package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import model.Category;
import model.Project;
import model.User;
import dataaccess.TaskManagerDAO;

/**
 * Servlet implementation class SearchUsersAjax
 */
@WebServlet("/createProject.do")
public class CreateNewProjectAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateNewProjectAjax() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        
        JSONObject arrayObj = new JSONObject();
        
        String query = request.getParameter("term");
        System.out.println(query);
        
        TaskManagerDAO dao = new TaskManagerDAO();
        ArrayList<Category> categories = dao.retrieveCategories(query);
        
        for(Category cat : categories) {
            arrayObj.put(cat.getId(), cat.getDescription());
        }
        
        out.println(arrayObj.toString());
        out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		String name = request.getParameter("projectName");
		String category = request.getParameter("category");
		String deadline = request.getParameter("deadline");
		TaskManagerDAO dao = new TaskManagerDAO();
		int cat_id;
		System.out.println("Project: " + name + ", " + category + ", " + deadline);
		
		Category checkCat = dao.retrieveCategory(category);
		
		if (checkCat.getId() == 0) {
			dao.createCategory(category);
			cat_id = dao.retrieveCategory(category).getId();
		}
		else {
			cat_id = checkCat.getId();
		}
		
		Project project = new Project();
		project.setCategoryId(cat_id);
		project.setDescription(name);
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date due_date = null;
		try {
			due_date = formatter.parse(deadline);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}		 
		project.setFinalDeadline(due_date);
		
		if (dao.createProject(project) == 1) {
			rd = request.getRequestDispatcher("index.jsp");
		}
		else {
			request.setAttribute("error", "There was a problem creating your project. Please try again.");
			rd = request.getRequestDispatcher("createproject.jsp");
		}
		
		rd.forward(request, response);
		
		
	}

}
