package com.rbs.proj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertTest_SurrogateKey {
	private static final String INSERT_EMP_QUERY="INSERT INTO EMP01(EMPNO,EMPNAME,DESG,SALARY) VALUES (EID_SEQ.NEXTVAL,?,?,?)";

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
				PreparedStatement ps=con.prepareStatement(INSERT_EMP_QUERY);
				){
			//read inputs from enduser
			String name=null,desg=null;
			float salary=0.0f;
			if(sc!=null) {
				System.out.println("Enter employee name::");
				name=sc.next();
				System.out.println("Enter employee desg::");
				desg=sc.next();
				System.out.println("Enter employee salary::");
				salary=sc.nextFloat();
			}
			if(ps!=null) {
				//set values to query params
				ps.setString(1, name);
				ps.setString(2, desg);
				ps.setFloat(3, salary);
				//execute the SQL Query
				int result=ps.executeUpdate();
				//process the record result
				if(result==0)
					System.out.println("Recored not inserted");
				else
					System.out.println("Result inserted");
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
