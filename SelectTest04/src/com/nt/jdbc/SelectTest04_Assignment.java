package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest04_Assignment {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try{
			//read inputs
			sc=new Scanner(System.in);
			int no=0;
			if(sc!=null) {
				System.out.println("Enter Dept number::");
				no=sc.nextInt();//gives 7499
			}
			//Load jdbc driver class(optional)
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
		    con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
			if(con!=null)
				st=con.createStatement();
			//prepare SQL Query
			
			String query="SELECT ENAME,JOB FROM EMP WHERE DEPTNO="+no;
			System.out.println(query);
			
			//send and execute the SQL query
			if(st!=null)
				rs=st.executeQuery(query);
			//process the ResultSet obj(1 or 0 records)
			if(rs!=null) {
				if(rs.next())
					System.out.println(rs.getString(1)+" " +rs.getString(2));
				else
					System.out.println("Record not found");
			}//if
					
		}//try
		catch(SQLException se) {//for known exceptions
			se.printStackTrace();
		}
		/*
		 * catch(ClassNotFoundException cnf) {//for known exceptions
		 * cnf.printStackTrace(); }
		 */
		catch(Exception e) { //for unknown exceptions
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
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally

	}//main

}//class

