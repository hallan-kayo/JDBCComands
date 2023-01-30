package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement statement = null;
		
		try {
			conn = DB.getConnection();
			statement = conn.prepareStatement(
					"UPDATE seller "
					+ "SET BaseSalary = BaseSalary + ? "
					+ "WHERE "
					+ "(DepartmentId = ?)"
					);
			statement.setDouble(1, 300);
			statement.setInt(2, 2);
			
			int rowsAffected = statement.executeUpdate();
			
			System.out.println("Done! " + rowsAffected + " rows affecteds.");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(statement);
			DB.closeConnection();
		}
	}

}
