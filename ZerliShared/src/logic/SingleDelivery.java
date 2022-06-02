package logic;

import java.io.Serializable;

public class SingleDelivery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int orderid;
	private String storename;
	private String supplydate;
	private String address;
	private String recivername;
	private String reciverphone;
	private String Dorder;
	
	public SingleDelivery(int orderid,String storename,String supplydate,String address,String recivername,String reciverphone,String Dorder)
	{
		this.orderid=orderid;
		this.storename= storename;
		this.supplydate=supplydate;
		this.address=address;
		this.recivername=recivername;
		this.reciverphone=reciverphone;
		this.Dorder=Dorder;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public String getSupplydate() {
		return supplydate;
	}

	public void setSupplydate(String supplydate) {
		this.supplydate = supplydate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRecivername() {
		return recivername;
	}

	public void setRecivername(String recivername) {
		this.recivername = recivername;
	}

	public String getReciverphone() {
		return reciverphone;
	}

	public void setReciverphone(String reciverphone) {
		this.reciverphone = reciverphone;
	}

	public String getDorder() {
		return Dorder;
	}

	public void setDorder(String dorder) {
		Dorder = dorder;
	}

	@Override
	public String toString() {
		return "SingleDelivery [orderid=" + orderid + ", storename=" + storename + ", supplydate=" + supplydate
				+ ", address=" + address + ", recivername=" + recivername + ", reciverphone=" + reciverphone
				+ ", Dorder=" + Dorder + "]";
	}	
	
	

}
