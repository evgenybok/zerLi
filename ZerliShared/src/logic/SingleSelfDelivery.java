package logic;

import java.io.Serializable;

public class SingleSelfDelivery implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int OrderID;
	private String handle_id;
	private String customerSupplyDate;
	private String deliverySupplyDate;
	public SingleSelfDelivery(int orderID, String handle_id, String customerSupplyDate, String deliverySupplyDate) {
		this.OrderID = orderID;
		this.handle_id = handle_id;
		this.customerSupplyDate = customerSupplyDate;
		this.deliverySupplyDate = deliverySupplyDate;
	}
	@Override
	public String toString() {
		return "SingleSelfDelivery [OrderID=" + OrderID + ", handle_id=" + handle_id + ", customerSupplyDate="
				+ customerSupplyDate + ", deliverySupplyDate=" + deliverySupplyDate + "]";
	}
	public int getOrderID() {
		return OrderID;
	}
	public void setOrderID(int orderID) {
		OrderID = orderID;
	}
	public String getHandle_id() {
		return handle_id;
	}
	public void setHandle_id(String handle_id) {
		this.handle_id = handle_id;
	}
	public String getCustomerSupplyDate() {
		return customerSupplyDate;
	}
	public void setCustomerSupplyDate(String customerSupplyDate) {
		this.customerSupplyDate = customerSupplyDate;
	}
	public String getDeliverySupplyDate() {
		return deliverySupplyDate;
	}
	public void setDeliverySupplyDate(String deliverySupplyDate) {
		this.deliverySupplyDate = deliverySupplyDate;
	}
	
	
	


}
