package logic;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Complain  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String HandleUserID;
	private String complainUserID;
	private int orderID;
	private String description;
	private String ComplainStatus;
	private double Refund;
	private String storeID;
	private LocalDateTime dateCreated;
	private boolean ReminderToHandle;

	public Complain(String HandleUserID,String complainUserID,int orderID,String description,String ComplainStatus,double Refund,String storeID,LocalDateTime dateCreated,boolean ReminderToHandle)
	{
		this.HandleUserID=HandleUserID;
		this.complainUserID=complainUserID;
		this.orderID=orderID;
		this.description=description;
		this.ComplainStatus= ComplainStatus;
		this.Refund=Refund;
		this.storeID=storeID;
		this.dateCreated=dateCreated;
		this.ReminderToHandle=ReminderToHandle;
	}
	public String getHandleUserID() {
		return HandleUserID;
	}

	public void setHandleUserID(String handleUserID) {
		HandleUserID = handleUserID;
	}

	public String getComplainUserID() {
		return complainUserID;
	}

	public void setComplainUserID(String complainUserID) {
		this.complainUserID = complainUserID;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComplainStatus() {
		return ComplainStatus;
	}

	public void setComplainStatus(String complainStatus) {
		ComplainStatus = complainStatus;
	}

	public double getRefund() {
		return Refund;
	}

	public void setRefund(double refund) {
		Refund = refund;
	}
	
	public String getStoreID() {
		return storeID;
	}
	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public boolean isReminderToHandle() {
		return ReminderToHandle;
	}
	public void setReminderToHandle(boolean reminderToHandle) {
		ReminderToHandle = reminderToHandle;
	}
	@Override
	public String toString() {
		return "Complain [HandleUserID=" + HandleUserID + ", complainUserID=" + complainUserID + ", orderID=" + orderID
				+ ", description=" + description + ", ComplainStatus=" + ComplainStatus + ", Refund=" + Refund
				+ ", storeID=" + storeID + ", dateCreated=" + dateCreated + ", ReminderToHandle=" + ReminderToHandle
				+ "]";
	}
	

}
