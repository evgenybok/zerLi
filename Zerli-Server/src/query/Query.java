package query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import logic.Order;
import ocsf.server.ConnectionToClient;

public class Query {


	public static void DisconnectAll() {
		String query = ("SELECT * FROM zerli.users;");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int ID = rs.getInt("id");
				String query1 = ("UPDATE users SET LoggedIn=false WHERE id=" + ID + ";");
				st.executeUpdate(query1);
			}
		} catch (SQLException e) {
		}
	}

	public static boolean Disconnect(String loginInfo,ConnectionToClient client) {
		String[] login = loginInfo.split("@"); // username@password
		String query = "SELECT * FROM zerli.users;";
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String username = rs.getString("Username");
				String password = rs.getString("Password");
				boolean Loggedin = rs.getBoolean("LoggedIn");
				if (username.equals(login[0]) && password.equals(login[1]) && Loggedin) {
					String ID = rs.getString("id");
					query = ("UPDATE users SET LoggedIn=false WHERE id=" + ID + ";");
					st.executeUpdate(query);
					return true;
				}
			}
			return false;

		} catch (SQLException e) {
			return false;
		}

	}

	public static String getItemName(String data) {
		String query = ("SELECT * FROM zerli.item;");
		HashMap<String, ArrayList<String>> map = new HashMap<>();
		String[] arr;
		arr=data.split("#",2);
		StringBuilder res = new StringBuilder();
		res.append("Order Number:" + arr[0] + "#");
		data=arr[1];
		while (!data.equals("@")) {
			arr = data.split("#", 3);
			if (!map.containsKey(arr[0]))
				map.put(String.valueOf(arr[0]), new ArrayList<String>());
			map.get(String.valueOf(arr[0])).add(" Amount: " + arr[1]);
			data = arr[2];
		}

		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int OrderID = rs.getInt("ID");
				String imgSrc = rs.getString("imgSrc");
				String Name = rs.getString("Name");
				double price = rs.getDouble("Price");
				String Color = rs.getString("Color");
				String Type = rs.getString("Type");
				if (map.containsKey(String.valueOf(OrderID))) {
					map.get(String.valueOf(OrderID)).add("Name: " + Name);
					map.get(String.valueOf(OrderID)).add("Price: " + String.valueOf(price));
					map.get(String.valueOf(OrderID)).add("Color: " + Color);
					map.get(String.valueOf(OrderID)).add("Type: " + Type);
				}
			}
			for (String key : map.keySet()) {
				res.append(map.get(key) + "#");
			}
			res.append("@");
			return res.toString();
		} catch (SQLException e) {
			return "Error";
		}
	}
}