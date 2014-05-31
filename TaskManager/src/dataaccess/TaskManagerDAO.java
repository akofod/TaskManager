package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
			ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/pbcart" );
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
			if (user.getNickname().equals(username) && user.getPassword().equals(password)) {
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
		User u = retrieveUser(user.getNickname());
		if (u != null) {
			return RECORD_EXISTS;
		}
		try
        {
            String sql = "INSERT INTO frank73_s14org.Users(nickname, firstname, " +
                    "lastname, password) VALUES (?,?,?,?) ";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getNickname());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            
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
            String sql = "INSERT INTO frank73_s14org.Projects(category_id, final_deadline) " + 
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
            String sql = "INSERT INTO frank73_s14org.Teams(description) " + 
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
            String sql = "INSERT INTO frank73_s14org.Categories(description) " + 
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
            String sql = "INSERT INTO frank73_s14org.Tasks(description, due_date, " +
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
	
	public User retrieveUser(int id) {
		return null;
	}
	
	public User retrieveUser(String nickname) {
		return null;
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
}
