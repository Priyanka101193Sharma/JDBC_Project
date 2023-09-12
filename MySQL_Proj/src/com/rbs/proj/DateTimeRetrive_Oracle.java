package com.rbs.proj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class DateTimeRetrive_Oracle {
	private static final String GET_EMP_DATES_QUERY="SELECT * FROM EMPLOYEE";

	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:mysql:///ntspbms61db","root","Priyanka@7187");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(GET_EMP_DATES_QUERY);
				){
			if(rs!=null) {
				while(rs.next()) {
					int no=rs.getInt(1);
					String name=rs.getString(2);
					java.sql.Date sqdob=rs.getDate(3);
					java.sql.Timestamp sqdoj=rs.getTimestamp(4);
					
					//convert java.sql.Date class obj requeird String pattern date value..
					SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yy");
					String sdob=sdf.format(sqdob);
					System.out.println(no+" "+name+" "+sdob+" "+sqdoj);
				}//while
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		

	}//main

}//class
