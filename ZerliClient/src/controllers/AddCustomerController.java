package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.util.Objects;

import javax.swing.JOptionPane;

import clientanalyze.AnalyzeMessageFromServer;
import communication.Message;
import communication.MessageType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Account;
import logic.User;

/**
 * @author Evgeny
 * The branch manager can register a new customer in this screen in the DB.
 */
public class AddCustomerController {

	@FXML
	private Button Add;

	@FXML
	private Button Back;

	@FXML
	private TextField CVV;

	@FXML
	private TextField CreditCardNumber;

	@FXML
	private TextField Email;

	@FXML
	private TextField FirstName;

	@FXML
	private TextField LastName;

	@FXML
	private TextField Password;

	@FXML
	private TextField Phone;

	@FXML
	private TextField userName;

	@FXML
	private Text accountType;

	@FXML
	private TextField expireDate;

	@FXML
	private TextField userID;

	@FXML
	private Text userTitle;

    @FXML
    private ImageView avatarImg;

	/**
     * Registers a new customer with the filled parameters in the DB.
     * @param event
     * @throws IOException
     */
	@FXML
	void btnAdd(MouseEvent event) throws IOException {
		String Userid = userID.getText();
		String firstname = FirstName.getText();
		String lastname = LastName.getText();
		String creditnum = CreditCardNumber.getText();
		String expire = expireDate.getText();
		String cvv = CVV.getText();
		String mail = Email.getText();
		String phone = Phone.getText();
		String username = userName.getText();
		String password = Password.getText();
		String role = "customer";
		String Status = "Active";
		if(Userid=="" || firstname=="" || lastname=="" || creditnum=="" || expire==""|| cvv=="" || mail==""
				|| phone=="" || username=="" || password=="" || role=="" || Status=="") {
			JOptionPane.showMessageDialog(null, "One or more of the fields are empty!!", "Info",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(!ContainsChar(Userid)	|| !ContainsChar(creditnum)	|| !ContainsChar(cvv)|| !ContainsChar(phone)) {
			JOptionPane.showMessageDialog(null, "One or more of the numeric fields are incorrect!", "Info",
					JOptionPane.INFORMATION_MESSAGE);
		}
		if (CheckIfUserNameExist(username) == false) {
			JOptionPane.showMessageDialog(null, "This username Allreday Exist", "Info",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		} else {
			User user = new User(username, password, false, Userid, firstname, lastname, role, phone, mail);
			Account account = new Account(Userid, creditnum, expire, cvv, 0.00, Status,0);
			InsertNewUser(user);
			InsertNewAccount(account);
			JOptionPane.showMessageDialog(null, "This user Added Succsesfully", "Info",
					JOptionPane.INFORMATION_MESSAGE);
			((Node) event.getSource()).getScene().getWindow().hide();
			Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/branchManager.fxml")));
			parent.getStylesheets().add("css/styleNew.css");
			Scene scene = new Scene(parent);
			Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			customerStage.setTitle("Customer");
			customerStage.setScene(scene);
			customerStage.show();
			customerStage.centerOnScreen();
		}
	}
		/**
		 * Checks if current string contains anything that is not a number.
		 * @param str Given string
		 * @return true if only numbers, false otherwise.
		 */
		private boolean ContainsChar(String str) {
			for(int i = 0; i<str.length();i++) {
				if(!(str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
					return false;
				}
			}
			return true;
		}
	
		/**
	     * Method that checks if there is another account with the entered username in the DB.
	     * @param username username
	     * @return true if exists, false otherwise.
	     */
	public boolean CheckIfUserNameExist(String username) {
		try {
			chat.accept(new Message(MessageType.CHECK_IF_USERNAME_EXIST, username));
			if (AnalyzeMessageFromServer.getData().equals(null)) // Incorrect username / password
				return false;
		} catch (Exception e) {
			return false;
		}
		;
		String temp = (String) AnalyzeMessageFromServer.getData();
		if (temp.equals("true"))// username exists need another username
		{
			return false;
		}
		return true;
	}

	/**
     * Inserts the newly created account to table Users in the DB.
     * @param user new user to be inserted to the DB
     */
	public void InsertNewUser(User user) {

		try {
			chat.accept(new Message(MessageType.INSERT_NEW_USER, user));
			if (AnalyzeMessageFromServer.getData().equals(null))
				return;
		} catch (Exception e) {
			return;
		}
		;
	}

	/**
     * Inserts the newly created account to table Accoun_details in the DB.
     * @param account - logic Account, contains account information.
     */
	public void InsertNewAccount(Account account) {
		try {
			chat.accept(new Message(MessageType.INSERT_NEW_ACCOUNT, account));
			if (AnalyzeMessageFromServer.getData().equals(null))
				return;
		} catch (Exception e) {
			return;
		}
		;
	}

	/**
     * closes current screen and opens the branch manager login screen.
     * @param event
     * @throws IOException
     */
	@FXML
	void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/branchManager.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage managerScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
		managerScreen.setTitle("Branch Manager Screen");
		managerScreen.setScene(scene);
		managerScreen.show();
		managerScreen.centerOnScreen();
	}

	/**
	 * Initializes data
	 */
	@FXML
	void initialize() {
		userTitle.setText(LoginScreenController.user.getUsername());
		Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
		avatarImg.setImage(personImage);
	}

}