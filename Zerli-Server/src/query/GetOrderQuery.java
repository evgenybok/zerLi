package query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import logic.Order;
import logic.SingleManageOrder;
import logic.SingleOrder;

/**
 * @author Evgeny
 * this class contains 'Order retreival' related queries based on the DB.
 *
 */
public class GetOrderQuery {

	/**
	 * @param userId - id number of user.
	 * returns all the orders from the DB made by the given user id number.
	 * @return ArrayList of logic Single Order.
	 */
	public static ArrayList<SingleOrder> GetOrders(String userId) {
		String query = ("SELECT * FROM zerli.orders WHERE UserID = '" + userId + "';");
		ArrayList<SingleOrder> orders = new ArrayList<SingleOrder>();
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int OrderNumber = rs.getInt("OrderNumber");
				double Price = rs.getDouble("Price");
				String StoreID = rs.getString("StoreID");
				String OrderDate = rs.getString("OrderDate");
				String SupplyDate = rs.getString("SupplyDate");
				String Status = rs.getString("Status");
				String SupplyType = rs.getString("SupplyType");
				double Refund = rs.getDouble("Refund");
				orders.add(new SingleOrder(OrderNumber, Price, StoreID, OrderDate, SupplyDate, SupplyType, Refund,
						Status));
			}
		} catch (SQLException e) {

		}
		return orders;
	}
	/**
	 * @param updatedData - order number, color, date.
	 * Method updates color and date of an order in the DB based on given string(color,date)
	 * @return true if successful, false otherwise.
	 */
	public static boolean Update(String updatedData) {
		String[] str = updatedData.split("#", 3);
		int ordNum = Integer.parseInt(str[0]);
		String color = str[1];
		String date = str[2];
		String query = ("SELECT * FROM zerli.orders;");

		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int num = rs.getInt("orderNumber");
				if (num == ordNum) {
					String color1 = rs.getString("Color");
					String date1 = rs.getString("date");
					if (color.equals("")) {
						color = color1;
					}
					if (date.equals("")) {
						date = date1;
					}

					String query1 = ("UPDATE zerli.orders SET Color=" + "\"" + color + "\"" + " , " + "Date=" + "\""
							+ date + "\"" + " WHERE orderNumber = " + ordNum + ";");
					st.executeUpdate(query1);
					return true;
				}

			}
		} catch (SQLException e) {
			return false;
		}
		return false;
	}

	/**
	 * @param orderNumber - serial number of order.
	 * retrieves the selected order based on given order number from the DB.
	 * @return String with details of order, Error string if failed.
	 */
	public static String getSelectedOrder(int orderNumber) {
		String query = ("SELECT * FROM zerli.iteminorder;");
		StringBuilder res = new StringBuilder();
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			res.append(orderNumber);
			res.append("#");
			while (rs.next()) {
				int OrderID = rs.getInt("orderID");
				int itemID = rs.getInt("itemID");
				int amount = rs.getInt("amount");
				if (orderNumber == OrderID) {
					res.append(itemID);
					res.append("#");
					res.append(amount);
					res.append("#");
				}
			}
			// ordernum, itemid, amount, itemid, amout
			res.append("@");
			if (res.toString().equals("@"))
				return null; // no details
			return res.toString();
		} catch (SQLException e) {
			return "Error";
		}
	}

	/**
	 * @param order logic Order, contains order details.
	 * Inserts into the DB new given order.
	 * @throws SQLException
	 */
	public static void InsertNewOrder(Order order) throws SQLException {
		int lastOrderNumber = 0;
		String sql = "SELECT MAX(OrderNumber) AS OrderNumber FROM orders";
		PreparedStatement ps = ConnectToDB.conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			lastOrderNumber = rs.getInt("OrderNumber"); // access the max value
		}
		lastOrderNumber++;
		String query = "INSERT INTO zerli.orders VALUES(" + lastOrderNumber + "," + order.getPrice() + ",'"
				+ order.getGreetingCard() + "','" + order.getShop() + "','" + order.getOrderDate() + "','"
				+ order.getSupplyDate() + "','" + order.getStatus() + "','" + order.getSupplyType() + "','"
				+ order.getUserID() + "'," + order.getRefund() + ",'" + order.getSupplyAddress() + "','"
				+ order.getReceiverName() + "','" + order.getReceiverPhone() + "','" + order.getDOrder() + "');";
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			return;
		}
		return;
	}

	/**
	 * @param orderID - order number, user id.
	 * returns all given orders made by given user id from the DB.
	 * @return array list of logic Single Order.
	 */
	public static ArrayList<SingleOrder> GetOrderByIdAndUserId(String orderID) {
		String[] user = orderID.split("@", 2);
		String query = ("SELECT * FROM zerli.orders WHERE OrderNumber = '" + user[0] + "' AND UserID ='" + user[1]
				+ "';");
		ArrayList<SingleOrder> orders = new ArrayList<SingleOrder>();
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int OrderNumber = rs.getInt("OrderNumber");
				double Price = rs.getDouble("Price");
				String StoreID = rs.getString("StoreID");
				String OrderDate = rs.getString("OrderDate");
				String SupplyDate = rs.getString("SupplyDate");
				String Status = rs.getString("Status");
				String SupplyType = rs.getString("SupplyType");
				double Refund = rs.getDouble("Refund");
				orders.add(new SingleOrder(OrderNumber, Price, StoreID, OrderDate, SupplyDate, SupplyType, Refund,
						Status));
				return orders;
			}
		} catch (SQLException e) {
			orders = new ArrayList<SingleOrder>();
			return orders;
		}
		return orders;
	}

	/**
	 * @param singleOrder - logic Single Order, contains order details.
	 * updates in the DB the order status to canceled and the given refund.
	 */
	public static void CancelOrder(SingleOrder singleOrder) {
		String query = ("UPDATE orders SET Status = 'Cancel Request', Refund =" + singleOrder.getRefund()
				+ " WHERE OrderNumber = '" + singleOrder.getOrderNumber() + "';");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}
	
	/**
	 * @param orderID - order number, branch manager id.
	 * returns all orders from a specific store
	 * @return array list of logic Single Order.
	 */
	public static ArrayList<SingleManageOrder> GetOrderById(String[] details) {
		String orderID = details[0];
		String bmID = details[1];
		String query = ("SELECT * FROM zerli.stores;");
		String tempStoreID = null;
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				if (bmID.equals(rs.getString("IDmanager"))) {
					tempStoreID = rs.getString("IDStore");
				}
			}

		} catch (SQLException e) {
			return null;
		}
		String query1 = ("SELECT * FROM zerli.orders WHERE OrderNumber = '" + orderID + "';");
		ArrayList<SingleManageOrder> list = new ArrayList<>();
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query1);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int orderId = rs.getInt("OrderNumber");
				String userId = rs.getString("UserID");
				double price = rs.getInt("Price");
				String orderDate = rs.getString("OrderDate");
				String SupplyType = rs.getString("SupplyType");
				String status = rs.getString("Status");
				String StoreID = rs.getString("StoreID");
				if (tempStoreID.equals(StoreID)) {
					list.add(new SingleManageOrder(orderId, userId, price, orderDate, SupplyType, status));
				}
			}
		} catch (SQLException e) {
			list = new ArrayList<SingleManageOrder>();
			return list;
		}
		return list;
	}

	/**
	 * @param details- refund to the user, user id number.
	 * updates the total refund in the DB based on the given string details.
	 */
	public static void UpdateRefundUsed(String details) {
		String[] str = details.split("@", 2);
		double refundUsed = Double.parseDouble(str[0]);
		double totalRefund = 0;
		String query = ("SELECT TotalRefund FROM zerli.account_details WHERE User_ID = '" + str[1] + "';");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				totalRefund = rs.getDouble("TotalRefund");
			}
		} catch (SQLException e) {

		}
		double updatedRefund = totalRefund - refundUsed;
		String query2 = ("UPDATE account_details SET TotalRefund=" + updatedRefund + " WHERE User_ID = '" + str[1]
				+ "';");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query2);
			st.executeUpdate();
		} catch (SQLException e) {
			return;

		}
	}

	/**
	 * Retrieves from the DB all of the given managers orders using his ID
	 * @param bmID
	 * @return Arraylist of SingleManageOrder logic containing manager orders.
	 */
	public static ArrayList<SingleManageOrder> GetManagerOrders(String bmID) {
		String query = ("SELECT * FROM zerli.stores;");
		String tempStoreID = null;
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				if (bmID.equals(rs.getString("IDmanager"))) {
					tempStoreID = rs.getString("IDStore");
				}
			}

		} catch (SQLException e) {
			return null;
		}
		String query1 = ("SELECT * FROM zerli.orders WHERE Status='Pending' OR Status='Cancel Request'");
		ArrayList<SingleManageOrder> orders = new ArrayList<SingleManageOrder>();
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query1);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int orderId = rs.getInt("OrderNumber");
				String userId = rs.getString("UserID");
				double price = rs.getInt("Price");
				String orderDate = rs.getString("OrderDate");
				String SupplyType = rs.getString("SupplyType");
				String status = rs.getString("Status");
				String StoreID = rs.getString("StoreID");
				if (tempStoreID.equals(StoreID))
					orders.add(new SingleManageOrder(orderId, userId, price, orderDate, SupplyType, status));
			}
		} catch (SQLException e) {
			return null;
		}
		return orders;
	}

	/**
	 * Updates order status to Approved in DB given an order ID number
	 * @param OrderId
	 */
	public static void UpdateOrderStatusByManager(String OrderId) {
		String query = ("UPDATE zerli.orders SET Status='Approved' WHERE OrderNumber=" + OrderId + ";");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}

	/**
	 * Updates order status to Cancelled in DB given an order ID number
	 * @param OrderId
	 */
	public static void CancelOrderStatusByManager(String OrderId) {
		String query = ("UPDATE zerli.orders SET Status='Cancelled' WHERE OrderNumber=" + OrderId + ";");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		double totalRefund = 0;
		double neededRefund = 0;
		String userID = null;
		String query1 = ("SELECT * FROM zerli.orders WHERE OrderNumber=" + OrderId + ";");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query1);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				neededRefund = rs.getDouble("Refund");
				userID = rs.getString("UserID");
			}
		} catch (SQLException e) {

		}
		String query2 = ("SELECT TotalRefund FROM zerli.account_details WHERE User_ID = '" + userID + "';");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query2);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				totalRefund = rs.getDouble("TotalRefund");
			}
		} catch (SQLException e) {

		}
		double updatedRefund = totalRefund + neededRefund;
		String query3 = ("UPDATE account_details SET TotalRefund=" + updatedRefund + " WHERE User_ID = '" + userID
				+ "';");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query3);
			st.executeUpdate();
		} catch (SQLException e) {
			return;

		}

	}
	
	/**
	 * Retrieves store ID number given order ID number
	 * @param orderID
	 * @return String store ID number
	 */
	public static String GetStoreIDByOrderID(String orderID)
	{
		String query = ("SELECT * FROM zerli.orders WHERE OrderNumber=" + "'orderID';");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			st.executeQuery();
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				return rs.getString("StoreID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
