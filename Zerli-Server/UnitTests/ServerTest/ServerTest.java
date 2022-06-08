package ServerTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.ServerController;
import logic.User;
import mainserver.EchoServer;
import query.ConnectToDB;
import query.LoginQuery;
import query.Query;

class ServerTest {
	private LoginQuery Q;
	private static Connection conn;
	String customerUserName, customerPassword, customerRole;
	String fakeUsername, fakePassword, fakeRole;
	boolean status;

	@BeforeEach
	void setUp() throws Exception {
		EchoServer.serverController = new ServerController();
		conn = ConnectToDB.connect("root", "ekzit123", "jdbc:mysql://localhost/zerli?serverTimezone=IST");
		Q = new LoginQuery(conn);
		Query.DisconnectAll();

		// setup for tests #1,#2 to check if user exists.
		customerUserName = "cu1";
		customerPassword = "cu1";
		customerRole = "customer";

		// setup for test #3,#4 to check if login fails when giving wrong user info.
		fakeUsername = "fakeUsername";
		fakePassword = "fakePassword";
	}
//--------------------------------Tests for login------------------------------

	/**
	 * test #1 Checks if query returns not empty user. input: username=cu1 ,
	 * password=cu1 , role=customer. Expected: true.
	 */
	@Test
	void testClientExists() {
		User actualUser = Q.ExistenceCheck(customerUserName, customerPassword);
		assertTrue(actualUser.getUsername().equals(customerUserName));
		assertTrue(actualUser.getPassword().equals(customerPassword));
		assertTrue(actualUser.getRole().equals(customerRole));
		Query.Disconnect(actualUser);
	}

	/**
	 * test #2 Checks if query returns empty user when user is already connected.
	 * input: username=cu1 , password=cu1. Expected: true.
	 */
	@Test
	void testClientAlreadyConnected() {
		User actualUser = Q.ExistenceCheck(customerUserName, customerPassword);
		User nullUser = Q.ExistenceCheck(customerUserName, customerPassword);
		assertTrue(nullUser == null);
		Query.Disconnect(actualUser);
	}

	/**
	 * test #3 Checks if query returns empty user when username does not exist.
	 * input: username=fakeUsername , password=cu1. Expected: true.
	 */
	@Test
	void testClientUsernameDoesntExist() {
		User actualUser = Q.ExistenceCheck(fakeUsername, customerPassword);
		assertTrue(actualUser == null);
	}

	/**
	 * test #4 Checks if query returns empty user when password is wrong. input:
	 * username=cu1 , password=fakePassword. Expected: true.
	 */
	@Test
	void testClientPasswordDoesntExist() {
		User actualUser = Q.ExistenceCheck(customerUserName, fakePassword);
		assertTrue(actualUser == null);
	}

	/**
	 * test #5 Checks if query returns empty user when password is wrong. input:
	 * username=null , password=null. Expected: true.
	 */
	@Test
	void testClientNullValues() {
		User actualUser = Q.ExistenceCheck(null, null);
		assertTrue(actualUser == null);
	}

	/**
	 * test #6 Checks if query catches exception when connection is closed. input:
	 * username=cu1 , password=cu1. Expected: true.
	 */
	@Test
	void testClientCatchException() {
		User actualUser = null;
		try {
			conn.close();
			actualUser = Q.ExistenceCheck(customerUserName, customerPassword);
		} catch (SQLException e) {
			assertTrue(actualUser == null);
		}
		try { // Restore connection
			conn = ConnectToDB.connect("root", "ekzit123", "jdbc:mysql://localhost/zerli?serverTimezone=IST");
		} catch (Exception e) {
		}
	}
}
