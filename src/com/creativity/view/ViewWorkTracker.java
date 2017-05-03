package com.creativity.view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.creativity.www.MysqlJdbcConn;

public class ViewWorkTracker {
	@SuppressWarnings("null")
	public ArrayList<String> ViewWorkTrack(String TaskName1) throws SQLException{
		@SuppressWarnings("unused")
		Connection connection = null;
		Statement statement = null; 
		ResultSet rs=null;
		ArrayList<String> workTrackList=new ArrayList<String>();
		StringBuilder stringBuf=new StringBuilder();
		connection = MysqlJdbcConn.getConnection();
		String Task1=TaskName1;
		System.out.println(Task1);
		String query = "select * from fillDetails where TaskName='"+Task1+"'";
		try {
			statement=connection.createStatement();
			rs=statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(rs.next()){
			int id=rs.getInt("id");
			stringBuf.append(id);
			stringBuf.append("\t");
			String s1=rs.getString("TaskName");
			stringBuf.append(s1);
			stringBuf.append("\t");
			String s2=rs.getString("Completion_details");
			stringBuf.append(s2);
			stringBuf.append("\t");
			int level_inp=rs.getInt("level_input");
			stringBuf.append(level_inp);
			workTrackList.add(stringBuf.toString());
			stringBuf.delete(0, stringBuf.length());
		}
		return workTrackList;
	}
	public String YearEndRating(int empId) throws SQLException{
		Connection connection = null;
		Statement statement = null; 
		ResultSet rs=null;
		StringBuilder stringBuf=new StringBuilder();
		connection = MysqlJdbcConn.getConnection();
		//String Task1=TaskName1;
	//	System.out.println(Task1);
		String query = "select SUM(Manager_Rating),COUNT(TaskName) from AssignTask where EmpId='"+empId+"'" +"and Completion_status='C' group by EmpId";
		try {
			statement=connection.createStatement();
			rs=statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rs.next()){
			double finalRating=rs.getInt("SUM(Manager_Rating)");
			double TotalRating=rs.getInt("COUNT(TaskName)");
			double finallyRating;
			double interRating;
		//	System.out.println(finalRating);
		//	System.out.println(TotalRating);
			interRating=TotalRating*5;
		//	System.out.println("FinalRating"+finalRating);
		//	System.out.println("TotalRating"+TotalRating);
		//	System.out.println("interRating"+interRating);
			
			finallyRating=(finalRating/interRating)*5;
			//System.out.println(finallyRating);
			stringBuf.append((int)Math.floor(finallyRating));
			stringBuf.append("\t");
		//	int s1=rs.getInt(empId);
			stringBuf.append(empId);
			
		}
		return stringBuf.toString();
	}
}
