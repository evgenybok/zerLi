package query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Evgeny
 * this class contains 'Store' related queries based on the DB.
 *
 */
public class StoresQuery {

	/**
	 * @param area- name of the area.
	 * @return List of stores inside given area.
	 */
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

		} catch (SQLException e) {
			return null;
		}
		return stores;
	}
	
	/**
	 * @param Name- name of store.
	 * @return store id number of given store name.
	 */
	public static String GetStoreId(String Name)
	{
		String query = "SELECT IDstore FROM zerli.stores WHERE storeName='" + Name + "'";
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String storeName1 = rs.getString("IDstore");
				return storeName1;
			}
			
		} catch (SQLException e) {
			return null;
		}
		return null;
	}


	/**
	 * @param ID- id number of manager.
	 * @return id number of store which is managed by the manager with given id from the DB.
	 */
	public static String GetStoreIdByWorkerID(String ID)
	{
		String query = "SELECT IDstore FROM zerli.stores WHERE IDmanager='" + ID + "'";
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String id = rs.getString("IDstore");
				return id;
			}

		} catch (SQLException e) {
			return null;
		}
		return null;
	}

	/**
	 * @param ID- id number of store.
	 * @return store name with given store id from the DB.
	 */
	public static ArrayList<String> GetStoreNameByID(String ID) {
		String query = "SELECT storeName FROM zerli.stores WHERE IDstore = '" + ID + "'";
		// String query = ("SELECT * FROM zerli.stores;");
		ArrayList<String> stores = new ArrayList<String>();
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String storeName1 = rs.getString("storeName");
				stores.add(storeName1);
			}

		} catch (SQLException e) {
			return null;
		}
		return stores;
	}
	
	public static ArrayList<String> GetStoresIdinList()
	{
		String query = "SELECT IDstore FROM zerli.stores;";
		ArrayList<String> stores = new ArrayList<>();
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String storeID = rs.getString("IDstore");
				stores.add(storeID);
			}
			
		} catch (SQLException e) {
			return null;
		}
		return stores;
	}
}

