package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dataaccess.TaskManagerDAO;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class AutoCompleteAjax
 */
@WebServlet("/autocomplete.ajax")
public class AutoCompleteAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AutoCompleteAjax() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Recieved POST request in AutoCompleteAjax");
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        
        JSONObject arrayObj = new JSONObject();
        
        String query = request.getParameter("term");
        System.out.println(query);
        
        TaskManagerDAO dao = new TaskManagerDAO();
        ArrayList<String> users = dao.getUserList(query);
        
        for(String user : users) {
            arrayObj.put(user, user);
        }
        
        out.println(arrayObj.toString());
        out.close();
	}

}
