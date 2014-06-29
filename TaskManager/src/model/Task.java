package model;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
	private static final long serialVersionUID = 1L;
	private int task_id;
	private String description;
	private Date due_date;
	private String priority;
	private double time_estimate;
	private double time_completed;
	private String status;
	private int project_id;
	public int getId() {
		return task_id;
	}
	public void setId(int task_id) {
		this.task_id = task_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDueDate() {
		return due_date;
	}
	public void setDueDate(Date due_date) {
		this.due_date = due_date;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public double getTimeEstimate() {
		return time_estimate;
	}
	public void setTimeEstimate(double time_estimate) {
		this.time_estimate = time_estimate;
	}
	public double getTimeCompleted() {
		return time_completed;
	}
	public void setTimeCompcompleted(double time_completed) {
		this.time_completed = time_completed;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getProjectId() {
		return project_id;
	}
	public void setProjectId(int project_id) {
		this.project_id = project_id;
	}
	public int getTask_id() {
		return task_id;
	}
	public Date getDue_date() {
		return due_date;
	}
	public double getTime_estimate() {
		return time_estimate;
	}
	public double getTime_completed() {
		return time_completed;
	}
	public int getProject_id() {
		return project_id;
	}
	
	
}
