package query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import logic.Account;
import logic.SingleDelivery;
import logic.SingleUser;
import logic.User;

/**
 * @author Evgeny
 * this class contains 'adding new user' related queries based on the DB.
 *
 */
public class AddNewUserQuery {
	
	/**
	 * @param username - username of the user.
	 * Checking if the username exists in the DB.
	 * @return true string if user exists, false string if doesn't exist.
	 */
	public static String checkIfUserExisting(String username) {
		String query = ("SELECT * FROM zerli.users WHERE Username = '" + username + "';");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String user = rs.getString("Username");
				if(user.equals(username))
					return "true";
			}
		} catch (SQLException e) {
			return "false";
		}
		return "false";
	}
	
	/**
	 * @param user - logic User, details of the user.
	 * Adds a new user to the DB.
	 */
	public static void InsertNewUser(User user)
	{
		String query= "INSERT INTO zerli.users VALUES('"+user.getUsername()+"','"+user.getPassword()+"'," + user.isLoggedIn()+",'" 
				+user.getID()+"','" + user.getFirstName()+"','" +user.getLastName()+"','" + user.getRole() + "','" + user.getPhoneNumber() +
				"','" + user.getEmail() + "');";
		System.out.println(query);
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			return;
		}	
	}
	/**
	 * @param account - logic account, details of account
	 * Adds a new account to the DB.
	 */
	public static void InsertNewAccount(Account account)
	{
		String query= "INSERT INTO zerli.account_details VALUES('"+account.getUser_ID()+"','"+account.getCreditCardNumber()+"','" +account.getExpiryDate()+"','" 
				+account.getCVV()+"'," + account.getTotalRefund()+",'" +account.getStatus()+"');";
		System.out.println(query);
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			return;
		}
	}
	/**
	 * Return array list from the DB of all users with the role of customer
	 * @return array list of logic Single User of those whose role matches customer
	 */
	public static ArrayList<SingleUser> GetUserDetials()
	{
		ArrayList<SingleUser> list= new  ArrayList<>();
		 String query = ("SELECT * FROM zerli.users WHERE Role = 'customer';");
		 try {
				PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					String status=null;
					String userID = rs.getString("id");
					String firstName = rs.getString("FirstName");
					String lastName = rs.getString("LastName");
					String query2 = ("SELECT Status FROM zerli.account_details WHERE User_ID = '"+ userID + "';");
					PreparedStatement st2 = ConnectToDB.conn.prepareStatement(query2);
					ResultSet rs2 = st2.executeQuery();
					while (rs2.next()) {
					status = rs2.getString("Status");
					}
					list.add(new SingleUser(userID,firstName,lastName,status));	
					}
			} catch (SQLException e) {
				return null;
			}
			return list;
	}
	/**
	 * Returns all data from the DB with matching User ID number
	 * @param UserId
	 * @return array list of logic Single User with matching given ID number
	 */
	public static ArrayList<SingleUser> GetUserByUserId(String UserId)
	{
		 ArrayList<SingleUser> list= new  ArrayList<>();
		 String query = ("SELECT * FROM zerli.users WHERE id = '" + UserId + "';");
		 try {
				PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					String status=null;
					String userID = rs.getString("id");
					String firstName = rs.getString("FirstName");
					String lastName = rs.getString("LastName");
					String query2 = ("SELECT Status FROM zerli.account_details WHERE User_ID = '"+ UserId + "';");
					PreparedStatement st2 = ConnectToDB.conn.prepareStatement(query2);
					ResultSet rs2 = st2.executeQuery();
					while (rs2.next()) {
					status = rs2.getString("Status");
					}
					list.add(new SingleUser(userID,firstName,lastName,status));	
					}
			} catch (SQLException e) {
				return null;
			}
			return list;
	}
	
	
	

}
