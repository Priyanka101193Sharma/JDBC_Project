package com.rbs.proj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EnployeeAgeCalculator_MySQL {

	private static final String MySQL_AGE_CALCULATOR="SELECT (TO_DAYS(CURDATE())-TO_DAYS(DOB))/365.25 FROM EMPLOYEE WHERE ENO=?";

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:mysql:///ntspbms61db","root","Priyanka@7187");
						PreparedStatement ps=con.prepareStatement(MySQL_AGE_CALCULATOR);
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
						System.out.println("Employee age::"+rs.getFloat(1));
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
