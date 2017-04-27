package com.creativity.www;
public class WorkTracker {
			//Daily Progress chart
			//Manager updates the chart with tasks for each person and the completion of tasks results in updating the progress bar
			private String task_Name;
			//	int[] level_Of_Completion={1,2,3,4,5};
			private String Completion_details;
			//Scanner scan=new Scanner(System.in);
			private int level_input;
			private String Approval_Status;
			public String getApproval_Status() {
				return Approval_Status;
			}
			public void setApproval_Status(String approval_Status) {
				Approval_Status = approval_Status;
			}
			public String getTask_Name() {
				return task_Name;
			}
			public void setTask_Name(String task_Name) {
				this.task_Name = task_Name;
			}
			public String getCompletion_details() {
				return Completion_details;
			}
			public void setCompletion_details(String completion_details) {
				Completion_details = completion_details;
			}
			public int getLevel_input() {
				return level_input;
			}
			public void setLevel_input(int level_input) {
				this.level_input = level_input;
			}
					
}

