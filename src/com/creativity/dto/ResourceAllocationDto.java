package com.creativity.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.creativity.www.MysqlJdbcConn;

public class ResourceAllocationDto {
	public void ResourceAllocationAssignValues(String project_id,int projectDeadline,int resourceBasedOnSkill,String skill) throws SQLException{
//		String if_next_to_insert="yes";
		//	Connection conn=MysqlJdbcConn.getConnection();
		//	ResultSet rs=null;
			Connection connection = null;
			Statement statement = null; 
			connection = MysqlJdbcConn.getConnection();
			String query = " insert into ResourceAllocation (projectId,projectDeadline,resourceBasedOnSkill,skill)"
				        + " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, project_id);
			preparedStmt.setInt(2,projectDeadline);
			preparedStmt.setInt(3,resourceBasedOnSkill);
			preparedStmt.setString(4, skill);
			
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
	public void ResourceAllocationUpdateAssignValues(String projectId,int resourceBasedOnSkill,String skill) throws SQLException{
//		String if_next_to_insert="yes";
		//	Connection conn=MysqlJdbcConn.getConnection();
		//	ResultSet rs=null;
			Connection connection = null;
			Statement statement = null; 
			connection = MysqlJdbcConn.getConnection();
			String query = " Update ResourceAllocation SET resourceBasedOnSkill=? where skill=? and projectId=?";
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt(1, resourceBasedOnSkill);
			preparedStmt.setString(2,skill);
			preparedStmt.setString(3,projectId);
			
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
	public String UpdateResource(String TaskName1,int empId) throws SQLException{
		@SuppressWarnings("unused")
		Connection connection = null;
		Statement statement = null; 
		ResultSet rs=null;
		String temp="";
		int getFinalresourceBasedOnSkill=0;
		ArrayList<String> resourceUpdate=new ArrayList<String>();
		StringBuilder stringBuf=new StringBuilder();
		connection = MysqlJdbcConn.getConnection();
		String Task1=TaskName1;
		System.out.println(Task1);
		String query2 = "select * from AvailableWorkforce where empId='"+empId+"'"+"and ifTagged='N'";
		try {
			statement=connection.createStatement();
			rs=statement.executeQuery(query2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rs.next()){
			String skillSet=rs.getString("skillSet");
			stringBuf.append(skillSet);
			stringBuf.append(" ");
			temp=skillSet;
		}
		else{
			stringBuf.append("NoRecord");
			stringBuf.append(" ");
		}
		String query = " Update AvailableWorkforce SET ifTagged=? where empId='"+empId+"'";
		PreparedStatement preparedStmt = connection.prepareStatement(query);
		preparedStmt.setString(1, "Y");
		// execute the preparedstatement
		preparedStmt.execute();
		//statement =  connection.createStatement();
		try{
		int affectedRows = statement.executeUpdate(query);
		}
		catch(Exception E){
			System.out.println(E.getStackTrace());
		}
		String query1 = "select * from ResourceAllocation where projectId='"+Task1+"'"+"and skill='"+temp+"'";
		try {
			statement=connection.createStatement();
			rs=statement.executeQuery(query1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rs.next()){
			int resourceBasedOnSkill=rs.getInt("resourceBasedOnSkill");
			getFinalresourceBasedOnSkill=resourceBasedOnSkill;
			stringBuf.append(getFinalresourceBasedOnSkill);
		}
		else{
			stringBuf.append("NoRecord");
		}
		return stringBuf.toString();
	}
	
}
