package query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Evgeny this class contains 'Report' related queries based on the DB.
 *
 */
public class ReportQuery {

	/**
	 * @param StoreID - id number of store.
	 * @param Month   - number of month.
	 * @param Year    - number of year. Query that Creates an Income Report From
	 *                Table Orders and updates the incomereports table.
	 */
	public static void CreateIncomeReports(String StoreID, String Month, String Year) {
		String query1 = "INSERT INTO zerli.incomereport (Week1,Week2,Week3,Week4,StoreID,Month,Year) VALUES('" + 0
				+ "','" + 0 + "','" + 0 + "','" + 0 + "','" + StoreID + "','" + Month + "','" + Year + "');";

		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query1);
			st.executeUpdate();
		} catch (SQLException e) {
			return;
		}
		;

		String query = "SELECT FLOOR((DayOfMonth(OrderDate)-1)/7)+1\n" + " as week_of_year, SUM(Price) as Price \n"
				+ "FROM zerli.orders\n" + "where StoreID= '" + StoreID + "' AND month(OrderDate)='" + Month
				+ "' AND year(OrderDate)='" + Year + "' \n" + "group by FLOOR((DayOfMonth(OrderDate)-1)/7)+1 \n"
				+ "Order BY week_of_year;";

		try {
			PreparedStatement st1 = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs1 = st1.executeQuery();
			while (rs1.next()) {
				String week = rs1.getString("week_of_year");
				String price = rs1.getString("Price");
				String newWeek = "Week" + week;

				String query2 = "Update incomereport SET " + newWeek + " ='" + price + "' WHERE StoreID='" + StoreID
						+ "' AND Month='" + Month + "' AND Year='" + Year + "';";
				System.out.println(query2);
				try {
					PreparedStatement st2 = ConnectToDB.conn.prepareStatement(query2);
					st2.executeUpdate();
				} catch (SQLException e) {
					return;
				}

			}
		}

		catch (SQLException e) {
			return;

		}
		;

	}

	/**
	 * @param StoreID - id number of store.
	 * @param Month   - number of month.
	 * @param Year    - number of year.
	 * @return array list of the statistics of the graph.
	 */
	public static ArrayList<String> GetIncomeGraphStatistics(String StoreID, String Month, String Year) {

		String query = "SELECT * FROM zerli.incomereport WHERE StoreID= '" + StoreID + "' AND Month='" + Month
				+ "' AND Year='" + Year + "';";
		ArrayList<String> GraphStats = new ArrayList<>();

		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String Week1 = rs.getString("Week1");
				String Week2 = rs.getString("Week2");
				String Week3 = rs.getString("Week3");
				String Week4 = rs.getString("Week4");
				String currentMonth = rs.getString("Month");

				GraphStats.add(currentMonth);
				GraphStats.add(Week1);
				GraphStats.add(Week2);
				GraphStats.add(Week3);
				GraphStats.add(Week4);
			}

		} catch (SQLException e) {
			return null;
		}

		return GraphStats;
	}

	/**
	 * @param StoreID - id number of store.
	 * @param Quarter - number of quarter.
	 * @param Year    - number of year. Creates quarterly complaint report.
	 * @throws SQLException
	 */
	public static void CreateComplaintQuarterReports(String StoreID, String Quarter, String Year) throws SQLException {
		String query, query1, query2;
		query = "INSERT INTO zerli.complaintreport (StoreID,QuarterNumber,Year,Month1,Month2,Month3) VALUES('" + StoreID
				+ "','" + Quarter + "','" + Year + "','" + 0 + "','" + 0 + "','" + 0 + "');";

		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			return;
		}
		;

		switch (Quarter) {
		case "01":
			query = "SELECT Month(Date)as month,count(OrderID)as amount FROM zerli.complaint WHERE sotreID='" + StoreID
					+ "'AND month(Date) between '01'AND '03' AND year(Date)='" + Year + "' "
					+ "Group BY month order by month;";

			try {
				PreparedStatement st1 = ConnectToDB.conn.prepareStatement(query);
				ResultSet rs1 = st1.executeQuery();
				String month = null;
				String amount = null;
				while (rs1.next()) {
					month = rs1.getString("month");
					amount = rs1.getString("amount");
					String new_month = "Month" + month;
					String query_2 = "Update complaintreport SET " + new_month + " ='" + amount + "' WHERE StoreID='"
							+ StoreID + "' AND QuarterNumber='01' AND Year='" + Year + "';";
					try {
						PreparedStatement st2 = ConnectToDB.conn.prepareStatement(query_2);
						st2.executeUpdate();
					} catch (SQLException e) {
						return;
					}

				}

			} catch (SQLException e) {
				return;
			}

			break;
		case "02":
			query = "SELECT Month(Date)as month,count(OrderID)as amount FROM zerli.complaint WHERE sotreID='" + StoreID
					+ "'AND month(Date) between '04'AND '06' AND year(Date)='" + Year + "' "
					+ "Group BY month order by month;";

			try {

				PreparedStatement st1 = ConnectToDB.conn.prepareStatement(query);
				ResultSet rs1 = st1.executeQuery();
				String month = null;
				String amount = null;
				while (rs1.next()) {
					month = rs1.getString("month");
					amount = rs1.getString("amount");
					String new_month = "Month" + month;
					String query_2 = "Update complaintreport SET " + new_month + " ='" + amount + "' WHERE StoreID='"
							+ StoreID + "' AND QuarterNumber='02' AND Year='" + Year + "';";
					try {
						PreparedStatement st2 = ConnectToDB.conn.prepareStatement(query_2);
						st2.executeUpdate();
					} catch (SQLException e) {
						return;
					}

				}

			} catch (SQLException e) {
				return;
			}

			break;
		case "03":
			query = "SELECT Month(Date)as month,count(OrderID)as amount FROM zerli.complaint WHERE sotreID='" + StoreID
					+ "'AND month(Date) between '07'AND '09' AND year(Date)='" + Year + "' "
					+ "Group BY month order by month;";

			try {
				PreparedStatement st1 = ConnectToDB.conn.prepareStatement(query);
				ResultSet rs1 = st1.executeQuery();
				String month = null;
				String amount = null;
				while (rs1.next()) {

					month = rs1.getString("month");
					amount = rs1.getString("amount");
					String new_month = "Month" + month;
					String query_2 = "Update complaintreport SET " + new_month + " ='" + amount + "' WHERE StoreID='"
							+ StoreID + "' AND QuarterNumber='03' AND Year='" + Year + "';";
					try {
						PreparedStatement st2 = ConnectToDB.conn.prepareStatement(query_2);
						st2.executeUpdate();
					} catch (SQLException e) {
						return;
					}

				}

			} catch (SQLException e) {
				return;
			}

			break;
		case "04":
			query = "SELECT Month(Date)as month,count(OrderID)as amount FROM zerli.complaint WHERE sotreID='" + StoreID
					+ "'AND month(Date) between '10'AND '12' AND year(Date)='" + Year + "' "
					+ "Group BY month order by month;";

			try {
				PreparedStatement st1 = ConnectToDB.conn.prepareStatement(query);
				ResultSet rs1 = st1.executeQuery();
				String month = null;
				String amount = null;
				while (rs1.next()) {
					month = rs1.getString("month");
					amount = rs1.getString("amount");
					String new_month = "Month" + month;
					String query_2 = "Update complaintreport SET " + new_month + " ='" + amount + "' WHERE StoreID='"
							+ StoreID + "' AND QuarterNumber='04' AND Year='" + Year + "';";
					try {
						PreparedStatement st2 = ConnectToDB.conn.prepareStatement(query_2);
						st2.executeUpdate();
					} catch (SQLException e) {
						return;
					}

				}

			} catch (SQLException e) {
				return;
			}

			break;

		default:
		}

	}

	public static ArrayList<String> GetComplaintGraphStatistics(String StoreID, String Quarter, String Year) {
		String query = "SELECT * FROM zerli.complaintreport WHERE StoreID= '" + StoreID + "' AND QuarterNumber='"
				+ Quarter + "' AND Year='" + Year + "';";
		ArrayList<String> complaintGraphStats = new ArrayList<>();

		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String month1 = rs.getString("Month1");
				String month2 = rs.getString("Month2");
				String month3 = rs.getString("Month3");
				String currentQuarter = rs.getString("QuarterNumber");

				complaintGraphStats.add(currentQuarter);
				complaintGraphStats.add(month1);
				complaintGraphStats.add(month2);
				complaintGraphStats.add(month3);
			}

		} catch (SQLException e) {
			return null;
		}

		return complaintGraphStats;
	}

	public static void CreateOrderReports(String StoreID, String Month, String Year) {
		String query1 = "INSERT INTO zerli.orderreport (Week1,Week2,Week3,Week4,StoreID,Month,Year) VALUES('" + 0
				+ "','" + 0 + "','" + 0 + "','" + 0 + "','" + StoreID + "','" + Month + "','" + Year + "');";

		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query1);
			st.executeUpdate();
		} catch (SQLException e) {
			return;
		}
		;

		String query = "SELECT FLOOR((DayOfMonth(OrderDate)-1)/7)+1\n" + " as week_of_year, COUNT(*) as Amount \n"
				+ "FROM zerli.orders\n" + "where StoreID= '" + StoreID + "' AND month(OrderDate)='" + Month
				+ "' AND year(OrderDate)='" + Year + "' \n" + "group by FLOOR((DayOfMonth(OrderDate)-1)/7)+1 \n"
				+ "Order BY week_of_year;";

		try {
			PreparedStatement st1 = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs1 = st1.executeQuery();
			while (rs1.next()) {
				String week = rs1.getString("week_of_year");
				String amount = rs1.getString("Amount");
				String newWeek = "Week" + week;

				String query2 = "Update orderreport SET " + newWeek + " ='" + amount + "' WHERE StoreID='" + StoreID
						+ "' AND Month='" + Month + "' AND Year='" + Year + "';";
				System.out.println(query2);
				try {
					PreparedStatement st2 = ConnectToDB.conn.prepareStatement(query2);
					st2.executeUpdate();
				} catch (SQLException e) {
					return;
				}

			}
		} catch (SQLException e) {
			return;

		}
		;

	}

	public static ArrayList<String> GetOrderGraphStatistics(String StoreID, String Month, String Year) {

		String query = "SELECT * FROM zerli.orderreport WHERE StoreID= '" + StoreID + "' AND Month='" + Month
				+ "' AND Year='" + Year + "';";
		ArrayList<String> GraphStats = new ArrayList<>();

		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String Week1 = rs.getString("Week1");
				String Week2 = rs.getString("Week2");
				String Week3 = rs.getString("Week3");
				String Week4 = rs.getString("Week4");
				String currentMonth = rs.getString("Month");

				GraphStats.add(currentMonth);
				GraphStats.add(Week1);
				GraphStats.add(Week2);
				GraphStats.add(Week3);
				GraphStats.add(Week4);
			}

		} catch (SQLException e) {
			return null;
		}

		return GraphStats;
	}

	public static ArrayList<String> GetIncomeQuarterGraphStatistics(String StoreID, String Quarter, String Year) {

		ArrayList<String> arraylist = new ArrayList<>();
		String query;
		switch (Quarter) {
		case "01":
			query = "SELECT Month as month , (Week1 + Week2 + Week3 + Week4)as total FROM zerli.incomereport WHERE Month='01' OR Month='02' OR Month='03' AND StoreID='"
					+ StoreID + "' AND year(Year)='" + Year + "' OR Month='03' ORDER BY month;";

			try {
				PreparedStatement st1 = ConnectToDB.conn.prepareStatement(query);
				ResultSet rs1 = st1.executeQuery();
				String month = null;
				String total = null;
				while (rs1.next()) {
					month = rs1.getString("month");
					total = rs1.getString("total");
					arraylist.add(total);
				}

			} catch (SQLException e) {
				return null;
			}
			;

			return arraylist;

		case "02":
			query = "SELECT Month as month , (Week1 + Week2 + Week3 + Week4)as total FROM zerli.incomereport WHERE Month='04' OR Month='05' OR Month='06' AND StoreID='"
					+ StoreID + "' AND year(Year)='" + Year + "' OR Month='06' ORDER BY month;";

			try {
				PreparedStatement st1 = ConnectToDB.conn.prepareStatement(query);
				ResultSet rs1 = st1.executeQuery();
				String month = null;
				String total = null;
				while (rs1.next()) {
					month = rs1.getString("month");
					total = rs1.getString("total");
					arraylist.add(total);
				}

			} catch (SQLException e) {
				return null;
			}
			;

			return arraylist;

		case "03":
			query = "SELECT Month as month , (Week1 + Week2 + Week3 + Week4)as total FROM zerli.incomereport WHERE Month='07' OR Month='08' OR Month='09' AND StoreID='"
					+ StoreID + "' AND year(Year)='" + Year + "' OR Month='09' ORDER BY month;";

			try {
				PreparedStatement st1 = ConnectToDB.conn.prepareStatement(query);
				ResultSet rs1 = st1.executeQuery();
				String month = null;
				String total = null;
				while (rs1.next()) {
					month = rs1.getString("month");
					total = rs1.getString("total");
					arraylist.add(total);
				}

			} catch (SQLException e) {
				return null;
			}
			;

			return arraylist;
		case "04":
			query = "SELECT Month as month , (Week1 + Week2 + Week3 + Week4)as total FROM zerli.incomereport WHERE Month='10' OR Month='11' OR Month='12' AND StoreID='"
					+ StoreID + "' AND year(Year)='" + Year + "' OR Month='12' ORDER BY month;";

			try {
				PreparedStatement st1 = ConnectToDB.conn.prepareStatement(query);
				ResultSet rs1 = st1.executeQuery();
				String month = null;
				String total = null;
				while (rs1.next()) {
					month = rs1.getString("month");
					total = rs1.getString("total");
					arraylist.add(total);
				}

			} catch (SQLException e) {
				return null;
			}
			;

			return arraylist;

		default:
			return null;
		}
	}
}
