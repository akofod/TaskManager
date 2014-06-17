package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Project implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int project_id;
	private String description;
	private int category_id;
	private Date final_deadline;
	private ArrayList<Task> projectTasks;
	
	public int getProject_id() {
		return project_id;
	}
	public int getId() {
		return project_id;
	}
	public void setId(int project_id) {
		this.project_id = project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCategoryId() {
		return category_id;
	}
	
	public void setCategoryId(int category_id) {
		this.category_id = category_id;
	}
	
	public Date getFinalDeadline() {
		return final_deadline;
	}
	public void setFinalDeadline(Date final_deadline) {
		this.final_deadline = final_deadline;
	}
	public ArrayList<Task> getProjectTasks() {
		return projectTasks;
	}
	public void setProjectTasks(ArrayList<Task> projectTasks) {
		this.projectTasks = projectTasks;
	}
}
