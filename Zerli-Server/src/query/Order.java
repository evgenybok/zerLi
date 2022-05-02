package query;

import java.io.Serializable;

public class Order implements Serializable {
	private int orderNumber;
	private double price;
	private String greetingCard;
	private String color;
	private String dOrder;
	private String shop;
	private String date;
	private String orderDate;
	//public ArrayList<Item> itemList;

	public Order(int orderNumber, double price, String greetingCard, String color, String dOrder, String shop,
			String date, String orderDate) {
		this.orderNumber = orderNumber;
		this.price = price;
		this.greetingCard = greetingCard;
		this.color = color;
		this.dOrder = dOrder;
		this.shop = shop;
		this.date = date;
		this.orderDate = orderDate;
		//this.itemList = itemList;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getGreetingCard() {
		return greetingCard;
	}

	public void setGreetingCard(String greetingCard) {
		this.greetingCard = greetingCard;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getdOrder() {
		return dOrder;
	}

	public void setdOrder(String dOrder) {
		this.dOrder = dOrder;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
/*
	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}
*/
	@Override
	public String toString() {
		return "Order [orderNumber=" + orderNumber + ", price=" + price + ", greeting Card=" + greetingCard + ", Color="
				+ color + ", dOrder=" + dOrder + ", shop=" + shop + ", date=" + date + ", orderDate=" + orderDate + "]";
	}
}