package login.app.dev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PS_BulkInsertTest {

	private static final String INSERT_STUDENT03_QUERY = "INSERT INTO STUDENT03 VALUES(?,?,?,?)";

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
				PreparedStatement ps=con.prepareStatement(INSERT_STUDENT03_QUERY)
				){
			int count=0;
			if(sc!=null) {
				System.out.println("Enter student count::");
				count=sc.nextInt();
			}//if
			
			if(sc!=null && ps!=null) {
				for(int i=1;i<=count;i++) {
					System.out.println("Enter"+i+"Student details");
					System.out.println("enter no:");
					int no=sc.nextInt();
					System.out.println("Enter name:");
					String name=sc.next();
					System.out.println("Enter address:");
					String addrs=sc.next();
					System.out.println("Enter sal:");
					double avg=sc.nextDouble();
					//set the above inputs to pre-compiled SQL Query param
					ps.setInt(1, no);ps.setString(2, name);ps.setString(3, addrs);
					ps.setDouble(4, avg);
					
					//execute the pre-compiled sql query
					int result=ps.executeUpdate();
					//process the results
					if(result==0)
						System.out.println(i+" student record is not inserted");
					else
						System.out.println(i+" student record is inserted");
					
				}//for
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
