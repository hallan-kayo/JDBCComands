package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Main {

	public static void main(String [] args) {
		
		Connection conn = null;
		Statement statement = null;
		
		try {
			
			conn = DB.getConnection();
			conn.setAutoCommit(false); //não confirma a transação até que tudo seja executado.
			statement = conn.createStatement();
			
			int rows = statement.executeUpdate("UPDATE seller SET BaseSalary = 2000 WHERE DepartmentId = 1");
			
//			//forçando uma exceção
//			int x = 1;
//			if( x < 2 ) {
//				throw new SQLException("fake error");
//			}
			
			int rows2 = statement.executeUpdate("UPDATE seller SET BaseSalary = 4000 WHERE DepartmentId = 2");
			
			conn.commit();//confirmando transação
			
			System.out.println("rows 1 " + rows);
			System.out.println("rows 2 " + rows2);
			
		}
		catch(SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transaction rolled back! Caused by: " +e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by" + e1.getMessage());
			}
		}
		finally {
			DB.closeStatement(statement);
			DB.closeConnection();
		}
	}
}
