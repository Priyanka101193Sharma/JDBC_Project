package login.app.dev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LogginApp {

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
				Statement st=con.createStatement();
				){
			//read inputs
			String user=null,pass=null;
			if(sc!=null) {
				System.out.println("Enter username::");
				user=sc.nextLine();
				System.out.println("Enter password::");
				pass=sc.nextLine();
			}
			//convert the inputs as requeired for the SQL Query
			user="'"+user+"'";
			pass="'"+pass+"'";
			//prepare the SQL Query
			//SELECT COUNT(*) FROM USERSINFO WHERE UNAME='RAJA' AND PWD='RANI';
			String query="SELECT COUNT(*) FROM USERSINFO WHERE UNAME="+user+" AND PWD="+pass;
			System.out.println(query);
			//send and execute the sql Query
			if(st!=null) {
				try(ResultSet rs=st.executeQuery(query)){
					//process the ResultSet obj
					if(rs!=null) {
						rs.next();
						int count=rs.getInt(1);
						if(count==0)
							System.out.println("InValid Credentials");
						else
							System.out.println("Valid Credentials");
					}//if
				}//try2
			}//if
		}//try1
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main

}//class
