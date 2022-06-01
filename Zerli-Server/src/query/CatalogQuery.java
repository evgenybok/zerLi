package query;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import logic.Item;

public class CatalogQuery {

	public static void UpdateCatalog(Item updatedItem) {
		String query = ("UPDATE item SET imgSrc = '" + updatedItem.getImgSrc() + "', Name ='" + updatedItem.getName()
				+ "',Price =" + updatedItem.getPrice() + ",Color ='" + updatedItem.getColor() + "',Type='"
				+ updatedItem.getType() + "',onSale = " + updatedItem.isOnSale() + ",salePrice = "
				+ updatedItem.getSalePrice() + " WHERE ID = '" + updatedItem.getID() + "';");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			return;
		}

	}

	public static void AddNewItem(Item newItem) {
		String query = ("INSERT INTO zerli.item VALUES(" + newItem.getID() + ",'" + newItem.getImgSrc() + "','"
				+ newItem.getName() + "'," + newItem.getPrice() + ",'" + newItem.getColor() + "','" + newItem.getType()
				+ "'," + newItem.isOnSale() + ", " + newItem.getSalePrice() + ");");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			return;
		}
	}

	public static void DeleteItem(Item item) {
		String query = ("DELETE FROM zerli.item WHERE id = " + item.getID() + ";");
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			return;
		}

	}
}
