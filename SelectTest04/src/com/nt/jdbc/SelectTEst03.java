package com.nt.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTEst03 {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//read input
			sc=new Scanner(System.in);
			String initChars=null;
			if(sc!=null)
			{
				System.out.println("Enter initial chars of Employee name:");
				initChars=sc.next();
				//covert input values as required for the SQL Query
				initChars="'"+initChars+"%'";//given's%
				
			}
			//Load jdbc driver class(optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
			if(con!=null)
				st=con.createStatement();
			//prepare SQL Query
			//SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE ENAME LIKE'S%'ORDER BY ENAME
			String query="SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE ENAME LIKE "+initChars+" ORDER BY ENAME";
			System.out.println(query);
			
			//send and execute the SQL Query
			if(st!=null)
				rs=st.executeQuery(query);
			
			//process the ResultSet obj
			if(rs!=null) {
				boolean isRSEmpty=true;
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getDouble(4)+" "+rs.getInt(5));
					isRSEmpty=false;
				}//while
				if(isRSEmpty)
					System.out.println("record not found");
				else
					System.out.println("record found and Displayed");
				}//if
		}//try
		catch(SQLException se) {//for known exception
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();//for unknown exceptions
		}
		finally {//Good code
			//close jdbc objs
			try {
				if(rs!=null)
					rs.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(sc!=null)
					sc.close();
			}//try
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally

	}//main

}//class
