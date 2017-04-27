package com.creativity.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import com.creativity.www.MysqlJdbcConn;

public class WorkTrackerDto {
	public void WorkTrackerAssign(int id,String taskName,String Completion_details,int level_input,String if_next_to_insert,String if_Completion_status) throws SQLException {
		// TODO Auto-generated method stub
//		String if_next_to_insert="yes";
		Connection connection = null;
		Statement statement = null; 
		String query ="";
		connection = MysqlJdbcConn.getConnection();
		if(if_Completion_status.equals("Update")){
			String stat="C";
			query ="UPDATE fillDetails SET Completion_details=?,level_input=? WHERE TaskName=?";
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1,Completion_details);
			preparedStmt.setInt(2,level_input);
			preparedStmt.setString(3,taskName);
			// execute the preparedstatement
			preparedStmt.execute();
		}
		else{
		query = " insert into fillDetails (id,TaskName,Completion_details,level_input,Approval_status)"
			        + " values (?, ?, ?, ?,?)";
		PreparedStatement preparedStmt = connection.prepareStatement(query);
		preparedStmt.setInt(1, id);
		preparedStmt.setString(2,taskName);
		preparedStmt.setString (3,Completion_details);
		preparedStmt.setInt(4,level_input);
		preparedStmt.setString(5, "NA");
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
	public void WorkTrackerAssign(int id,String taskName,String approval_Status) throws SQLException{
		Connection connection = null;
		Statement statement = null; 
		connection = MysqlJdbcConn.getConnection();
		String query ="UPDATE fillDetails SET Approval_status=? WHERE TaskName=?";
		PreparedStatement preparedStmt = connection.prepareStatement(query);
		preparedStmt.setString(1,approval_Status);
		preparedStmt.setString(2,taskName);
		// execute the preparedstatement
		preparedStmt.execute();
		try{
		int affectedRows = statement.executeUpdate(query);
		}
		catch(Exception E){
			System.out.println(E.getStackTrace());
		}
		
	}
	public void EmpUpdateWorkTrackerAssign(int id,String taskName,int levelInput) throws SQLException{
		Connection connection = null;
		Statement statement = null; 
		connection = MysqlJdbcConn.getConnection();
		String query ="UPDATE fillDetails SET level_input=? WHERE TaskName=?";
		PreparedStatement preparedStmt = connection.prepareStatement(query);
		preparedStmt.setInt(1,levelInput);
		preparedStmt.setString(2,taskName);
		// execute the preparedstatement
		preparedStmt.execute();
		try{
		int affectedRows = statement.executeUpdate(query);
		}
		catch(Exception E){
			System.out.println(E.getStackTrace());
		}
		
	}
}
