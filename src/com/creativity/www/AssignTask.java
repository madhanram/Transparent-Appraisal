package com.creativity.www;

public class AssignTask {
private String taskName;
private int empId;
private int no_Of_Days;
private String completion_Status;
private int manager_Rating;
public int getEmpId() {
	return empId;
}
public void setEmpId(int empId) {
	this.empId = empId;
}
public int getManager_Rating() {
	return manager_Rating;
}
public void setManager_Rating(int manager_Rating) {
	this.manager_Rating = manager_Rating;
}
public String getTaskName() {
	return taskName;
}
public void setTaskName(String taskName) {
	this.taskName = taskName;
}
public int getNo_Of_Days() {
	return no_Of_Days;
}
public void setNo_Of_Days(int no_Of_Days) {
	this.no_Of_Days = no_Of_Days;
}
public String getCompletion_Status() {
	return completion_Status;
}
public void setCompletion_Status(String completion_Status) {
	this.completion_Status = completion_Status;
}

}
