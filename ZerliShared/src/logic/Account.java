package logic;

import java.io.Serializable;

public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String User_ID;
	private String CreditCardNumber;
	private String ExpiryDate;
	private String CVV;
	private double TotalRefund;
	private String Status;
	private int ordersAmount;

	public Account(String User_ID, String CreditCardNumber, String ExpiryDate, String CVV, double TotalRefund,
			String Status, int ordersAmount) {
		this.User_ID = User_ID;
		this.CreditCardNumber = CreditCardNumber;
		this.ExpiryDate = ExpiryDate;
		this.CVV = CVV;
		this.TotalRefund = TotalRefund;
		this.Status = Status;
		this.ordersAmount = ordersAmount;
	}

	public String getUser_ID() {
		return User_ID;
	}

	public void setUser_ID(String user_ID) {
		User_ID = user_ID;
	}

	public String getCreditCardNumber() {
		return CreditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		CreditCardNumber = creditCardNumber;
	}

	public String getExpiryDate() {
		return ExpiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		ExpiryDate = expiryDate;
	}

	public String getCVV() {
		return CVV;
	}

	public void setCVV(String cVV) {
		CVV = cVV;
	}

	public double getTotalRefund() {
		return TotalRefund;
	}

	public void setTotalRefund(double totalRefund) {
		TotalRefund = totalRefund;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getOrdersAmount() {
		return ordersAmount;
	}

	public void setOrdersAmount(int ordersAmount) {
		this.ordersAmount = ordersAmount;
	}

}
