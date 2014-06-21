package control;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import dataaccess.TaskManagerDAO;

@WebServlet("/UpdateUserAjax")
public class UpdateUserAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserAjax() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String new_user_id = request.getParameter("new_user_id");
		String nickname = request.getParameter("nickname");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String cur_pass = request.getParameter("oldPass");
		String new_pass = request.getParameter("newPass");
		String new_pass_confirm = request.getParameter("newPassConfirm");
		String action = request.getParameter("reqAction");
		String message = "";
		TaskManagerDAO dao = new TaskManagerDAO();
		
		User currUser = (User) request.getSession(true).getAttribute("user");
		
		if (cur_pass == null || cur_pass.trim().length() == 0) {
			message = "Your current password is required for all update actions.";
		}
		else if (!dao.getSecurePassword(cur_pass, currUser.getSalt()).equals(currUser.getPassword())) {
			message = "Your current password entered is incorrect.  Please enter your current password.";
		}
		else if (action.equalsIgnoreCase("submitEmail")){
			message = dao.updateUserId(currUser.getId(), new_user_id);
			if (message.equalsIgnoreCase("Email updated successfully.")) {
				User authUser = dao.retrieveUser(new_user_id);
				request.getSession(true).setAttribute("user", authUser);
			}
		}
		else if (action.equalsIgnoreCase("submitNickname")){
			message = dao.updateUserNickname(currUser.getId(), nickname);	
			if (message.equalsIgnoreCase("Nickname updated successfully.")) {
				User authUser = dao.retrieveUser(currUser.getId());
				request.getSession(true).setAttribute("user", authUser);
			}
		}
		else if (action.equalsIgnoreCase("submitFirstName")){
			message = dao.updateUserFirstName(currUser.getId(), firstname);
			if (message.equalsIgnoreCase("First name updated successfully.")) {
				User authUser = dao.retrieveUser(currUser.getId());
				request.getSession(true).setAttribute("user", authUser);
			}
		}
		else if (action.equalsIgnoreCase("submitLastName")){
			message = dao.updateUserLastName(currUser.getId(), lastname);
			if (message.equalsIgnoreCase("Last name updated successfully.")) {
				User authUser = dao.retrieveUser(currUser.getId());
				request.getSession(true).setAttribute("user", authUser);
			}
		}
		else if (action.equalsIgnoreCase("submitPassword")){
			if (!new_pass.equals(new_pass_confirm)) {
				message = "The new passwords entered do not match.";
			}
			else {
				message = dao.updatePassword(currUser.getId(), new_pass);
				if (message.equalsIgnoreCase("Password updated successfully")) {
					User authUser = dao.retrieveUser(currUser.getId());
					request.getSession(true).setAttribute("user", authUser);
				}
			}
		}
		DataOutputStream out = new DataOutputStream(response.getOutputStream());
		response.setContentType("text/plain");
		out.writeBytes(message);
	}
}
