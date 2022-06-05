package logic;

import java.io.Serializable;

public class SingleWorker implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7899961195112646578L;
	private String ID;
	private String firstName;
	private String lastName;
	private String role;
	
	
	public SingleWorker(String iD, String firstName, String lastName, String role) {
		super();
		ID = iD;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}


	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "SingleWorker [ID=" + ID + ", firstName=" + firstName + ", lastName=" + lastName + ", role=" + role
				+ "]";
	}
	

}
