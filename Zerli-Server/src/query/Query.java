package query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import communication.MessageAnswer;
import logic.Item;
import logic.Order;
import logic.SingleOrder;
import logic.SingleWorker;
import logic.User;

/**
 * @author Evgeny this class contains 'General Queries' related queries based on
 *         the DB.
 *
 */
public class Query {

	/**
	 * @param user- logic User, contains user details. updates login status in DB of
	 *              given user when logging in. changes logged in status in DB.
	 * @return logic User of user details.
	 */
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
				String firstName = rs.getString("FirstName");
				String lasName = rs.getString("LastName");
				String role = rs.getString("Role");
				String phoneNumber = rs.getString("PhoneNumber");
				String email = rs.getString("Email");
				if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
					if (!Loggedin) {
						query = ("UPDATE users SET LoggedIn=true WHERE id='" + ID + "';");
						user = new User(username, password, true, ID, firstName, lasName, role, phoneNumber, email,
								true);
						st.executeUpdate(query);
						return user;
					} else if (Loggedin) {
						user = new User(username, password, false, ID, role, true);
						return user;
					}
				}

			}
			return user = new User(null, null, false, null, null, false);
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Disconnects all user from the DB.
	 */
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

	/**
	 * @param user- logic User, contains user details. Disconnects given single
	 *              user.
	 * @return true if update was successful, false otherwise.
	 */
	public static boolean Disconnect(User user) {
		String query = ("UPDATE users SET LoggedIn=false WHERE id='" + user.getID() + "' AND Username='"
				+ user.getUsername() + "';");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * @return ArrayList of users in DB
	 */
	public static ArrayList<User> GetUsersDB() {
		ArrayList<User> users = new ArrayList<>();
		String query = "SELECT FROM zerli.users;";
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String username = rs.getString("Username");
				String password = rs.getString("Password");
				boolean Loggedin = rs.getBoolean("LoggedIn");
				String ID = rs.getString("id");
				String Firstname = rs.getString("FirstName");
				String Lastname = rs.getString("LastName");
				String role = rs.getString("Role");
				String Phonenumber = rs.getString("PhoneNumber");
				String Email = rs.getString("Email");
				users.add(new User(username, password, Loggedin, ID, Firstname, Lastname, role, Phonenumber, Email));
			}
		} catch (SQLException e) {
		}
		return users;
	}

	/**
	 * @param data - order number, array list of item details
	 * @return item name of given order.
	 */
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
				// String imgSrc = rs.getString("imgSrc");
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

	/**
	 * @return array list of logic item of premade items
	 */
	public static ArrayList<Item> GetPremadeItems() {
		String query = "SELECT * FROM zerli.item WHERE ID LIKE '2%' ORDER BY onSale =0 AND ID;";
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int ID = rs.getInt("ID");
				String imgSrc = rs.getString("imgSrc");
				String Name = rs.getString("Name");
				Double Price = rs.getDouble("Price");
				String Color = rs.getString("color");
				String Type = rs.getString("Type");
				boolean onSale = rs.getBoolean("onSale");
				Double salePrice = rs.getDouble("salePrice");
				items.add(new Item(ID, imgSrc, Name, Price, Color, Type, onSale, salePrice));
			}
		} catch (SQLException e) {

		}
		return items;

	}

	/**
	 * @return arraylist of logic item of self assembly items.
	 */
	public static ArrayList<Item> GetSelfAssemblyItems() {
		String query = "SELECT * FROM zerli.item WHERE ID LIKE '1%' ORDER BY onSale =0 AND ID;";
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int ID = rs.getInt("ID");
				String imgSrc = rs.getString("imgSrc");
				String Name = rs.getString("Name");
				Double Price = rs.getDouble("Price");
				String Color = rs.getString("color");
				String Type = rs.getString("Type");
				boolean onSale = rs.getBoolean("onSale");
				Double salePrice = rs.getDouble("salePrice");
				items.add(new Item(ID, imgSrc, Name, Price, Color, Type, onSale, salePrice));
			}
		} catch (SQLException e) {

		}
		return items;

	}

	public static ArrayList<SingleWorker> GetWorkers() {
		String query = "SELECT * FROM zerli.users WHERE Role = 'worker' OR Role = 'Delivery' OR Role = 'customer specialist' OR Role = 'Marketing' OR Role = 'customer service';";
		ArrayList<SingleWorker> workers = new ArrayList<SingleWorker>();
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				String id=rs.getString("id");
				String firstname=rs.getString("FirstName");
				String lastname=rs.getString("LastName");
				String role=rs.getString("Role");
				workers.add(new SingleWorker(id,firstname,lastname,role));
			}
		} catch (SQLException e) {
		}
		return workers;
	}

}