package query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import logic.SingleDelivery;
import logic.SingleSelfDelivery;

/**
 * @author Evgeny
 * this class contains 'Delivery' related queries based on the DB.
 *
 */
public class DeliveryQuery {
	
	/**
	 * Method returns an array list of orders which are approved and are ready for delivery.
	 * @return Array list of orders which are ready for delivery.
	 */
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
	/**
	 * @param Storeid - id number of store.
	 * Method returns an array list of deliveries which are approved, have given store id, and are ready for delivery.
	 * @return Array list of logic Single Delivery.
	 */
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
	/**
	 * @param Orderid - id number of order.
	 * Method returns an array list of deliveries which are approved, have given order id, and are ready for delivery.
	 * @return Array list of logic Single Delivery.
	 */
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
	/**
	 * @param Orderid - id number of order.
	 * Method updates the delivery status of the given order id number.
	 * @return String true if update was successful, string false otherwise.
	 */
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
	/**
	 * @param us - logic Single Self Delivery, contains delivery details.
	 * Inserts given delivery to the DB.
	 * @return String true if inserted, string false otherwise.
	 */
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
	/**
	 * @param orderid - id number of order.
	 * Updates total refund amount in DB based on given order id number.
	 */
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
	/**
	 * @param handleUser - id number of the delivery handler.
	 * Method returns an array list of logic Single Self Delivery from the DB with the given handler ID number.
	 * @return Arraylist of logic Single Self Delivery.
	 */
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
	
	/**
	 * @param orderid - order id number.
	 * Method returns an array list of logic Single Self Delivery from the DB with the given order ID number.
	 * @return Arraylist of logic Single Self Delivery.
	 */
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
