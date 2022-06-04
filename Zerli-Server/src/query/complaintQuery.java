package query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import logic.Complain;
import logic.SingleComplaint;

/**
 * @author Evgeny
 * this class contains 'Complaint' related queries based on the DB.
 *
 */
public class complaintQuery {

	/**
	 * @param OrderId - id number of an order.
	 * Finds the corresponding user id with the given order id number in the DB.
	 * @return userid associated with given order id
	 */
	public static String GetUserIDbyOrderNumberQuery(String OrderId) {
		String userid=null;
		String query = ("SELECT UserID FROM zerli.orders WHERE OrderNumber = '" + OrderId + "';");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				userid = rs.getString("UserID");
				
			}
		} catch (SQLException e) {
			return null;
		}
		return userid;
	}
	
	/**
	 * @param complain - logic Complain, contains details of a complaint.
	 * Method inserts a new complaint in the DB.
	 */
	public static void InsertNewComplain(Complain complain)
	{
		String query= "INSERT INTO zerli.complaint VALUES('"+complain.getHandleUserID()+"','"+complain.getComplainUserID()+"'," +complain.getOrderID()+",'" 
				+complain.getDescription()+"','" + complain.getComplainStatus()+"'," +complain.getRefund()+");";
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			return;
		}	
	}
	/**
	 * @param orderid - id number of order.
	 * Method checks if given order has a complaint.
	 * @return String true if complaint exists, false otherwise.
	 */
	public static String CheckComplaintExist(String orderid)
	{
		String temp;
		String query = ("SELECT OrderID FROM zerli.complaint WHERE OrderId = '" + orderid + "' AND complainStatus= 'WaitForHandle';");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				temp = rs.getString("OrderId");
				return "true";
			}
		} catch (SQLException e) {
			return "false";
		}
		return "false";
		
	}
	/**
	 * @param userid_orderid - user id number and order id number in one string.
	 * Checks if there is an order number with given order number and user id.
	 * @return true string if exists, false string otherwise.
	 */
	public static String CheckIfThereExistOrderForUserId(String userid_orderid)
	{
		String str[]= userid_orderid.split("@",2);
		String query = ("SELECT OrderNumber FROM zerli.orders WHERE OrderNumber = '" + str[1] + "' AND UserID= '" + str[0] + "';");
		String temp;
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				temp = rs.getString("OrderNumber");
				return "true";
			}
		} catch (SQLException e) {
			return "false";
		}
		return "false";
	}
	/**
	 * Method gathers in the array list all of the complaints which are needed to be handled.
	 * @return ArrayList of logic single complaint
	 */
	public static ArrayList<SingleComplaint> GetComplaints()
	{
		String query = ("SELECT * FROM zerli.complaint WHERE complainStatus = 'WaitForHandle';");
		ArrayList<SingleComplaint> singlecomplaint = new ArrayList<>();
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String userid=rs.getString("complainUserID");
				String orderid=rs.getString("OrderId");
				String description=rs.getString("Description");
				singlecomplaint.add(new SingleComplaint(userid,orderid,description));
			}
		} catch (SQLException e) {

		}
		return singlecomplaint;
	}
	/**
	 * @param userid - id number of the user.
	 * Method gets all single complaints made by given userid.
	 * @return Arraylist of logic Single Complaint of complaints made by the given userid.
	 */
	public static ArrayList<SingleComplaint> GetSingleComplaintByUserId(String userid)
	{
		String query = ("SELECT * FROM zerli.complaint WHERE complainUserID = '" + userid +"';");
		System.out.println(query);
		ArrayList<SingleComplaint> singlecomplaint = new ArrayList<>();
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String Userid=rs.getString("complainUserID");
				String orderid=rs.getString("OrderId");
				String description=rs.getString("Description");
				singlecomplaint.add(new SingleComplaint(Userid,orderid,description));
			}
		} catch (SQLException e) {

		}
		return singlecomplaint;
	}
	/**
	 * @param details - refund amount, user id, order id.
	 * Method updates the total refund amount in the db based on given parameters. 
	 */
	public static void UpdateAccountTotalRefund(String details)
	{
		double total=0;
		String[] str = details.split("@",3);
		double refund = Double.parseDouble(str[0]);
		String query = ("SELECT TotalRefund FROM zerli.account_details WHERE User_ID = '" + str[1] + "';"); 
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				total=rs.getDouble("TotalRefund");
			}
		} catch (SQLException e) {

		}
		double newtotal = total + refund;
		String query2 = ("UPDATE account_details SET TotalRefund=" + newtotal +" WHERE User_ID = '" + str[1] + "';");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query2);
			st.executeUpdate();
		} catch (SQLException e) {
			return;
		}	
		String query3 = ("UPDATE complaint SET Refund=" + refund +" WHERE complainUserID = '" + str[1] + "' AND OrderId = '" + str[2]+"';");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query3);
			st.executeUpdate();
		} catch (SQLException e) {
			return;
		}
	}
	/**
	 * @param orderid - id number of the order.
	 * Updates complaint in the DB based on given order id number.
	 */
	public static void UpdateToHandled(String orderid)
	{
		String query = ("UPDATE complaint SET complainStatus = 'Handled' WHERE OrderId = '" + orderid +"';");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			return;
		}	
	}

	

}
