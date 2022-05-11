package query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import logic.User;
import ocsf.server.ConnectionToClient;

public class Query {

	public static User Login(User user) {
		String query = "SELECT * FROM zerli.users;";
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String username = rs.getString("Username");
				String password = rs.getString("Password");
				boolean Loggedin = rs.getBoolean("LoggedIn");
				String ID = rs.getString("id");
				String role = rs.getString("Role");
				if (username.equals(user.getUsername()) && password.equals(user.getPassword()) && !Loggedin) {
					query = ("UPDATE users SET LoggedIn=true WHERE id='" + ID + "';");
					user = new User(username, password, true, ID, role);
					st.executeUpdate(query);
				}
			}
			return user;
		} catch (SQLException e) {
			return user;
		}
	}

	public static void DisconnectAll() {
		String query = ("SELECT * FROM zerli.users;");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String query1 = ("UPDATE users SET LoggedIn=false;");
				st.executeUpdate(query1);
			}
		} catch (SQLException e) {
		}
	}

	public static boolean Disconnect(User user) {
		String query = ("SELECT * FROM zerli.users;");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String query1 = ("UPDATE users SET LoggedIn=false WHERE id=" + user.getID() + ";");
				st.executeUpdate(query1);
				return true;
			}

			return false;
		} catch (SQLException e) {
			return false;
		}
	}

	public static String GetOrders() {
		String query = ("SELECT * FROM zerli.orders;");
		StringBuilder res = new StringBuilder();
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int OrderNumber = rs.getInt("orderNumber");
				double Price = rs.getDouble("price");
				String GreetingCard = rs.getString("greetingCard");
				String color = rs.getString("Color");
				String D_Order = rs.getString("dOrder");
				String Shop = rs.getString("shop");
				String Date = rs.getString("date");
				String OrderDate = rs.getString("orderDate");
				res.append(OrderNumber);
				res.append("#");
				res.append(Price);
				res.append("#");
				res.append(GreetingCard);
				res.append("#");
				res.append(color);
				res.append("#");
				res.append(D_Order);
				res.append("#");
				res.append(Shop);
				res.append("#");
				res.append(Date);
				res.append("#");
				res.append(OrderDate);
				res.append("@");
			}
			res.append("&");
			if (res != null) {
				return res.toString();
			}

		} catch (SQLException e) {

		}
		return ("ERROR");
	}

	public static boolean Update(String updatedData) {
		String[] str = updatedData.split("#", 3);
		int ordNum = Integer.parseInt(str[0]);
		String color = str[1];
		String date = str[2];
		String query = ("SELECT * FROM zerli.orders;");

		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int num = rs.getInt("orderNumber");
				if (num == ordNum) {
					String color1 = rs.getString("Color");
					String date1 = rs.getString("date");
					if (color.equals("")) {
						color = color1;
					}
					if (date.equals("")) {
						date = date1;
					}

					String query1 = ("UPDATE zerli.orders SET Color=" + "\"" + color + "\"" + " , " + "Date=" + "\""
							+ date + "\"" + " WHERE orderNumber = " + ordNum + ";");
					st.executeUpdate(query1);
					return true;
				}

			}
		} catch (SQLException e) {
			return false;
		}
		return false;
	}

	public static String getSelectedOrder(int orderNumber) {
		String query = ("SELECT * FROM zerli.iteminorder;");
		StringBuilder res = new StringBuilder();
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			res.append(orderNumber);
			res.append("#");
			while (rs.next()) {
				int OrderID = rs.getInt("orderID");
				int itemID = rs.getInt("itemID");
				int amount = rs.getInt("amount");
				if (orderNumber == OrderID) {
					res.append(itemID);
					res.append("#");
					res.append(amount);
					res.append("#");
				}
			}
			// ordernum, itemid, amount, itemid, amout
			res.append("@");
			if (res.toString().equals("@"))
				return null; // no details
			return res.toString();
		} catch (SQLException e) {
			return "Error";
		}
	}

	public static String getItemName(String data) {
		String query = ("SELECT * FROM zerli.item;");
		HashMap<String, ArrayList<String>> map = new HashMap<>();
		String[] arr;
		arr = data.split("#", 2);
		StringBuilder res = new StringBuilder();
		res.append("Order Number:" + arr[0] + "#");
		data = arr[1];
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