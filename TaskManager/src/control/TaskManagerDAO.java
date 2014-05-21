package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import model.UserDetail;

public class TaskManagerDAO {
	private String userid;      //We will need to determine how we want to
	private String password;    //get the userid, password and url into the DAO.
	private String url;
	private Connection con;
	
	public TaskManagerDAO() {
		getConnection();
	}
	
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
	
	public void close() {
		try {
			con.close();
		}
		catch(SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
		}
	}
	
	public Boolean authenticateUser(String username, String password) {
		Boolean auth = false;
		UserDetail ud = null;
		
		return auth;
	}
	
	
	
}
