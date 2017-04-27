package com.creativity.dto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.creativity.www.MysqlJdbcConn;

public class BridgeWorkforceResAlloc {
	public ArrayList<String> BridgeResource(int workExp,String skill)  throws SQLException{
		
		@SuppressWarnings("unused")
		Connection connection = null;
		Statement statement = null; 
		ResultSet rs=null;
		ArrayList<String> BridgeResourceList=new ArrayList<String>();
		StringBuilder stringBuf=new StringBuilder();
		connection = MysqlJdbcConn.getConnection();
		String skill1=skill;
		int workExp1=workExp;
	//	System.out.println(Task1);
		String query = "select * from AvailableWorkforce where skillSet='"+skill1+"'" + " and workExp>='"+workExp1+"'";
		try {
			statement=connection.createStatement();
			rs=statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(rs.next()){
			int id=rs.getInt("empId");
			stringBuf.append(id);
			stringBuf.append("\t");
			String s1=rs.getString("empName");
			stringBuf.append(s1);
			stringBuf.append("\t");
			String s2=rs.getString("skillSet");
			stringBuf.append(s2);
			stringBuf.append("\t");
			int level_inp=rs.getInt("workExp");
			stringBuf.append(level_inp);
			BridgeResourceList.add(stringBuf.toString());
			stringBuf.delete(0, stringBuf.length());
		}
		return BridgeResourceList;
	}
	
}
