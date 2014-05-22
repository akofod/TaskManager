package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int team_id;
	private String description;
	private ArrayList<Project> teamProjects;
	private ArrayList<User> teamMembers;
	
	public int getId() {
		return team_id;
	}
	public void setId(int id) {
		this.team_id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<Project> getTeamProjects() {
		return teamProjects;
	}
	public void setTeamProjects(ArrayList<Project> teamProjects) {
		this.teamProjects = teamProjects;
	}
	public ArrayList<User> getTeamMembers() {
		return teamMembers;
	}
	public void setTeamMembers(ArrayList<User> teamMembers) {
		this.teamMembers = teamMembers;
	}
	
}
