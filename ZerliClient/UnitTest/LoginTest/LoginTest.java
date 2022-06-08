package LoginTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.IJavaFx;
import controllers.IMySqlConnection;
import controllers.LoginScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;

class LoginTest {
	public class StubMyLoginManager implements IMySqlConnection, IJavaFx {

		//JavaFX stub is used to distinguish the funcionality of different screens.
		@Override
		public void javaFxmlCustomer(MouseEvent event, FXMLLoader loader) throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void javaFxmlWorker(MouseEvent event, FXMLLoader loader) throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void javaFxmlDelivery(MouseEvent event, FXMLLoader loader) throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void javaFxmlManager(MouseEvent event, FXMLLoader loader) throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void javaFxmlCEO(MouseEvent event, FXMLLoader loader) throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void javaFxmlSpecialist(MouseEvent event, FXMLLoader loader) throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void javaFxmlService(MouseEvent event, FXMLLoader loader) throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void javaFxmlMarketing(MouseEvent event, FXMLLoader loader) throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void javaFxmlNotFound() {
			// TODO Auto-generated method stub

		}

		@Override
		public void javaFxmlAlreadyLogged() {
			// TODO Auto-generated method stub

		}

		@Override
		public void javaFxmlEmptyFields() {
			// TODO Auto-generated method stub

		}

		// Sets the details on screen.
		@Override
		public void setDetails() {
			loginScreenController.setDataField(Username, Password,Role);
		}

		@Override
		public String Login(String UserName, String Password) {
			return Role;
		}

	}

	LoginScreenController loginScreenController;
	IMySqlConnection mySqlConnection;
	IJavaFx javaFx;
	static String Role;
	static String Username;
	static String Password;

	@BeforeEach
	void setUp() throws Exception {
		mySqlConnection = new StubMyLoginManager();
		javaFx = new StubMyLoginManager();
		loginScreenController = new LoginScreenController(mySqlConnection, javaFx);
	}
//-------------------------Login user types tests----------------------------------
	
	@Test
	//Test #1
	//Checking the login of the customer with existing username and password
	//input: UserName=cu1, Password=cu1, Role = customer.
	//expected: return true.
	void testSuccessCustomerLogin() throws IOException {
		// simulates typing username and password in text field.
		Username = "cu1";
		Password = "cu1";
		Role = "customer";
		boolean actualRes = loginScreenController.btnLogin(null);
		assertTrue(actualRes);
	}
	@Test
	//Test #2
	//Checking the login of the branch manager with existing username and password
	//input: UserName=mn1, Password=mn1, Role = branch manager.
	//expected: return true.
	void testSuccessManagerLogin() throws IOException {
		// simulates typing username and password in text field.
		Username = "mn1";
		Password = "mn1";
		Role = "branch manager";
		boolean actualRes = loginScreenController.btnLogin(null);
		assertTrue(actualRes);
	}
	@Test
	//Test #3
	//Checking the login of the Worker with existing username and password
	//input: UserName=worker1, Password=worker1, Role = Worker.
	//expected: return true.
	void testSuccessWorkerLogin() throws IOException {
		// simulates typing username and password in text field.
		Username = "worker1";
		Password = "worker1";
		Role = "Worker";
		boolean actualRes = loginScreenController.btnLogin(null);
		assertTrue(actualRes);
	}
	@Test
	//Test #4
	//Checking the login of the Marketing with existing username and password
	//input: UserName=worker2, Password=worker2, Role = Marketing.
	//expected: return true.
	void testSuccessMarketingLogin() throws IOException {
		// simulates typing username and password in text field.
		Username = "worker2";
		Password = "worker2";
		Role = "Marketing";
		boolean actualRes = loginScreenController.btnLogin(null);
		assertTrue(actualRes);
	}
	@Test
	//Test #5
	//Checking the login of the customer specialist with existing username and password
	//input: UserName=sp, Password=sp, Role = customer specialist.
	//expected: return true.
	void testSuccessSpecialistLogin() throws IOException {
		// simulates typing username and password in text field.
		Username = "sp";
		Password = "sp";
		Role = "customer specialist";
		boolean actualRes = loginScreenController.btnLogin(null);
		assertTrue(actualRes);
	}
	@Test
	//Test #6
	//Checking the login of the customer service with existing username and password
	//input: UserName=sv, Password=sv, Role = customer service.
	//expected: return true.
	void testSuccessServiceLogin() throws IOException {
		// simulates typing username and password in text field.
		Username = "sv";
		Password = "sv";
		Role = "customer service";
		boolean actualRes = loginScreenController.btnLogin(null);
		assertTrue(actualRes);
	}
	@Test
	//Test #7
	//Checking the login of the ceo with existing username and password
	//input: UserName=ceo, Password=ceo, Role = ceo.
	//expected: return true.
	void testSuccessCEOLogin() throws IOException {
		// simulates typing username and password in text field.
		Username = "ceo";
		Password = "ceo";
		Role = "ceo";
		boolean actualRes = loginScreenController.btnLogin(null);
		assertTrue(actualRes);
	}
	
//---------------------------------------------------------------------------
	@Test
	//Test #8
	//Checking the login of a user that is already logged in.
	//input: UserName=cu1, Password=cu1, Role = customer.
	//expected: return false.
	void testAlreadyLoggedIn() throws IOException {
		// simulates typing username and password in text field.
		Username = "cu1";
		Password = "cu1";
		Role = "loggedIn";
		boolean actualRes = loginScreenController.btnLogin(null);
		assertFalse(actualRes);
	}
	@Test
	//Test #9
	//Checking the login of a user with a username that does not exist.
	//input: UserName=cu11, Password=cu1, Role = not found.
	//expected: return false.
	void testUsernameDoesntExist() throws IOException {
		// simulates typing username and password in text field.
		Username = "cu11";
		Password = "cu1";
		Role = "not found";
		boolean actualRes = loginScreenController.btnLogin(null);
		assertFalse(actualRes);
	}
	@Test
	//Test #10
	//Checking the login of a user with a wrong password.
	//input: UserName=cu1, Password=cu1test, Role = not found.
	//expected: return false.
	void testPasswordWrong() throws IOException {
		// simulates typing username and password in text field.
		Username = "cu1";
		Password = "cu1test";
		Role = "not found";
		boolean actualRes = loginScreenController.btnLogin(null);
		assertFalse(actualRes);
	}
	@Test
	//Test #10
	//Checking the login of a user with empty fields
	//input: UserName="", Password="", Role = "".
	//expected: return false.
	void testEmptyFields() throws IOException {
		// simulates typing username and password in text field.
		Username = "";
		Password = "";
		Role = "";
		boolean actualRes = loginScreenController.btnLogin(null);
		assertFalse(actualRes);
	}
	
	

}
