package query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import logic.SingleDelivery;
import logic.SingleSelfDelivery;


public class DeliveryQuery {
	
	public static ArrayList<SingleDelivery> getOrderForDelivey()
	{
		ArrayList<SingleDelivery> list= new  ArrayList<>();
		 String query = ("SELECT * FROM zerli.orders WHERE Status = 'Approved' AND SupplyType ='Delivery';");
		 try {
				PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					int OrderNumber = rs.getInt("OrderNumber");
					String StoreID = rs.getString("StoreID");
					String SupplyDate = rs.getString("SupplyDate");
					String Adress = rs.getString("SupplyAdress");
					String recivername= rs.getString("RecieverName");
					String reciverphone=rs.getString("RecieverPhone");
					String dorder=rs.getString("Dorder");
					list.add(new SingleDelivery(OrderNumber,StoreID,SupplyDate,Adress,recivername,reciverphone,dorder));
					}
			} catch (SQLException e) {
				return null;
			}
			return list;
	}
	public static ArrayList<SingleDelivery> getSingleDeliveryByStoreId(String Storeid)
	{
		ArrayList<SingleDelivery> list= new  ArrayList<>();
		 String query = ("SELECT * FROM zerli.orders WHERE Status = 'Approved' AND StoreID = '" + Storeid + "' AND SupplyType ='Delivery';");
		 try {
				PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					int OrderNumber = rs.getInt("OrderNumber");
					String StoreID = rs.getString("StoreID");
					String SupplyDate = rs.getString("SupplyDate");
					String Adress = rs.getString("SupplyAdress");
					String recivername= rs.getString("RecieverName");
					String reciverphone=rs.getString("RecieverPhone");
					String dorder=rs.getString("Dorder");
					list.add(new SingleDelivery(OrderNumber,StoreID,SupplyDate,Adress,recivername,reciverphone,dorder));
					}
			} catch (SQLException e) {
				return null;
			}
			return list;
	}
	public static ArrayList<SingleDelivery> getSingleDeliveryByOrderId(String Orderid)
	{
		ArrayList<SingleDelivery> list= new  ArrayList<>();
		 String query = ("SELECT * FROM zerli.orders WHERE Status = 'Approved' AND OrderNumber = '" + Orderid + "' AND SupplyType ='Delivery';");
		 try {
				PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					int OrderNumber = rs.getInt("OrderNumber");
					String StoreID = rs.getString("StoreID");
					String SupplyDate = rs.getString("SupplyDate");
					String Adress = rs.getString("SupplyAdress");
					String recivername= rs.getString("RecieverName");
					String reciverphone=rs.getString("RecieverPhone");
					String dorder=rs.getString("Dorder");
					list.add(new SingleDelivery(OrderNumber,StoreID,SupplyDate,Adress,recivername,reciverphone,dorder));
					}
			} catch (SQLException e) {
				return null;
			}
			return list;
	}
	public static String UpdateDeliveryStatus(String Orderid)
	{
		 String query = ("UPDATE orders SET Status= 'Delivered' WHERE OrderNumber = '" + Orderid + "';");
			try {
				PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
				st.executeUpdate();
			} catch (SQLException e) {
				return "False";
			}
			return "True";
	}
	public static String InsertSingleSelfDelivery(SingleSelfDelivery us)
	{
		String query = ("INSERT INTO zerli.delivery VALUES(" + us.getOrderID() + ",'" +us.getHandle_id()+ "','" +us.getCustomerSupplyDate() +
				"','" + us.getDeliverySupplyDate() + "');");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			return "False";
		}
		return "True";
	}
	public static void UpdateRefundByOrderId(String orderid)
	{
		int order_id= Integer.parseInt(orderid);
		double order_price=0;
		String account_refund=null;
		String user_id = null;
		String query = ("SELECT * FROM zerli.orders WHERE OrderNumber = " + order_id + ";");
		 try {
				PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
						order_price = rs.getDouble("Price");
						user_id= rs.getString("UserID");
					}
			} catch (SQLException e) {
				return;
			}
			String query2 = ("SELECT TotalRefund FROM zerli.account_details WHERE  User_ID= '" + user_id + "';");
			 try {
					PreparedStatement st2 = ConnectToDB.conn.prepareStatement(query2);
					ResultSet rs2 = st2.executeQuery();
					while (rs2.next()) {
						account_refund = rs2.getString("TotalRefund");
						}
				} catch (SQLException e) {
					return;
				}
			double total_refund = Double.parseDouble(account_refund);  
			double new_refund = order_price + total_refund;
			String new_total_refund = String.valueOf(new_refund);
			
			
			 String query3 = ("UPDATE account_details SET TotalRefund= '" + new_total_refund +"' WHERE User_ID = '" + user_id + "';");
				try {
					PreparedStatement st3 = ConnectToDB.conn.prepareStatement(query3);
					st3.executeUpdate();
				} catch (SQLException e) {
					return;
				}	
	}
	
	public static ArrayList<SingleSelfDelivery> getSingleSelfOrder(String handleUser)
	{
		ArrayList<SingleSelfDelivery> list= new  ArrayList<>();
		 String query = ("SELECT * FROM zerli.delivery WHERE HandelerId = '" + handleUser + "';");
		 try {
				PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					int Orderid = rs.getInt("OrderID");
					String handelerId = rs.getString("HandelerId");
					String customerSupplyDate = rs.getString("CustomerSupplyDate");
					String derliverySupplyDate= rs.getString("DerliverySupplyDate");
					list.add(new SingleSelfDelivery(Orderid,handelerId,customerSupplyDate,derliverySupplyDate));
					}
			} catch (SQLException e) {
				return null;
			}
			return list;
	}
	public static ArrayList<SingleSelfDelivery> getSingleSelfOrderByOrderId(String orderid)
	{
		ArrayList<SingleSelfDelivery> list= new  ArrayList<>();
		 String query = ("SELECT * FROM zerli.delivery WHERE OrderID = '" + orderid + "';");
		 try {
				PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					int Orderid = rs.getInt("OrderID");
					String handelerId = rs.getString("HandelerId");
					String customerSupplyDate = rs.getString("CustomerSupplyDate");
					String derliverySupplyDate= rs.getString("DerliverySupplyDate");
					list.add(new SingleSelfDelivery(Orderid,handelerId,customerSupplyDate,derliverySupplyDate));
					}
			} catch (SQLException e) {
				return null;
			}
			return list;
	}
	
}