package query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StoresQuery {

	public static ArrayList<String> GetStoreName(String area) {
		String query = "SELECT storeName FROM zerli.stores WHERE area='" + area + "'";
		// String query = ("SELECT * FROM zerli.stores;");
		ArrayList<String> stores = new ArrayList<String>();
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String storeName1 = rs.getString("storeName");
				stores.add(storeName1);
			}
			System.out.println(stores.get(0));

		} catch (SQLException e) {
			return null;
		}
		return stores;
	}
}
