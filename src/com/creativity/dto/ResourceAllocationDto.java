package com.creativity.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.creativity.www.MysqlJdbcConn;

public class ResourceAllocationDto {
	public void ResourceAllocationAssignValues(int project_id,int projectDeadline,int totalResources,int resourceBasedOnSkill,String skill) throws SQLException{
//		String if_next_to_insert="yes";
		//	Connection conn=MysqlJdbcConn.getConnection();
		//	ResultSet rs=null;
			Connection connection = null;
			Statement statement = null; 
			connection = MysqlJdbcConn.getConnection();
			String query = " insert into ResourceAllocation (projectId,projectDeadline,totalResources,resourceBasedOnSkill,skill)"
				        + " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt(1, project_id);
			preparedStmt.setInt(2,projectDeadline);
			preparedStmt.setInt   (3,totalResources);
			preparedStmt.setInt(4,resourceBasedOnSkill);
			preparedStmt.setString(5, skill);
			
			// execute the preparedstatement
			preparedStmt.execute();
			//statement =  connection.createStatement();
			try{
			int affectedRows = statement.executeUpdate(query);
			}
			catch(Exception E){
				System.out.println(E.getStackTrace());
			}
	}
}
