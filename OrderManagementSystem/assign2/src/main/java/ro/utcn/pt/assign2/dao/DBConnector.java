package ro.utcn.pt.assign2.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {

	private static Connection connector;
	public DBConnector() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
		
		//creating a variable for the connection called "connector"
        connector = DriverManager.getConnection("jdbc:mysql://localhost:3306/pt_assign2","root","");
	}
	
	public static Connection getConnection(){///should it be static?
		return connector;
	}
}