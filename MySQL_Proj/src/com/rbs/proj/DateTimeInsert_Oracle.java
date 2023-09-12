package com.rbs.proj;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateTimeInsert_Oracle {

	private static final String INSERT_EMP_DATES_QUERY="INSERT INTO EMPLOYEE VALUES(?,?,?,?,?)";

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:mysql:///ntspbms61db","root","Priyanka@7187");
				PreparedStatement ps=con.prepareStatement(INSERT_EMP_DATES_QUERY);
				){
			//READ INPUTS
			int no=0;
			String name=null,dob=null,tob=null,doj=null;
			if(sc!=null) {
				System.out.println("Enter employee number::");
				no=sc.nextInt();
				System.out.println("Enter employee name::");
				name=sc.next();
				System.out.println("Enter employee DOB(yyyy-mm-dd)::");
				dob=sc.next();
				System.out.println("Enter employee TOB(hh:mm:ss)");
				tob=sc.next();
				System.out.println("Enter employee DOJ (yyyy-MM-dd hh:mm:ss)::");
				sc.nextLine();
				doj=sc.nextLine();
			}
			//convert String date value to java.sq.Date class obj
			//Convert string values to java.util.Date class obj
			java.text.SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
			java.util.Date udob=sdf.parse(dob);
			//convert java.util.Date obj to java.sql.Time class obj
			long ms=udob.getTime();
			java.sql.Date sqdob=new java.sql.Date(ms);
			//convert String time value to java.sql.Time class obj
			java.sql.Time sqtob=java.sql.Time.valueOf(tob);
			//convert String date time value to java.sql.Timestamp class obj
			java.sql.Timestamp sqdoj=java.sql.Timestamp.valueOf(doj);
			//set values to query param and execute the query
			if(ps!=null) {
				ps.setInt(1, no);
				ps.setString(2, name);
				ps.setDate(3,sqdob);
				ps.setTime(4, sqtob);
				ps.setTimestamp(5, sqdoj);
				
				//execute the query
				int count = ps.executeUpdate();
				//process the result
				if(count==0)
					System.out.println("Record not inserted");
				else
					System.out.println("Record inserted ");
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
