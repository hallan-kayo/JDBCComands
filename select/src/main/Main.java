package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection conn = null;
		Statement statement = null;
		ResultSet result = null;
		
		try {
			conn = DB.getConnection();
			statement = conn.createStatement();
			result = statement.executeQuery("SELECT * FROM department");
			
			while(result.next()) {
				System.out.println(result.getInt("Id") + " - " + result.getString("Name"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(statement);
			DB.closeResultSet(result);
		}
	}

}
