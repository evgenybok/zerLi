package logic;

import java.io.Serializable;

public class Login implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3629799086398079582L;

	private String userName;

	private String password;

	public Login(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String toString() {
		return "UserName: " + this.userName + "\nPassword: " + this.password;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
