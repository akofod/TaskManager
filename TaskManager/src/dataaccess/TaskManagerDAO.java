package dataaccess;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.Category;
import model.Project;
import model.Task;
import model.User;

public class TaskManagerDAO {
	private final int NO_RECORD = -1;
	private final int RECORD_EXISTS = 0;
	private final int SUCCESS = 1;
	private Connection con;
	DataSource ds;
	
	/**
	 * Constructor for TaskManagerDAO objects.
	 */
	public TaskManagerDAO() {
		getConnection();
	}
	
	
	/**
	 * Establishes connection with database.
	 * Tries to connect three times before throwing exception
	 * to allow for heavy database traffic.
	 * @return the database connection.
	 */
	public Connection getConnection() {
		try {
			Context cxt = new InitialContext();
			ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/TaskManager" );
			con = ds.getConnection();
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}

        return null;
	}
	
	/**
	 * Authenticates a users credentials.
	 * @param username The user's username.
	 * @param password The user's password.
	 * @return True if the user is in the database and the password
	 * is correct, false otherwise.
	 */
	public int authenticateUser(String username, String password) {
		User user = retrieveUser(username);
		if (user.getId() != null) {
			String hashPass = getSecurePassword(password, user.getSalt());
			if (user.getId().equals(username) && user.getPassword().equals(hashPass)) {
				return SUCCESS;
			}
			else {
				return RECORD_EXISTS;
			}
		}
		return NO_RECORD;		
	}
	
	/**
	 * Create a user
	 * @param String username - the nick name for this user
	 * @param String firstName - user's first name
	 * @param String lastName - user's last name
	 * @param String email - user's email address
	 * @param String password - the password entered by the user
	 * @return True if user was created, false otherwise
	 */
	public int createUser(User user) {
		User u = retrieveUser(user.getId());
		if (u.getId() != null) {
			return RECORD_EXISTS;
		}
		try
        {
            String sql = "INSERT INTO users(user_id, nickname, firstname, " +
                    "lastname, password, salt) VALUES (?,?,?,?,?,?) ";
            
            getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            String salt = getSalt();
            String hashPass = getSecurePassword(user.getPassword(), salt);
            
            ps.setString(1, user.getId());
            ps.setString(2, user.getNickname());
            ps.setString(3, user.getFirstname());
            ps.setString(4, user.getLastname());
            ps.setString(5, hashPass);
            ps.setString(6, salt);
            
            ps.executeUpdate();
            ps.close();
            con.close();
            return SUCCESS;
        }
        catch(Exception e){
            System.out.println(e);
            return NO_RECORD;
        }
	}
	
