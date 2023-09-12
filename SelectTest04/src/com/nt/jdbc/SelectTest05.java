//Write a jdbc program to get count of records in Student db table.... 
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest05 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//load jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
			//create jdbc Statement obj
			if(con!=null)
				st=con.createStatement();
			//prepare SQL Query
			//SELECT COUNT(*) FROM STUDENT
			String query="SELECT COUNT(*) FROM STUDENT";
			//send and execute the sql query
			if(st!=null)
				rs=st.executeQuery(query);
			//process the ResultSet obj
			if(rs!=null) {
				rs.next();
				int count=rs.getInt(1);
				System.out.println("Records count in Student db table::"+count);
			}//if
			
			}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally

	}//main

}//class
