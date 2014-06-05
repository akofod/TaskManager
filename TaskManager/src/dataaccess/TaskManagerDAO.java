package dataaccess;

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
import model.Team;
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
	 * Explicitly close the database connection.
	 */
	public void close() {
		try {
			con.close();
		}
		catch(SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
		}
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
		if (user != null) {
			if (user.getId().equals(username) && user.getPassword().equals(password)) {
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
                    "lastname, password) VALUES (?,?,?,?,?) ";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getId());
            ps.setString(2, user.getNickname());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getPassword());
            
            ps.executeUpdate();
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
	public int createProject(int catId, Date deadline) {
		//TODO: Should a team also be assigned when a project is created?
		java.sql.Date sqlDate = new java.sql.Date(deadline.getTime());
		try
        {
            String sql = "INSERT INTO Projects(category_id, final_deadline) " + 
            		"VALUES (?,?) ";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, catId);
            ps.setDate(2, sqlDate);
            
            ps.executeUpdate();
            return SUCCESS;
        }
        catch(Exception e){
            System.out.println(e);
            return NO_RECORD;
        }
	}
	
	/**
	 * Create a new team.
	 * @param description - The description of the team.
	 * @return True if the team was created successfully,
	 * false otherwise.
	 */
	public int createTeam(String description) {
		try
        {
            String sql = "INSERT INTO Teams(description) " + 
            		"VALUES (?) ";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, description);
            
            ps.executeUpdate();
            return SUCCESS;
        }
        catch(Exception e){
            System.out.println(e);
            return NO_RECORD;
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

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, description);
            
            ps.executeUpdate();
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

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, task.getDescription());
            ps.setDate(2, sqlDate);
            ps.setString(3, task.getPriority());
            ps.setDouble(4, task.getTimeEstimate());
            ps.setDouble(5, task.getTimeCompleted());
            ps.setString(6, task.getStatus());
            ps.setInt(7, task.getProjectId());
            
            ps.executeUpdate();
            return SUCCESS;
        }
        catch(Exception e){
            System.out.println(e);
            return NO_RECORD;
        }
	}
	
	/**
	 * Adds a project to a team.
	 * @param project - The project to be added.
	 * @param team - The team to add the project to.
	 * @return True if the project was added successfully,
	 * false otherwise.
	 */
	public int addProjectToTeam(Project project, Team team) {
		return NO_RECORD; // TODO: Can a team also be added to a project?
	}
	
	/**
	 * Adds a user to a team.
	 * @param user - The user to be added.
	 * @param team - The team to add the user to.
	 * @return True if the user was added successfully,
	 * false otherwise.
	 */
	public int addUserToTeam(User user, Team team) {
		return NO_RECORD;
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
	
	public ArrayList<Task> retrieveTasks(Team team) {
		return null;
	}
	
	public ArrayList<Task> retrieveTasks(Project project) {
		return null;
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

            PreparedStatement s = con.prepareStatement(sql);
            s.setString(1, id);

            ResultSet rs = s.executeQuery();

            String user_id, nname, fname, lname, pass;
            if (rs.next()) {
            	 user_id = rs.getString("user_id");
                 nname = rs.getString("nickname");
                 fname = rs.getString("firstname");
                 lname = rs.getString("lastname");
                 pass = rs.getString("password");
                 
                 user.setId(user_id);
                 user.setNickname(nname);
                 user.setFirstName(fname);
                 user.setLastName(lname);
                 user.setPassword(pass);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
		return user;
	}
	
	public ArrayList<User> retrieveUsers(Project project) {
		return null;
	}
	
	public ArrayList<User> retrieveUsers(Team team) {
		return null;
	}
	
	public Project retrieveProject(int id) {
		return null;
	}
	
	public ArrayList<Project> retrieveProjects(Team team) {
		return null;
	}
	
	public ArrayList<Project> retrieveProjects(User user) {
		return null;
	}
	
	public Team retrieveTeam(int id) {
		return null;
	}
	
	public Category retrieveCategory(int id) {
		return null;
	}
	
	public Category retrieveCategory(String desc) {
		return null;
	}
	
	public ArrayList<Category> retrieveCategories() {
		return null;
	}
	
	public int updateUser(int user_id, String username, String firstName, String lastName,
			String email, String password) {
		return NO_RECORD;
	}
	
	public int updateTeam(int team_id, String description) {
		return NO_RECORD;
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
	
	public int updatePassword(String userName, String oldPass, String newPass) {
		if(authenticateUser(userName, oldPass)==1 && validatePassword(newPass) == 1)
		{	
			try
			{
				String sql = "Update users set password=? where nickname=?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, newPass);
				ps.setString(2, userName);
				ps.executeUpdate();
				return SUCCESS;
			}
			catch (Exception e){
				System.out.println(e);
	            return NO_RECORD;
			}
		}
		return NO_RECORD;
	}
	
	public int deleteUser(User user) {
		return NO_RECORD;
	}
	
	public int deleteTeam(Team team) {
		return NO_RECORD;
	}
	
	public int deleteProject(Project project) {
		return NO_RECORD;
	}
	
	public int deleteCategory(Category category) {
		return NO_RECORD;
	}
	
	public int deleteTask(Task task) {
		return NO_RECORD;
	}
	
	public int removeProjectFromTeam(Project project, Team team) {
		return NO_RECORD;
	}
	
	public int removeUserFromTeam(User user, Team team) {
		return NO_RECORD;
	}
	
	public int removeTaskFromUser(Task task, User user) {
		return NO_RECORD;
	}
	
	public int validatePassword(String strPass) {
		int passLength = strPass.trim().length();
		boolean hasUpper = false;
		boolean hasLower = false;
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
			if (hasUpper && hasLower)
			{
				return SUCCESS;
			}
		}
		return NO_RECORD;
	}
	
	public boolean isNicknameUsed(String nickname) {
		boolean used = false;
		try {
            String sql = "SELECT * FROM users WHERE nickname = ?";

            PreparedStatement s = con.prepareStatement(sql);
            s.setString(1, nickname);

            ResultSet rs = s.executeQuery();

            if (rs.next()) {
            	 used = true;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
		return used;
	}
}
