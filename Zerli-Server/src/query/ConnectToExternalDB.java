package query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectToExternalDB {
	private static Connection connExternalDB;
	private static Connection conn;

	public static void connectionToDB(Connection connection) {
		conn = connection;
	}

	public static void connectionToExternalDB(Connection connection) {
		connExternalDB = connection;
	}

	public static ResultSet getDataFromExternalDB() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM externaldb.users";
		try {
			pstmt = connExternalDB.prepareStatement(query);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static void insertToZerliDB() {
		ResultSet rs = getDataFromExternalDB();
		try {
			while (rs.next()) {
				String query = "INSERT INTO zerli.user VALUES( '" + rs.getString(1) + "' , '" + rs.getString(2) + "' , "
						+ rs.getBoolean(3) + " , '" + rs.getString(4) + "', '" + rs.getString(5) + "', '"
						+ rs.getString(6) + "', '" + rs.getString(7) + "' , '" + rs.getString(8) + "' , '"
						+ rs.getString(9) + "')";
				PreparedStatement pstmt = null;
				pstmt = conn.prepareStatement(query);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
		}

	}
}
