package com.creativity.dto;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.creativity.www.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class AssignTaskDto {
	public void AssignValues(int id,int EmpId,String taskName,int no_Of_Days,String completion_Status,String if_next_to_insert,int manager_Rating,String if_completion_Status,String MgrId) throws SQLException{
//		String if_next_to_insert="yes";
		//	Connection conn=MysqlJdbcConn.getConnection();
		//	ResultSet rs=null;
			Connection connection = null;
			Statement statement = null; 
			connection = MysqlJdbcConn.getConnection();
			String query="";
			if(if_completion_Status.equals("Update")){
				String stat="A";
				query ="UPDATE AssignTask SET EmpId=?,Completion_Status=?,Manager_Rating=? WHERE TaskName=?";
				PreparedStatement preparedStmt = connection.prepareStatement(query);
				preparedStmt.setInt(1, EmpId);
				preparedStmt.setString(2,completion_Status);
				preparedStmt.setInt(3,manager_Rating);
				preparedStmt.setString(4, taskName);
				// execute the preparedstatement
				preparedStmt.execute();
			}
			else{
			query = " insert into AssignTask (id,EmpId,TaskName,No_of_days,Completion_Status,Manager_Rating,MgrId,ApprovalMgrId)"
				        + " values (?,?, ?, ?, ?,?,?,?)";
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt(1, id);
			preparedStmt.setInt(2, EmpId);
			preparedStmt.setString(3,taskName);
			preparedStmt.setInt   (4,no_Of_Days);
			preparedStmt.setString(5,completion_Status);
			preparedStmt.setInt(6,0);
			preparedStmt.setString(7,MgrId);
			preparedStmt.setString(8,"NA");
			// execute the preparedstatement
			preparedStmt.execute();
			}
			
			//statement =  connection.createStatement();
			try{
			int affectedRows = statement.executeUpdate(query);
			}
			catch(Exception E){
				System.out.println(E.getStackTrace());
			}
	}
	public void UpdateAssignTaskValues(String task1,String mgrId) throws SQLException{
		Connection connection = null;
		Statement statement = null; 
		ResultSet rs=null;
		connection = MysqlJdbcConn.getConnection();
		String query="select * from filldetails where Approval_status='Approved'" +"and TaskName='"+task1+"'";
		try {
			statement=connection.createStatement();
			rs=statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	System.out.println("ApprovedUpdateAssignnnnnnnnnnnnnnn");
		if(rs.next()){
		//	System.out.println("ApprovedUpdateAssignnnnnnnnnnnnnnn");
			rs.getInt("level_input");
			query ="UPDATE assigntask SET Manager_Rating=?,Completion_Status=?,ApprovalMgrId=? WHERE TaskName=?";
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			//preparedStmt.setInt(1, EmpId);
			preparedStmt.setInt(1,rs.getInt("level_input"));
			preparedStmt.setString(2,"C");
			preparedStmt.setString(3, mgrId);
			preparedStmt.setString(4, task1);
			// execute the preparedstatement
			preparedStmt.execute();
			//query ="Update AvailableWorkforce SET ifTagged=? where empId='"+empId+"'";
		}
	}

}
