package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest05_TWR1 {

	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");){
			if(con!=null)
				try(Statement st=con.createStatement()){
					if(st!=null)
						try(ResultSet rs=st.executeQuery("SELECT COUNT(*) FROM EMP")){
							if(rs!=null) {
								rs.next();
								System.out.println("EMP db table records count::"+rs.getInt(1));
							}
						}//try3
				}//try2
				
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
