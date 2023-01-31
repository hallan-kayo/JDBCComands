package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement statement = null;
		
		try {
			conn = DB.getConnection();
			statement = conn.prepareStatement(
					"DELETE FROM department "
					+ "WHERE "
					+ "Id = ?"
					);
			
			statement.setInt(1, 2);
			
			int rowsAffected = statement.executeUpdate();
			System.out.println("Done! " + rowsAffected + " rows affected.");
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}
		finally {
			DB.closeStatement(statement);
			DB.closeConnection();
		}
	}

}
