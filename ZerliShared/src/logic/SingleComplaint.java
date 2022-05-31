package logic;

import java.io.Serializable;

public class SingleComplaint implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userid;
	private String orderid;
	private String description;
	
	public SingleComplaint(String userid,String orderid,String description)
	{
		this.userid=userid;
		this.orderid=orderid;
		this.description=description;
	}
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
