package controllers;

import java.io.IOException;
import java.util.Objects;
import static controllers.IPScreenController.chat;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Account;
import logic.User;

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
    void btnAdd(MouseEvent event) throws IOException {
    	String Userid = userID.getText();
    	String firstname = FirstName.getText();
    	String lastname = LastName.getText();
    	String creditnum = CreditCardNumber.getText();
    	String expire = expireDate.getText();
    	String cvv = CVV.getText();
    	String mail = Email.getText();
    	String phone = Phone.getText();
    	String username= userName.getText();
    	String password= Password.getText();
    	String role= "customer";
    	String Status="Active";
    	if(CheckIfUserNameExist(username)==false)
    	{
    		JOptionPane.showMessageDialog(null, "This username Allreday Exist", "Info",JOptionPane.INFORMATION_MESSAGE);
    	}
    	else
    	{
    		User user= new User(username,password,false,Userid,firstname,lastname,role,phone,mail);
    		Account account = new Account(Userid,creditnum,expire,cvv,0.00,Status);
    		InsertNewUser(user);
    		InsertNewAccount(account);
    		JOptionPane.showMessageDialog(null, "This user Added Succsesfully", "Info",JOptionPane.INFORMATION_MESSAGE);
    		((Node) event.getSource()).getScene().getWindow().hide();
    		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/branchManager.fxml")));
    		Scene scene = new Scene(parent);
    		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    		customerStage.setTitle("Customer");
    		customerStage.setScene(scene);
    		customerStage.show();
    		customerStage.centerOnScreen();
    		
    	}
    }
    public boolean CheckIfUserNameExist(String username)
    {
    	try {
			chat.accept(new Message(MessageType.CHECK_IF_USERNAME_EXIST,username));
			if (AnalyzeMessageFromServer.getData().equals(null)) // Incorrect username / password
				return false;
		} catch (Exception e) {
			return false;
		}
		;
		String temp = (String) AnalyzeMessageFromServer.getData();
		if(temp.equals("true"))// username exists need another username
		{
			return false;
		}
    	return true;
    }
    public void InsertNewUser(User user)
    {
    	
    	try {
			chat.accept(new Message(MessageType.INSERT_NEW_USER,user));
			if (AnalyzeMessageFromServer.getData().equals(null)) 
				return;
		} catch (Exception e) {
			return;
		};
    }
    public void InsertNewAccount(Account account)
    {

    	try {
			chat.accept(new Message(MessageType.INSERT_NEW_ACCOUNT,account));
			if (AnalyzeMessageFromServer.getData().equals(null))
				return;
		} catch (Exception e) {
			return;
		};
    	
    }

    @FXML
    void btnBack(MouseEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/branchManager.fxml")));
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Customer");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
    }
    
    @FXML
    void initialize() {

    }

}