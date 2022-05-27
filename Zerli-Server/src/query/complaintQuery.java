package query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import logic.Complain;
import logic.SingleOrder;

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
				+complain.getDescription()+"','" + complain.getComplainStatus()+"','" +complain.getRefund()+"');";
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

}
