package query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import logic.Account;
import logic.SingleOrder;

/**
 * @author Evgeny this class contains account details related queries based on
 *         the DB.
 *
 */
public class AccountDetailsQuery {

	/**
	 * @param userID - id number of the user. This method returns an array list of
	 *               account details for all users.
	 * @return ArrayList of account.
	 */
	public static ArrayList<Account> GetAccountDetails(String userID) {
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
				double refund = rs.getDouble("TotalRefund");
				String status = rs.getString("Status");
				int ordersAmount = rs.getInt("OrdersAmount");
				if (userID.equals(user_ID)) {
					details.add(
							new Account(user_ID, creditCardNumber, creditCardDate, CVV, refund, status, ordersAmount));
					break;
				}
			}
			if (details.isEmpty())
				details = null;
		} catch (SQLException e) {
			return null;
		}
		return details;
	}

	/**
	 * Updates given account's status to the given status.
	 * @param accountDetails - account status and account user id
	 */
	public static void UpdateStatusByManager(String accountDetails) {
		String[] AccountDetails = accountDetails.split("@");
		String Status = AccountDetails[0];
		String UserId = AccountDetails[1];
		String query = ("UPDATE zerli.account_details SET Status='" + Status + "' WHERE User_ID='" + UserId + "';");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

	}

	public static void UpdateOrdersAmount(Account account) {
		int ordersAmount = account.getOrdersAmount() + 1;
		String query = ("UPDATE account_details SET OrdersAmount=" + ordersAmount + " WHERE User_ID = '"
				+ account.getUser_ID() + "';");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			return;

		}

	}
	/**
	 * Updates given account's role to the given role by the manager.
	 * @param accountDetails - account role and account user id
	 */
	public static void UpdateRoleByManager(String accountDetails) {
		String[] AccountDetails=accountDetails.split("@");
		String Role = AccountDetails[0];
		String UserId = AccountDetails[1];
		String query = ("UPDATE zerli.users SET Role='" + Role +"' WHERE id='"+ UserId + "';");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
	}
}
