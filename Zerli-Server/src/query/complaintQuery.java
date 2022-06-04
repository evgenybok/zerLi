package query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import logic.Complain;
import logic.SingleComplaint;

public class complaintQuery {

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
