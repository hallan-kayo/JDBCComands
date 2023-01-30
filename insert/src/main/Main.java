package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Connection conn = null;
		PreparedStatement statement = null;
		
		try {
			conn = DB.getConnection();
//			statement = conn.prepareStatement(
//					"insert into seller "
//					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
//					+ "Values "
//					+ "(?,?,?,?,?)",
//					Statement.RETURN_GENERATED_KEYS);
//			
//			statement.setString(1, "Carl Purple");
//			statement.setString(2, "caril@gmail.com");
//			statement.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
//			statement.setDouble(4, 3000.0);
//			statement.setInt(5, 4);
			
			statement = conn.prepareStatement("insert into department (Name) values('d1'),('d2')", Statement.RETURN_GENERATED_KEYS);
			
			int rowsAffecteds = statement.executeUpdate();

			if(rowsAffecteds > 0 ) {
				ResultSet resultset = statement.getGeneratedKeys();
				while(resultset.next()) {
					int id = resultset.getInt(1);
					System.out.println("Done! Id = " + id);
				}
			}else {
				System.out.println("No Rows affecteds");
			}
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
