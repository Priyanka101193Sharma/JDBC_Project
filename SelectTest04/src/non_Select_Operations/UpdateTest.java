package non_Select_Operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","tiger");
				Statement st=con.createStatement()){
					String location=null;
					String name=null;
					int id=0;
					
					
					if(sc!=null)
					{
					    System.out.println("Enter student ID::");
					    id=sc.nextInt();
					    System.out.println("Enter new Address for the Student::");
					    location=sc.next();
					    System.out.println("Enter Student Name::");
					    name = sc.next();
					}
					//convert the input value as required for the SQL Query
					location="'"+location+"'";//gives 'mumbai'
					name="'"+name+"'";
					
					//prepare the SqlQuery
					//UPDATE STUDENT SET SADD='MUMBAI',AVG=56.78 WHERE SNO=132
					String query="UPDATE STUDENT SET location="+location+",SNAME="+name+" WHERE ID="+id;
					System.out.println(query);
					
					//Send and execute the SQL Query
					int count=0;
					if(st!=null)
						count=st.executeUpdate(query);
					//process the result
					if(count==0)
						System.out.println("Recorf not found for updation");
					else
						System.out.println(count+"no. of record is found and updated");
				}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
				

	}//main

}//class
