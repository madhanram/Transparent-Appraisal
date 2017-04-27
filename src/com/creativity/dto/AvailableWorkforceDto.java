package com.creativity.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.creativity.www.MysqlJdbcConn;

public class AvailableWorkforceDto {
	public void WorkforceAssignValues(int employee_id,String employeeName,String skillSet,int workExp,String ifTagged,String ifManager) throws SQLException{
//		String if_next_to_insert="yes";
		//	Connection conn=MysqlJdbcConn.getConnection();
		//	ResultSet rs=null;
			Connection connection = null;
			Statement statement = null; 
			connection = MysqlJdbcConn.getConnection();
			String query = " insert into AvailableWorkforce (empId,empName,workExp,skillSet,ifTagged,ifManager)"
				        + " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt(1, employee_id);
			preparedStmt.setString(2,employeeName);
			preparedStmt.setInt   (3,workExp);
			preparedStmt.setString(4,skillSet);
			preparedStmt.setString(5, ifTagged);
			preparedStmt.setString(6, ifManager);
			
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
