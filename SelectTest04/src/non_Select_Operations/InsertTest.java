package non_Select_Operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest {

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","tiger");
						Statement st=con.createStatement();
				){
			String name=null,addrs=null;
			float avg=0.0f;
			int no=0;
			//read inputs fro, the enduser
			if(sc!=null) {
				System.out.println("Enter student number::");
				no=sc.nextInt();//gives 101
				System.out.println("Enter student name::");
				name=sc.next();//gives raja
				System.out.println("Enter student address::");
				addrs=sc.next();//gives hyd
				System.out.println("Enter student marks avg::");
				avg=sc.nextFloat();//gives 90.89
			}
			//convert inputs values as required for the SQL Query
			name="'"+name+"'";//gives 'raja'
			addrs="'"+addrs+"'";
			//prepare SQL Query
			//INSER INTO STUDENT VALUES(101,'RAJA','HYD',90.89
			String query="INSER INTO STUDENT VALUES("+no+","+name+","+addrs+","+avg+")";
			System.out.println(query);
			
			//send and execute sql query
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			//process the result
			if(count==0)
				System.out.println("record not inserted");
			else
				System.out.println("Record inserted");
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main

}//class
