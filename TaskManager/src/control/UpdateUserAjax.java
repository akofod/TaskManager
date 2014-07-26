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
		String new_user_id = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String cur_pass = request.getParameter("current_password");
		String new_pass = request.getParameter("password");
		String new_pass_confirm = request.getParameter("confirm");
		String action = request.getParameter("reqAction");
		String message = "";
		TaskManagerDAO dao = new TaskManagerDAO();
		
		User currUser = (User) request.getSession(true).getAttribute("user");
		
		
		if ("updateUserDetails".equals(request.getParameter("type"))) {
			if (dao.isNicknameUsed(nickname)) {
				message = "Nickname already in use.";
			}
			else if (nickname.length() < 6 || nickname.length() > 15) {
				message = "Nickname must be between 6 and 15 characters.";
			}
			else if (!nickname.matches("^[a-zA-Z0-9]{6,15}$")) {
				message = "Nickname may only contain letters or numbers.";
			}
			else {
				User newUser = new User();
				newUser.setUser_id(new_user_id);
				newUser.setNickname(nickname);
				newUser.setFirstName(firstname);
				newUser.setLastName(lastname);
				if (dao.updateUser(newUser, currUser) == 1) {
					currUser.setUser_id(new_user_id);
					currUser.setNickname(nickname);
					currUser.setFirstName(firstname);
					currUser.setLastName(lastname);
					message = "User Update Successful";
				}
			}
		}
		
		if ("updatePassword".equals(request.getParameter("type"))) {
			if (!dao.getSecurePassword(cur_pass, currUser.getSalt()).equals(currUser.getPassword())) {
				message += "Your current password entered is incorrect.  Please enter your current password.\n";
			}
			else if (!new_pass.equals(new_pass_confirm)) {
				message += "The new passwords entered do not match.";
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
