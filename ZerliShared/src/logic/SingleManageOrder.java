package logic;

import java.io.Serializable;

public class SingleManageOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int orderId;
	private String userId;
	private double price;
	private String orderDate;
	private String supplyType;
	private String status;

	public SingleManageOrder (int orderId, String userId, double price, String orderDate, String SupplyType, String status)
	{
		this.orderId=orderId;
		this.userId=userId;
		this.price=price;
		this.orderDate=orderDate;
		this.supplyType=SupplyType;
		this.status=status;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(String supplyType) {
		this.supplyType = supplyType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "SingleDelivery [orderid=" + orderId + ", userid=" + userId + ", price=" + price
				+ ", orderDate=" + orderDate + ", supplydate=" + supplyType + ", status=" + status
				+"]";
	}	
}