package query;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import logic.Item;

/**
 * @author Evgeny
 * this class contains 'Catalog' related queries based on the DB.
 *
 */
public class CatalogQuery {

	/**
	 * @param updatedItem- item from the catalog, based on logic Item
	 * This method updates the item details in the DB.
	 */
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

	/**
	 * @param newItem - logic item, contains item details.
	 * Adds a new item to the DB.
	 */
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

	/**
	 * @param item- logic Item, contains item details.
	 * Deletes given item from the DB.
	 */
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
