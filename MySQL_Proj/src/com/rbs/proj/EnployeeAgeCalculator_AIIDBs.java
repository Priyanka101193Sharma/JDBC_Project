package com.rbs.proj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Date;
import java.util.Scanner;

public class EnployeeAgeCalculator_AIIDBs {

	private static final String GET_DOB_QUERY="SELECT DOB FROM EMPLOYEE WHERE ENO=?";

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:mysql:///ntspbms61db","root","Priyanka@7187");
						PreparedStatement ps=con.prepareStatement(GET_DOB_QUERY);
				){
			//READ INPUTS
			int no=0;
			if(sc!=null) {
				System.out.println("Enter employee no:");
				no=sc.nextInt();
			}
			if(ps!=null) {
				//set query param value
				ps.setInt(1,no);
				
				//execute the Query
				try(ResultSet rs=ps.executeQuery()){
					//process the ResultSet obj
					if(rs.next()) {
						//dob from DB table
						java.sql.Date sqodb=rs.getDate(1);
						//sys date
						java.util.Date sysDate=new java.util.Date();
						//dob in millis
						long dobMs=sqodb.getTime();
						//sysDate in Millis
						long sysDateMs=sysDate.getTime();
						//calculate the age
						float age=(sysDateMs-dobMs)/(1000.0f*60.0f*24.0f*365.25f);
						System.out.println("Employee age::"+age);
					}
					else {
						System.out.println("Employee not found");
					}
				}//try2
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
