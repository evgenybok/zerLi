package query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import logic.Order;
import logic.SingleOrder;

public class GetOrderQuery {

	public static ArrayList<SingleOrder> GetOrders(String userId) {
		String query = ("SELECT * FROM zerli.orders WHERE UserID = '" + userId + "';");
		ArrayList<SingleOrder> orders = new ArrayList<SingleOrder>();
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int OrderNumber = rs.getInt("OrderNumber");
				int Price = rs.getInt("Price");
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
				int Price = rs.getInt("Price");
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

	public static void CancelOrder(SingleOrder singleOrder) {
		String query = ("UPDATE orders SET Status = 'Cancelled', Refund =" + singleOrder.getRefund()
				+ " WHERE OrderNumber = '" + singleOrder.getOrderNumber() + "';");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

	}

}