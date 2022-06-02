package query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import logic.SingleDelivery;


public class DeliveryQuery {
	
	public static ArrayList<SingleDelivery> getOrderForDelivey()
	{
		ArrayList<SingleDelivery> list= new  ArrayList<>();
		 String query = ("SELECT * FROM zerli.orders WHERE Status = 'Approved';");
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
					list.add(new SingleDelivery(OrderNumber,StoreID,SupplyDate,Adress,recivername,reciverphone,dorder));				}
			} catch (SQLException e) {
				return null;
			}
			return list;
	}

}
