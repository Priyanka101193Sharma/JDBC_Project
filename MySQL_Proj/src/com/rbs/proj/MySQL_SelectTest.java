package com.rbs.proj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL_SelectTest {

	private static final String FETCH_ALL_STUDS = "SELECT SNO,SNAME,SADD,AVG FROM STUDENT";

	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/NTAJ916DB","root","Priyanka@7187");
				PreparedStatement ps=con.prepareStatement(FETCH_ALL_STUDS);
				ResultSet rs=ps.executeQuery();
				){
			if(rs!=null) {
				boolean isRsEmpty=true;
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					isRsEmpty=false;
				}
				if(isRsEmpty)
					System.out.println("No records found in db table");
				else
					System.out.println("Records are found and displayed");
			}//if
			System.out.println("con object class name::"+con.getClass());
			System.out.println("Statement obj class name::"+ps.getClass());
			System.out.println("ResultSet object class name::"+rs.getClass());
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main

}//class
