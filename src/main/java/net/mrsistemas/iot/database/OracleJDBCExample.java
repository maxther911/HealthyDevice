package net.mrsistemas.healthy.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleJDBCExample {
	

	public OracleJDBCExample() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		GetConnection connection = GetConnection.getInstance();
		try {
			ResultSet result = connection.getDataWithParameters( "admin");
			
			while (result.next()){
				System.out.println("USER: " + result.getString(2));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
