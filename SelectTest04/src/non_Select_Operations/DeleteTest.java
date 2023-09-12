package non_Select_Operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","tiger");
				Statement st=con.createStatement()){
					String location=null;
					
					
					if(sc!=null)
					{
					    
					    System.out.println("Enter new Address for the Student::");
					    location=sc.next();
					    
					}
					//convert the input value as required for the SQL Query
					location="'"+location+"'";//gives 'mumbai'
					
					
					//prepare the SqlQuery
					//DELETE FROM STUDENT WHERE LOCATION='DELHI'
					String query="DELETE FROM STUDENT WHERE LOCATION="+location;
					System.out.println(query);
					
					//Send and execute the SQL Query
					int count=0;
					if(st!=null)
						count=st.executeUpdate(query);
					//process the result
					if(count==0)
						System.out.println("Record not found for delete");
					else
						System.out.println(count+"no. of record is found and deleted");
				}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
				

	}//main

}//class