	/**
	 * Create a new project.
	 * @param description - Description of the project.
	 * @param catId - Id for the project category.
	 * @param deadline - The final deadline for the project.
	 * @return True if the project was created successfully,
	 * false otherwise.
	 */
	public int createProject(Project project) {
		java.sql.Date sqlDate = new java.sql.Date(project.getFinalDeadline().getTime());
		try
        {
            String sql = "INSERT INTO Projects(category_id, final_deadline, description) " + 
            		"VALUES (?,?,?) ";
            getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, project.getCategoryId());
            ps.setDate(2, sqlDate);
            ps.setString(3, project.getDescription());
            
            ps.executeUpdate();
            ps.close();
            con.close();
            return SUCCESS;
        }
        catch(Exception e){
            System.out.println(e);
            return NO_RECORD;
        }
	}
	
	public int addUserToProject(User user, Project project) {
		boolean alreadyAssigned = false;
		try
		{
			String sqlCheck = "Select project_id from userproject where user_id=?";
			getConnection();
			PreparedStatement psCheck = con.prepareStatement(sqlCheck);	
			psCheck.setString(1, user.getId());
			ResultSet rs = psCheck.executeQuery();
			
			while (rs.next()) {
				if (rs.getInt("project_id") == project.getId()) {
					alreadyAssigned = true;
					break;
				}
			}
			rs.close();
			psCheck.close();
			con.close();
		}
		catch (Exception e){
			System.out.println(e);
		}
		
		if (!alreadyAssigned) {
			try
        	{
            	String sql = "INSERT INTO userproject(user_id, project_id) " + 
            		"VALUES (?,?) ";
            	getConnection();
            	PreparedStatement ps = con.prepareStatement(sql);

            	ps.setString(1, user.getId());
            	ps.setInt(2, project.getId());
            
            	ps.executeUpdate();
            	ps.close();
            	con.close();
            	return SUCCESS;
        	}
        	catch(Exception e){
            	System.out.println(e);
            	return NO_RECORD;
        	}
		}
		else {
			return RECORD_EXISTS;
		}
	}
	
	/**
	 * Create a new category
	 * @param desctiption - The description of the category.
	 * @return True if the category was created successfully,
	 * false otherwise.
	 */
	public int createCategory(String description) {
		try
        {
            String sql = "INSERT INTO Categories(description) " + 
            		"VALUES (?) ";
            getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, description);
            
            ps.executeUpdate();
            ps.close();
            con.close();
            return SUCCESS;
        }
        catch(Exception e){
            System.out.println(e);
            return NO_RECORD;
        }
	}
	
	/**
	 * Creates a new task.
	 * @param description - Description of the task.
	 * @param dueDate - The date the task is due.
	 * @param priority - The priority of the task.
	 * @param timeEst - The time estimated for the task.
	 * @param projectId - The project that the task is associated with.
	 * @param userId - The user assigned to the task.
	 * @return True if the task was created successfully,
	 * false otherwise.
	 */
	public int createTask(Task task) {
		java.sql.Date sqlDate = new java.sql.Date(task.getDueDate().getTime());
		try
        {
            String sql = "INSERT INTO Tasks(description, due_date, " +
                    "priority, time_estimate, time_completed, status, project_id) VALUES (?,?,?,?,?,?,?) ";
            getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, task.getDescription());
            ps.setDate(2, sqlDate);
            ps.setString(3, task.getPriority());
            ps.setDouble(4, task.getTimeEstimate());
            ps.setDouble(5, task.getTimeCompleted());
            ps.setString(6, task.getStatus());
            ps.setInt(7, task.getProjectId());
            
            ps.executeUpdate();
            ps.close();
            con.close();
            return SUCCESS;
        }
        catch(Exception e){
            System.out.println(e);
            return NO_RECORD;
        }
	}
	
	public int findMaxTask () {
		int result = -1;
		try
        {
            String sql = "Select max(task_id) as task_id from tasks";
            getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
            	result =  rs.getInt("task_id");
            }
            
            rs.close();
            ps.close();
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
		return result;
	}
	
	public int findMaxProject () {
		
		int result = -1;
		
		try
        {
            String sql = "Select max(project_id) as project_id from projects";
            getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
            	result =  rs.getInt("project_id");
            }
            
            rs.close();
            ps.close();
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
		return result;
	}
	
	/**
	 * Assigns a task to a user.
	 * @param task - The task to be assigned.
	 * @param user - The user to assign the task to.
	 * @return True if the task was added successfully,
	 * false otherwise.
	 */
	public int addTaskToUser(Task task, User user) {
		return NO_RECORD;
	}
	
	/**
	 * Retrieves all tasks for specified user.
	 * @param user -  The user
	 * @return All tasks for user
	 */
	public ArrayList<Task> retrieveTasks(User user) {
		return null;
	}
	
	public ArrayList<Task> retrieveTasks(Project project) {
		ArrayList<Task> tasks = new ArrayList<Task>();
		try {
            String sql = "SELECT * FROM tasks WHERE project_id = ?";
            getConnection();
            PreparedStatement s = con.prepareStatement(sql);
            s.setInt(1, project.getId());

            ResultSet rs = s.executeQuery();

            while (rs.next()) {
            	Task task = new Task();
            	task.setId(rs.getInt("task_id"));
            	task.setDescription(rs.getString("description"));
            	task.setDueDate(rs.getDate("due_date"));
                task.setPriority(rs.getString("priority"));
                task.setProjectId(project.getId());
                task.setStatus(rs.getString("status"));
                task.setTimeCompcompleted(rs.getDouble("time_completed"));
                task.setTimeEstimate(rs.getDouble("time_estimate"));
                tasks.add(task);
            }
            
            rs.close();
            s.close();
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
		return tasks;
	}
	
	public ArrayList<Task> retrieveTasks(User user, Category cat) {
		return null;
	}
	
	/**
	 * Retrieves a User object from the database.
	 * @param id The user_id to retrieve.
	 * @return The specified User object.
	 */
	public User retrieveUser(String id) {
		User user = new User();
		try {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            getConnection();
            PreparedStatement s = con.prepareStatement(sql);
            s.setString(1, id);

            ResultSet rs = s.executeQuery();

            String user_id, nname, fname, lname, pass, salt;
            if (rs.next()) {
            	 user_id = rs.getString("user_id");
                 nname = rs.getString("nickname");
                 fname = rs.getString("firstname");
                 lname = rs.getString("lastname");
                 pass = rs.getString("password");
                 salt = rs.getString("salt");
                 
                 user.setId(user_id);
                 user.setNickname(nname);
                 user.setFirstName(fname);
                 user.setLastName(lname);
                 user.setPassword(pass);
                 user.setSalt(salt);
            }
            
            rs.close();
            s.close();
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
		return user;
	}
	
	public User retrieveUserByNickname(String nickname) {
		User user = new User();
		try {
            String sql = "SELECT * FROM users WHERE nickname = ?";
            getConnection();
            PreparedStatement s = con.prepareStatement(sql);
            s.setString(1, nickname);

            ResultSet rs = s.executeQuery();

            String user_id, nname, fname, lname, pass, salt;
            if (rs.next()) {
            	 user_id = rs.getString("user_id");
                 nname = rs.getString("nickname");
                 fname = rs.getString("firstname");
                 lname = rs.getString("lastname");
                 pass = rs.getString("password");
                 salt = rs.getString("salt");
                 
                 user.setId(user_id);
                 user.setNickname(nname);
                 user.setFirstName(fname);
                 user.setLastName(lname);
                 user.setPassword(pass);
                 user.setSalt(salt);
            }
            rs.close();
            s.close();
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
		return user;
	}
	
	public ArrayList<User> retrieveUsers(Project project) {
		return null;
	}
	
	public Project retrieveProject(int id) {
		Project project = new Project();
		try {
            String sql = "SELECT * FROM projects WHERE project_id = ?";
            getConnection();
            PreparedStatement s = con.prepareStatement(sql);
            s.setInt(1, id);

            ResultSet rs = s.executeQuery();

            if (rs.next()) {
            	project.setId(rs.getInt("project_id"));
            	project.setDescription("Need to add project descriptions to table");
            	project.setFinalDeadline(rs.getDate("final_deadline"));
            	project.setCategoryId(rs.getInt("category_id"));
            	project.setProjectTasks(retrieveTasks(project));
            }
            
            rs.close();
            s.close();
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
		return project;
	}
	
	public ArrayList<Project> retrieveProjects(User user) {
		ArrayList<Project> projects = new ArrayList<Project>();
		try {
            String sql = "SELECT * FROM projects WHERE project_id in "
            		+ "(SELECT project_id FROM userproject WHERE user_id = ?)";
            getConnection();
            PreparedStatement s = con.prepareStatement(sql);
            s.setString(1, user.getId());

            ResultSet rs = s.executeQuery();

            while (rs.next()) {
            	Project project = new Project();
            	project.setId(rs.getInt("project_id"));
            	project.setDescription(rs.getString("description"));
            	project.setFinalDeadline(rs.getDate("final_deadline"));
            	project.setCategoryId(rs.getInt("category_id"));
            	project.setProjectTasks(retrieveTasks(project));
                projects.add(project);
            }
            rs.close();
            s.close();
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
		return projects;
	}
	
	public Category retrieveCategory(int id) {
		return null;
	}
	
	public Category retrieveCategory(String desc) {
		Category cat = new Category();
		try {
			String sql = "SELECT * FROM categories WHERE description = ?";
			getConnection();
            PreparedStatement s = con.prepareStatement(sql);
            s.setString(1, desc);

            ResultSet rs = s.executeQuery();

            while (rs.next()) {
            	cat.setId(rs.getInt("category_id"));
            	cat.setDescription(rs.getString("description"));
            }
            rs.close();
            s.close();
            con.close();
            
		}
		catch(Exception e) {
			System.out.println(e);
		}

		return cat;
	}
	
	public ArrayList<Category> retrieveCategories() {
		return null;
	}
	
	public ArrayList<Category> retrieveCategories(String term) {
		ArrayList<Category> list = new ArrayList<Category>();
		try {
			String sql = "SELECT * FROM categories WHERE description LIKE ?";
			getConnection();
            PreparedStatement s = con.prepareStatement(sql);
            s.setString(1,"%" + term + "%");

            ResultSet rs = s.executeQuery();

            while (rs.next()) {
            	Category cat = new Category();
            	cat.setId(rs.getInt("category_id"));
            	cat.setDescription(rs.getString("description"));
            	list.add(cat);
            }
            rs.close();
            s.close();
            con.close();
            
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	public int updateUser(int user_id, String username, String firstName, String lastName,
			String email, String password) {
		return NO_RECORD;
	}
	
	public int updateUser(User newUser, User oldUser) {
		
		int result = 0;
		
		try {
			String sql = "Update Users set user_id = ?, nickname = ?, firstname = ?, lastname=? where user_id = ?";
			getConnection();
			PreparedStatement s = con.prepareStatement(sql);
			s.setString(1, newUser.getUser_id());
			s.setString(2, newUser.getNickname());
			s.setString(3, newUser.getFirstname());
			s.setString(4, newUser.getLastname());
			s.setString(5, oldUser.getUser_id());
			
			result = s.executeUpdate();
			
			s.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String updateUserId(String oldId, String newId) {
		String result = "Email not updated.";
		if (newId == null || newId.trim().length() == 0) {
			return "Please enter a valid new email address";
		}
		else if (oldId.equals(newId)) {
			return "Email is unchanged.  No update necessary.";
		}
		else if (!validateEmail(newId)){
			return "Email address not formatted correctly.  Please try again.";
		}
		else if (isUserIdUsed(newId)) {
			return "Email is already used.  Please try a different email.";
		}
		else {
			try
			{
				String sql = "Update users set user_id=? where user_id=?";
				getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, newId);
				ps.setString(2, oldId);
				ArrayList<Integer> tasks = selectUserTask(oldId);
				deleteUserTask(oldId);
				ps.executeUpdate();
				insertUserTask(newId, tasks);
				result = "Email updated successfully.";
				ps.close();
				con.close();
			}
			catch (Exception e){
				System.out.println(e);
			}
		}
		return result;
	}
	
	public String updateUserNickname(String userId, String newNickname) {
		String result = "Nickname not updated.";
		String nickname = "";
		
		if (newNickname != null && newNickname.trim().length() > 0) {
			nickname = newNickname;
		}
		
		if (nickname.equals(retrieveUser(userId).getNickname())) {
			return "Nickname is unchanged.  No update necessary.";
		}
		else {
			try
			{
				String sql = "Update users set nickname=? where user_id=?";
				getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, nickname);
				ps.setString(2, userId);
				ps.executeUpdate();
				result = "Nickname updated successfully.";
				ps.close();
				con.close();
			}
			catch (Exception e){
				System.out.println(e);
			}
		}
		return result;
	}
	
	public String updateUserFirstName(String userId, String newFirstName) {
		String result = "First name not updated.";
		String firstname = "";
		
		if (newFirstName != null && newFirstName.trim().length() > 0) {
			firstname = newFirstName;
		}
		
		if (firstname.equals(retrieveUser(userId).getFirstname())) {
			return "First name is unchanged.  No update necessary.";
		}
		else {
			try {
				String sql = "Update users set firstname=? where user_id=?";
				getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, newFirstName);
				ps.setString(2, userId);
				ps.executeUpdate();
				result = "First name updated successfully.";
				ps.close();
				con.close();
			}
			catch (Exception e){
				System.out.println(e);
			}
		}
		return result;
	}
	
	public String updateUserLastName(String userId, String newLastName) {
		String result = "Last name not updated.";
		String lastname = "";
		
		if (newLastName != null && newLastName.trim().length() > 0) {
			lastname = newLastName;
		}
		
		if (lastname.equals(retrieveUser(userId).getLastname())) {
			return "Last name is unchanged.  No update necessary.";
		}
		else {
			try {
				String sql = "Update users set lastname=? where user_id=?";
				getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, lastname);
				ps.setString(2, userId);
				ps.executeUpdate();
				result = "Last name updated successfully.";
				ps.close();
				con.close();
			}
			catch (Exception e){
				System.out.println(e);
			}
		}
		return result;
	}
	
	public String updatePassword(String userName, String newPass) {
		String result = "Password not updated.";
		if (newPass == null || newPass.trim().length() == 0) {
			return "New password missing.  Please try again.";
		}
		String currentPass = retrieveUser(userName).getPassword();
		String newPassCheck = getSecurePassword(newPass, retrieveUser(userName).getSalt()); 
		if (currentPass.equals(newPassCheck)) {
			return "Password is unchanged.  No update necessary.";
		}
		else {
			if(validatePassword(newPass))
			{	
				try
				{
					String sql = "Update users set password=?, salt=? where user_id=?";
					getConnection();
					PreparedStatement ps = con.prepareStatement(sql);
					String salt = getSalt();
					String newPassSecure = getSecurePassword(newPass, salt);
					ps.setString(1, newPassSecure);
					ps.setString(2, salt);
					ps.setString(3, userName);
					ps.executeUpdate();
					result = "Password updated successfully";
					ps.close();
					con.close();
				}
				catch (Exception e){
					System.out.println(e);
				}
			}
			else {
				result = "Password is invalid.  Password must be at least 8 characters in length, contain at least one upper case character, ";
				result += "contain at least one lower case character, contain at least one numeric character, and contain no spaces.";
			}
		}
		return result;
	}
	
	public ArrayList<Integer> selectUserTask(String userId) {
		ArrayList<Integer> tasks = new ArrayList<Integer>();
		try
		{
			String sqlTask = "Select task_id from usertask where user_id=?";
			getConnection();
			PreparedStatement ps0 = con.prepareStatement(sqlTask);	
			ps0.setString(1, userId);
			ResultSet rs = ps0.executeQuery();
			
			while (rs.next()) {
				tasks.add(new Integer(rs.getInt("task_id")));
			}
			rs.close();
			ps0.close();
			con.close();
		}
		catch (Exception e){
			System.out.println(e);
		}
		return tasks;
	}
	
	public void deleteUserTask(String userId) {
		try
		{
			String sqlDelete = "Delete from usertask where user_id=?";
			getConnection();
			PreparedStatement ps1 = con.prepareStatement(sqlDelete);
			ps1.setString(1, userId);
			ps1.executeUpdate();
			ps1.close();
			con.close();
		}
		catch (Exception e){
			System.out.println(e);
		}
	}
	
	public void insertUserTask(String userId, ArrayList<Integer> taskList) {
		try
		{	
			for (Integer i : taskList) {
				String sqlInsert = "Insert into usertask values (?,?)";
				getConnection();
				PreparedStatement ps = con.prepareStatement(sqlInsert);
				ps.setString(1, userId);
				ps.setInt(2, i);
				ps.executeUpdate();
				ps.close();
				con.close();
			}
		}
		catch (Exception e){
			System.out.println(e);
		}
	}
	
	public void insertUserTask(String userId, int task) {
		try
		{	
			String sqlInsert = "Insert into usertask values (?,?)";
			getConnection();
			PreparedStatement ps = con.prepareStatement(sqlInsert);
			ps.setString(1, userId);
			ps.setInt(2, task);
			ps.executeUpdate();
			ps.close();
			con.close();
		}
		catch (Exception e){
			System.out.println(e);
		}
	}
	
	public int updateProject(int project_id, String description, int catId, Date deadline) {
		return NO_RECORD;
	}
	
	public int updateCategory(int category_id, String description) {
		return NO_RECORD;
	}
	
	public int updateTask(int task_id, String description, Date dueDate, int priority,
			Date timeEst, int projectId, int userId) {
		return NO_RECORD;
	}
	
	public int deleteUser(User user) {
		return NO_RECORD;
	}
	
	public boolean deleteProject(Project project) {
		
		ArrayList<Task> projectTasks = retrieveTasks(project);
		for(Task t : projectTasks) {
			deleteTask(t.getId());
		}
		if (removeUserFromProject(project)) {
			try
			{
				String sql = "Delete from projects where project_id=?";
				getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, project.getProject_id());
				ps.executeUpdate();
				ps.close();
				con.close();
				return true;
			}
			catch (Exception e){
				System.out.println(e);
			}
		}
		return false;
	}
	
	public int deleteCategory(Category category) {
		return NO_RECORD;
	}
	
	public int deleteTask(Task task) {
		return NO_RECORD;
	}
	
	public boolean deleteTask(int task) {
		if (deleteUserTask(task)) {
			try
			{
				String sqlDelete = "Delete from tasks where task_id=?";
				getConnection();
				PreparedStatement ps1 = con.prepareStatement(sqlDelete);
				ps1.setInt(1, task);
				ps1.executeUpdate();
				ps1.close();
				con.close();
				return true;
			}
			catch (Exception e){
				System.out.println(e);
			}
		}
		return false;
	}
	
	public boolean deleteUserTask(int task) {
		try
		{
			String sqlDelete = "Delete from usertask where task_id=?";
			getConnection();
			PreparedStatement ps1 = con.prepareStatement(sqlDelete);
			ps1.setInt(1, task);
			ps1.executeUpdate();
			ps1.close();
			con.close();
			return true;
		}
		catch (Exception e){
			System.out.println(e);
		}
		return false;
	}
	
	public boolean removeUserFromProject(Project project) {
		try
		{
			String sqlDelete = "Delete from userproject where project_id=?";
			getConnection();
			PreparedStatement ps1 = con.prepareStatement(sqlDelete);
			ps1.setInt(1, project.getId());
			ps1.executeUpdate();
			ps1.close();
			con.close();
			return true;
		}
		catch (Exception e){
			System.out.println(e);
		}
		return false;
	}
	
	public int removeTaskFromUser(Task task, User user) {
		return NO_RECORD;
	}
	
	public boolean validateEmail(String email) {
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	    return email.matches(EMAIL_REGEX);
	}
	
	public boolean validatePassword(String strPass) {
		int passLength = strPass.trim().length();
		boolean hasUpper = false;
		boolean hasLower = false;
		boolean hasNumeric = false;
		int hasSpace = strPass.indexOf(" ");
		if (passLength > 7 && hasSpace == -1)
		{
			for(int i=0; i<passLength; i++) {
		        if(Character.isUpperCase(strPass.charAt(i))) {
		            hasUpper = true;
		        }
		    }
			for(int i=0; i<passLength; i++) {
		        if(Character.isLowerCase(strPass.charAt(i))) {
		            hasLower = true;
		        }
		    }
			for(int i=0; i<passLength; i++) {
		        if(Character.isDigit(strPass.charAt(i))) {
		            hasNumeric = true;
		        }
		    }
			if (hasUpper && hasLower && hasNumeric)
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isNicknameUsed(String nickname) {
		boolean used = false;
		try {
            String sql = "SELECT * FROM users WHERE nickname = ?";
            getConnection();
            PreparedStatement s = con.prepareStatement(sql);
            s.setString(1, nickname);

            ResultSet rs = s.executeQuery();

            if (rs.next()) {
            	 used = true;
            }
            rs.close();
            s.close();
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
		return used;
	}
	
	public boolean isUserIdUsed(String userId) {
		boolean used = false;
		try {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            getConnection();
            PreparedStatement s = con.prepareStatement(sql);
            s.setString(1, userId);

            ResultSet rs = s.executeQuery();

            if (rs.next()) {
            	 used = true;
            }
            rs.close();
            s.close();
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
		return used;
	}
	
	public String getSecurePassword(String passwordToHash, String salt)
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
	
	public String getSalt() throws Exception
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }
	
	/**
	 * Returns a list of all email addresses and nicknames
	 * that begin with the string passed in.
	 * @param name The string passed in from the search form.
	 * @return The list of email addresses and nicknames.
	 */
	public ArrayList<String> getUserList(String name) {
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			String sql = "SELECT * FROM users WHERE user_id LIKE ?";
			getConnection();
            PreparedStatement s = con.prepareStatement(sql);
            s.setString(1, name + "%");

            ResultSet rs = s.executeQuery();
            
            while (rs.next()) {
            	String user = rs.getString("user_id");
            	list.add(user);
            }
            rs.close();
            s.close();
            con.close();
            
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		try {
			String sql = "SELECT * FROM users WHERE nickname LIKE ?";
			getConnection();
            PreparedStatement s = con.prepareStatement(sql);
            s.setString(1, name + "%");

            ResultSet rs = s.executeQuery();
            
            while (rs.next()) {
            	String user = rs.getString("nickname");
            	list.add(user);
            }
            rs.close();
            s.close();
            con.close();
            
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return list;
	}

	
}
