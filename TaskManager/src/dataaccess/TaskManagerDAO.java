package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.Category;
import model.Project;
import model.Task;
import model.Team;
import model.User;

public class TaskManagerDAO {
	private String userid;      //TODO: We will need to determine how we want to
	private String password;    //get the userid, password and url into the DAO.
	private String url;
	private Connection con;
	
	/**
	 * Constructor for TaskManagerDAO objects.
	 */
	public TaskManagerDAO() {
		getConnection();
	}
	
	/**
	 * Establishes connection with database.
	 * @return the database connection.
	 */
	public Connection getConnection() {
		try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (java.lang.ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: " + e.getMessage());
        }

        try {
            con = DriverManager.getConnection(url, userid, password);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }

        return con;
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
	public Boolean authenticateUser(String username, String password) {		
		return false;
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
	public Boolean createUser(String username, String firstName, String lastName,
			String email, String password) {
		return false;
	}
	
	/**
	 * Create a new project.
	 * @param description - Description of the project.
	 * @param catId - Id for the project category.
	 * @param deadline - The final deadline for the project.
	 * @return True if the project was created successfully,
	 * false otherwise.
	 */
	public Boolean createProject(String description, int catId, Date deadline) {
		return false; //TODO: Should a team also be assigned when a project is created?
	}
	
	/**
	 * Create a new team.
	 * @param description - The description of the team.
	 * @return True if the team was created successfully,
	 * false otherwise.
	 */
	public Boolean createTeam(String description) {
		return false;
	}
	
	/**
	 * Create a new category
	 * @param desctiption - The description of the category.
	 * @return True if the category was created successfully,
	 * false otherwise.
	 */
	public Boolean createCategory(String desctiption) {
		return false;
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
	public Boolean createTask(String description, Date dueDate, int priority,
			Date timeEst, int projectId, int userId) {
		return false;
	}
	
	/**
	 * Adds a project to a team.
	 * @param project - The project to be added.
	 * @param team - The team to add the project to.
	 * @return True if the project was added successfully,
	 * false otherwise.
	 */
	public Boolean addProjectToTeam(Project project, Team team) {
		return false; // TODO: Can a team also be added to a project?
	}
	
	/**
	 * Adds a user to a team.
	 * @param user - The user to be added.
	 * @param team - The team to add the user to.
	 * @return True if the user was added successfully,
	 * false otherwise.
	 */
	public Boolean addUserToTeam(User user, Team team) {
		return false;
	}
	
	/**
	 * Assigns a task to a user.
	 * @param task - The task to be assigned.
	 * @param user - The user to assign the task to.
	 * @return True if the task was added successfully,
	 * false otherwise.
	 */
	public Boolean addTaskToUser(Task task, User user) {
		return false;
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
	
	public Boolean updateUser(int user_id, String username, String firstName, String lastName,
			String email, String password) {
		return false;
	}
	
	public Boolean updateTeam(int team_id, String description) {
		return false;
	}
	
	public Boolean updateProject(int project_id, String description, int catId, Date deadline) {
		return false;
	}
	
	public Boolean updateCategory(int category_id, String description) {
		return false;
	}
	
	public Boolean updateTask(int task_id, String description, Date dueDate, int priority,
			Date timeEst, int projectId, int userId) {
		return false;
	}
	
	public Boolean deleteUser(User user) {
		return false;
	}
	
	public Boolean deleteTeam(Team team) {
		return false;
	}
	
	public Boolean deleteProject(Project project) {
		return false;
	}
	
	public Boolean deleteCategory(Category category) {
		return false;
	}
	
	public Boolean deleteTask(Task task) {
		return false;
	}
	
	public Boolean removeProjectFromTeam(Project project, Team team) {
		return false;
	}
	
	public Boolean removeUserFromTeam(User user, Team team) {
		return false;
	}
	
	public Boolean removeTaskFromUser(Task task, User user) {
		return false;
	}
}
