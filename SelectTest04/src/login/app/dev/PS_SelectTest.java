package login.app.dev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PS_SelectTest {
	private static final String GET_ALL_EMPS="SELECT EMPNO,ENAME,JOB,SAL FROM EMP";

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
						PreparedStatement ps=con.prepareStatement(GET_ALL_EMPS)
						){
			//send and execute the SQL query
			try(ResultSet rs=ps.executeQuery()){
				//process the ResultSet obj(1 or 0 records)
				while(rs.next())
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getDouble(4));
			}
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		

	}

}
