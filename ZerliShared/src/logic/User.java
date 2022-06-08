package logic;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String Username;
	private String Password;
	private boolean LoggedIn;
	private String ID;
	private String FirstName;
	private String LastName;
	private String Role;
	private String PhoneNumber;
	private String Email;
	private boolean Exists;

	public User(String username, String password, boolean loggedIn, String iD, String firstName, String lastName,
			String role, String phoneNumber, String email) {
		super();
		Username = username;
		Password = password;
		LoggedIn = loggedIn;
		ID = iD;
		FirstName = firstName;
		LastName = lastName;
		Role = role;
		PhoneNumber = phoneNumber;
		Email = email;
	}

	public User(String iD,String username, String password, String role)
	{
		ID=iD;
		Username=username;
		Password=password;
		Role=role;
	}
	
	public User(String username, String password, String role)
	{
		Username=username;
		Password=password;
		Role=role;
	}
	
	public User(String username, String password) {
		super();
		Username = username;
		Password = password;
	}

	public User(String username, String password, boolean loggedIn, String iD, String role, boolean exists) {
		super();
		Username = username;
		Password = password;
		LoggedIn = loggedIn;
		ID = iD;
		Role = role;
		Exists = exists;
	}

	public User(String username, String password, boolean loggedIn, String iD, String firstName, String lastName,
			String role, String phoneNumber, String email, boolean exists) {
		super();
		Username = username;
		Password = password;
		LoggedIn = loggedIn;
		ID = iD;
		FirstName = firstName;
		LastName = lastName;
		Role = role;
		PhoneNumber = phoneNumber;
		Email = email;
		Exists = exists;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public boolean isLoggedIn() {
		return LoggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		LoggedIn = loggedIn;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	@Override
	public String toString() {
		return "User [Username=" + Username + ", Password=" + Password + ", LoggedIn=" + LoggedIn + ", ID=" + ID
				+ ", FirstName=" + FirstName + ", LastName=" + LastName + ", Role=" + Role + ", PhoneNumber="
				+ PhoneNumber + ", Email=" + Email + "]";
	}

	public boolean isExists() {
		return Exists;
	}

	public void setExists(boolean exists) {
		Exists = exists;
	}
}