package query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ocsf.server.ConnectionToClient;

public class Query {

	public static boolean Login(String loginInfo, ConnectionToClient client) {

		String[] login = loginInfo.split("@"); // username@password
		String query = "SELECT * FROM zerli.users;";
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String username = rs.getString("Username");
				String password = rs.getString("Password");
				boolean Loggedin = rs.getBoolean("LoggedIn");
				int ID = (int) client.getId();
				if (username.equals(login[0]) && password.equals(login[1]) && !Loggedin) {
					query = ("UPDATE users SET LoggedIn=true , id=" + ID + " WHERE Username=username;");
					st.executeUpdate(query);
					return true;
				}
			}
			return false;

		} catch (SQLException e) {
			return false;
		}
	}

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

	public static boolean Disconnect(ConnectionToClient client) {
		String query = ("SELECT * FROM zerli.users;");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int ID = rs.getInt("id");
				if (ID == client.getId()) {
					String query1 = ("UPDATE users SET LoggedIn=false WHERE id=" + ID + ";");
					st.executeUpdate(query1);
					return true;
				}
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
				int Price = rs.getInt("price");
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
			while (rs.next()) {
				int OrderID = rs.getInt("orderID");
				int itemID = rs.getInt("itemID");
				int amount = rs.getInt("amount");
				if (orderNumber == OrderID) {
					res.append(OrderID);
					res.append("#");
					res.append(itemID);
					res.append("#");
					res.append(amount);
					res.append("#");
				}
			}
			res.append("@");
			return res.toString();
		} catch (SQLException e) {
			return "Error";

		}
	}
}