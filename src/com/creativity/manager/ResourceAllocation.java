package com.creativity.manager;

public class ResourceAllocation {
	private int projectId;
	private int projectDeadline;
	private int totalResources;
	private int resourceBasedOnSkill;
	private String skill;
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getProjectDeadline() {
		return projectDeadline;
	}
	public void setProjectDeadline(int projectDeadline) {
		this.projectDeadline = projectDeadline;
	}
	public int getTotalResources() {
		return totalResources;
	}
	public void setTotalResources(int totalResources) {
		this.totalResources = totalResources;
	}
	public int getResourceBasedOnSkill() {
		return resourceBasedOnSkill;
	}
	public void setResourceBasedOnSkill(int resourceBasedOnSkill) {
		this.resourceBasedOnSkill = resourceBasedOnSkill;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
			this.skill = skill;
	}

}
