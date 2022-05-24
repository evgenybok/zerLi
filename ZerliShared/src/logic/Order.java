package logic;

import java.io.Serializable;

public class Order implements Serializable {
	/**
	 * 
	 */
	public static int orderCount=1;
	private static final long serialVersionUID = -4476848811757027097L;
	private int orderNumber;
	private double price;
	private String greetingCard;
	//private String color;
//	private String dOrder;
	private String shop;
	private String orderDate;
	private String supplyDate;
	private String Status;
	private String supplyType;
	private String userID;
	private String refund;
	private String supplyAddress;
	private String receiverName;
	private String receiverPhone;

	public Order(int orderNumber, double price, String greetingCard, String shop,
			String orderDate,String supplyDate, String Status,String SupplyType,String userID, String refund,String supplyAddress,
			String receiverName,String receiverPhone) {
		this.orderNumber = orderNumber;
		this.price = price;
		this.greetingCard = greetingCard;
		//this.color = color;
		//this.dOrder = dOrder;
		this.shop = shop;
		this.orderDate = orderDate;
		this.supplyDate = supplyDate;
		this.Status = Status;
		this.supplyType = SupplyType;
		this.userID = userID;
		this.refund = refund;
		this.supplyAddress = supplyAddress;
		this.receiverName = receiverName;
		this.receiverPhone = receiverPhone;
	}
	public String getSupplyDate() {
		return supplyDate;
	}

	public void setSupplyDate(String supplyDate) {
		this.supplyDate = supplyDate;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverName;
	}
	
	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	public String getSupplyAddress() {
		return supplyAddress;
	}

	public void setSupplyAddress(String supplyAddress) {
		this.supplyAddress = supplyAddress;
	}
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getRefund() {
		return refund;
	}

	public void setRefund(String refund) {
		this.refund = refund;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(String supplyType) {
		this.supplyType = supplyType;
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

	/*
	 * public String getColor() { return color; }
	 * 
	 * public void setColor(String color) { this.color = color; }
	 * 
	 * public String getDOrder() { return dOrder; }
	 * 
	 * public void setdOrder(String dOrder) { this.dOrder = dOrder; }
	 */

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getDate() {
		return supplyDate;
	}

	public void setDate(String date) {
		this.supplyDate = date;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "Order [orderNumber=" + orderNumber + ", price=" + price + ", greeting Card=" + greetingCard +
				", shop=" + shop + ", OrderDate=" + orderDate + ", SupplyDate=" + supplyDate +", Status=" + Status + 
				", UserID=" + userID + ", Refund=" + refund + ", SupplyAddress=" + supplyAddress + ", ReceiverName=" + receiverName +
				", ReceiverPhone=" + receiverPhone +   "]";
	}
}