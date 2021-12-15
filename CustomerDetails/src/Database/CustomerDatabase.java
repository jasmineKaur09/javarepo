package Database ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDatabase {
	static Connection connection = null;
	static Statement statement = null;
	static ResultSet resultset = null;
	
	public static void main(String[]args) {
		String fileName = "CustomerDetails.accdb";

		try
		{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("Problem in loading the driver");
			System.out.println(ex.getMessage());
		}
		
		String dbURL = "jdbc:ucanaccess://" + fileName;
		try {
			connection = DriverManager.getConnection(dbURL);
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		searchAllRecord();
	}
	
		
	
	
	public static ResultSet searchAllRecord() {
		 try {
		resultset = statement.executeQuery("SELECT CusName From Customer"); 
		while(resultset.next()) {
			String name = resultset.getString(1);
			System.out.println("Customer name " + name );
		}
		close();

		return resultset;

		 }
		 catch(SQLException e)
			{
				System.out.println(e.getMessage());
				close();
				return resultset;
			}
	}
	
	
	
	public static void close()
	{
		if (connection != null) {
			try {
				// resultset.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}
}
