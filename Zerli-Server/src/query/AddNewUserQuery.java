package query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.Account;
import logic.User;

public class AddNewUserQuery {
	
	public static String checkIfUserExisting(String username) {
		String query = ("SELECT  Username FROM zerli.users WHERE Username = '" + username + "';");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String user = rs.getString("Username");
				return "true";
			}
		} catch (SQLException e) {
			return "false";
		}
		return "false";
	}
	
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
	

}
