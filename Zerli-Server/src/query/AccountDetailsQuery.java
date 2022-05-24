package query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import logic.Account;
import logic.User;

public class AccountDetailsQuery {
	
	public static ArrayList<Account> GetAccountDetails(String userID) 
	{
		String query = ("SELECT * FROM zerli.account_details;");
		ArrayList<Account> details = new ArrayList<>();
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String user_ID = rs.getString("User_ID");
				String creditCardNumber = rs.getString("CreditCardNumber");
				String creditCardDate = rs.getString("ExpiryDate");
				String CVV = rs.getString("CVV");
				String refund = rs.getString("TotalRefund");
				String status = rs.getString("Status");
				if(userID.equals(user_ID))
				{
					details.add(new Account(user_ID,creditCardNumber,creditCardDate,CVV,refund,status));
				break;
				}
			}
			if(details.isEmpty())
				details=null;
		} catch (SQLException e) {
			return null;
		}
		return details;
	}
}
