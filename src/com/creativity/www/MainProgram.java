package com.creativity.www;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.creativity.dto.AssignTaskDto;
import com.creativity.dto.AvailableWorkforceDto;
import com.creativity.dto.BridgeWorkforceResAlloc;
import com.creativity.dto.ResourceAllocationDto;
import com.creativity.dto.WorkTrackerDto;
import com.creativity.employee.AvailableWorkforce;
import com.creativity.manager.ResourceAllocation;
import com.creativity.view.ViewWorkTracker;

public class MainProgram {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		//This is my second commit
		
		Scanner scan=new Scanner(System.in);
		AssignTask assign_Task=new AssignTask();
		WorkTracker workTrack=new WorkTracker();
		//String if_next_to_insert="yes";
		AssignTaskDto assignValues=new AssignTaskDto();
		WorkTrackerDto workTrackDto=new WorkTrackerDto();
		ViewWorkTracker viewWorkTrack=new ViewWorkTracker();
		AvailableWorkforce availableWorkforce=new AvailableWorkforce();
		AvailableWorkforceDto availableWorkforceDto=new AvailableWorkforceDto();
		BridgeWorkforceResAlloc bridgeResource=new BridgeWorkforceResAlloc();
		ResourceAllocation resource=new ResourceAllocation();
		ResourceAllocationDto resourceAlloc=new ResourceAllocationDto();
		String if_next_to_insert="";
		String Completion_status="";
		ArrayList<String> viewWorkTrackOp=new ArrayList<String>();
		ArrayList<String> viewResource=new ArrayList<String>();
		System.out.println("Enter Who Are You");
		String who_are_you=scan.next();
		if(who_are_you.equals("manager")){
			System.out.println("Are you a Viewer or a Manager who updates WorkTracker Approval Status ");
			String view=scan.next();
			if(view.equals("no")){
				System.out.println("Give the Manager_ID");
				String mgrId=scan.next();
				System.out.println("Tell whether to Update or Insert");
				Completion_status=scan.next();
					do{
					System.out.println("Enter the ID ");
					int id=scan.nextInt();
					System.out.println("Employee ID ");
					assign_Task.setEmpId(scan.nextInt());
					System.out.println("Enter Task Name ");
					assign_Task.setTaskName(scan.next());
					System.out.println("No. of days required to complete the task ");
					assign_Task.setNo_Of_Days(scan.nextInt());
					System.out.println("Please set the completion status ");
					assign_Task.setCompletion_Status(scan.next());
					System.out.println("Give the rating for the task employee has done ");
					assign_Task.setManager_Rating(scan.nextInt());
					System.out.println("If Next to Insert ");
					if_next_to_insert=scan.next();
					try {
						assignValues.AssignValues(id,assign_Task.getEmpId(),assign_Task.getTaskName(),assign_Task.getNo_Of_Days(), assign_Task.getCompletion_Status(),if_next_to_insert,assign_Task.getManager_Rating(),Completion_status,mgrId);
						if(assign_Task.getTaskName().substring(0, 4).equals("Proj")){
						String s[]=resourceAlloc.UpdateResource(assign_Task.getTaskName(), assign_Task.getEmpId()).split("\\s+");		
						System.out.println("resourceAlloc"+s[1]+"----------------"+s[0]);
						if(s[0].equals("NoRecord") || s[1].equals("NoRecord") ){
							System.out.println("Resource  for that particular skill is already been tagged to a project");
						}
						else{
						resourceAlloc.ResourceAllocationUpdateAssignValues(assign_Task.getTaskName(), Integer.parseInt(s[1])-1,s[0]);
						}
						}
					} 
					catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						}
					}while(if_next_to_insert.equals("yes"));
				}
			else if(view.equals("approval")){
					System.out.println("Give the Manager_ID");
					String mgrId=scan.next();
					do{
						System.out.println("Enter the ID ");
						int id=scan.nextInt();
						System.out.println("Enter TaskName ");
						workTrack.setTask_Name(scan.next());
						System.out.println("Enter the Approval Status ");
						workTrack.setApproval_Status(scan.next());
						System.out.println("If Next to Insert ");
						if_next_to_insert=scan.next();
						System.out.println(workTrack.getTask_Name());
						System.out.println(workTrack.getApproval_Status());
						try {
							workTrackDto.WorkTrackerAssign(id, workTrack.getTask_Name(), workTrack.getApproval_Status());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(workTrack.getApproval_Status().equals("Approved")){
							assignValues.UpdateAssignTaskValues(workTrack.getTask_Name(),mgrId);
							}
					}while(if_next_to_insert.equals("yes"));
			}
			else{
				System.out.println("Enter Task Name ");
				workTrack.setTask_Name(scan.next());
				try {
					viewWorkTrackOp=viewWorkTrack.ViewWorkTrack(workTrack.getTask_Name());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(String row:viewWorkTrackOp){
					System.out.println(row);
					System.out.println("----------------------------------------------");
				}
			}
		}
		else if(who_are_you.equals("non-manager")){ 
			Completion_status=scan.next();
			if(Completion_status.equals("Insert")){
			do{
				int id=scan.nextInt();
				System.out.println("Enter TaskName ");
				workTrack.setTask_Name(scan.next());
				System.out.println("Enter Completion Details ");
				workTrack.setCompletion_details(scan.next());
				System.out.println("Enter Level Input ");
				workTrack.setLevel_input(scan.nextInt());		
				System.out.println("if_next_to_insert");
				if_next_to_insert=scan.next();
				try {
					workTrackDto.WorkTrackerAssign(id, workTrack.getTask_Name(),workTrack.getCompletion_details(), workTrack.getLevel_input(),if_next_to_insert,Completion_status);
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}while(if_next_to_insert.equals("yes"));
			}
			else{
				int id=scan.nextInt();
				System.out.println("Enter TaskName ");
				workTrack.setTask_Name(scan.next());
				System.out.println("Enter Completion Details ");
				workTrack.setCompletion_details(scan.next());
				System.out.println("Enter Level Input ");
				workTrack.setLevel_input(scan.nextInt());	
				workTrackDto.EmpUpdateWorkTrackerAssign(id, workTrack.getTask_Name(),workTrack.getCompletion_details(),workTrack.getLevel_input());
				//workTrackDto.EmpUpdateWorkTrackerAssign(id, taskName, levelInput);
			}
		}
		else if(who_are_you.equals("new")){ 
			do{
			System.out.println("Enter Employee ID ");
			availableWorkforce.setEmployee_id(scan.nextInt());
			System.out.println("Enter Employee Name ");
			availableWorkforce.setEmployeeName(scan.next());
			System.out.println("Are you a Manager ");
			availableWorkforce.setIfManager(scan.next());
			System.out.println("If tagged to project");
			availableWorkforce.setIfTagged(scan.next());
			System.out.println("Enter skill");
			availableWorkforce.setSkillSet(scan.next());
			System.out.println("Enter Work Experience");
			availableWorkforce.setWorkExp(scan.nextInt());
			if_next_to_insert=scan.next();
			try {
				availableWorkforceDto.WorkforceAssignValues(availableWorkforce.getEmployee_id(), availableWorkforce.getEmployeeName(), availableWorkforce.getSkillSet(), availableWorkforce.getWorkExp(), availableWorkforce.getIfTagged(), availableWorkforce.getIfManager());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}while(if_next_to_insert.equals("yes"));
		}
		else if(who_are_you.equals("ProjectResourceAllocator")){
			System.out.println("Enter Project ID");
			resource.setProjectId(scan.next());
			System.out.println("Enter project Deadline");
			resource.setProjectDeadline(scan.nextInt());
			System.out.println("Enter resourceBasedOnSkill");
			resource.setResourceBasedOnSkill(scan.nextInt());
			System.out.println("Enter Skill set");
			resource.setSkill(scan.next());
			resourceAlloc.ResourceAllocationAssignValues(resource.getProjectId(), resource.getProjectDeadline(), resource.getResourceBasedOnSkill(), resource.getSkill());
		}
		else if(who_are_you.equals("ViewResource")){ 
				try {
					System.out.println("Enter Work Experience");
					int workExperience=scan.nextInt();
					System.out.println("Enter skill");
					String skill=scan.next();
					viewResource=bridgeResource.BridgeResource(workExperience, skill);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for(String row:viewResource){
					System.out.println(row);
					System.out.println("----------------------------------------------");
				}
		}
		else if(who_are_you.equals("YearEndRating")){ 
			    System.out.println("Enter Employee Id for which final rating is to be displayed");
			    int empId=scan.nextInt();
			    String finalRating=viewWorkTrack.YearEndRating(empId);
			    String ArrayRating[]=finalRating.split("\t");
			    System.out.println("Employee Id:"+ArrayRating[1]+"\t"+"Rating:"+ArrayRating[0]);
		}
		else{
			System.out.println("Invalid user");
		}
		//My third commit
	}

}
