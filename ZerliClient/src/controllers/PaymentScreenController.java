package controllers;

import static controllers.IPScreenController.chat;

import java.awt.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import clientanalyze.AnalyzeMessageFromServer;
import communication.Message;
import communication.MessageType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Account;
import logic.User;

public class PaymentScreenController {
		String userID= LoginScreenController.user.getID();
		String cardNum,date,CustomerCVV;
		boolean DeliveryFlag=false;
	 	@FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private TextField Adress;

	    @FXML
	    private ComboBox<String> Area;

	    @FXML
	    private Button Back;

	    @FXML
	    private TextField CVV;

	    @FXML
	    private TextField CardDate;

	    @FXML
	    private TextField CardNum;

	    @FXML
	    private DatePicker Date;

	    @FXML
	    private Button Pay;

	    @FXML
	    private TextField Phone;

	    @FXML
	    private TextField ReciverName;

	    @FXML
	    private ComboBox<String> StoreName;

		@FXML
		private CheckBox DeliveryBox;

		@FXML
		private CheckBox PickUpBox;
		@FXML
		private ImageView BoxImg;

		@FXML
		private ImageView DeliveryImg;

		@FXML
		private ImageView MailImg;

		@FXML
		private ImageView Card1Img;

		@FXML
		private ImageView Card2Img;

		@FXML
		private ImageView BitCoinImg;
		@FXML
		private ImageView CheckoutImg;
		@FXML
	    private Text delviryFee;

	@FXML
    void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CartScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage deliveryDetailsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		deliveryDetailsStage.setTitle("Shopping Cart");
		deliveryDetailsStage.setScene(scene);
		deliveryDetailsStage.show();
		deliveryDetailsStage.centerOnScreen();
    }

	@FXML
	void DeliveryBtn(MouseEvent event) {
		if(DeliveryBox.isSelected())
		{
			PickUpBox.setDisable(true);
			delviryFee.setText("12.90");
			DeliveryFlag=true;
		}
		else
		{
			DeliveryFlag=false;
			delviryFee.setText("0.00");
			PickUpBox.setDisable(false);	
		}
			
	}
	@FXML
	void PickBtn(MouseEvent event) {

		if(PickUpBox.isSelected()) {
			DeliveryBox.setDisable(true);
			Adress.setDisable(true);
			DeliveryFlag=false;
			delviryFee.setText("0.00");
		}
		else
		{
			DeliveryFlag=false;
			DeliveryBox.setDisable(false);
			Adress.setDisable(false);
			delviryFee.setText("0.00");
		}
	}
    @FXML
    void GetStoreName(MouseEvent event) {
    	ArrayList<String> storeName=new ArrayList<>();
    	String area = Area.getValue();
    	try {
			chat.accept(new Message(MessageType.GET_STORE_NAME, area));
			if (AnalyzeMessageFromServer.getData().equals(null)) // Incorrect username / password
				return;
			
		} catch (Exception e) {
			return;
		}
		;
		storeName=(ArrayList<String>)AnalyzeMessageFromServer.getData();
		StoreName.setItems(FXCollections.observableArrayList(storeName));
    }
    
    public void showCreditDetail()
    {
    	ArrayList<Account> accountCreditDetails=new ArrayList<>();
    	try {
			chat.accept(new Message(MessageType.GET_CREDIT_DETAILS,userID));
			if (AnalyzeMessageFromServer.getData().equals(null)) 
				return;
			
		} catch (Exception e) {
			return;
		}
		;
		accountCreditDetails=(ArrayList<Account>)AnalyzeMessageFromServer.getData();
		//// this line for hide the number and show them on screen
		String changedNumber = ChangeCreditCard(accountCreditDetails.get(0).getCreditCardNumber());
		String expiryDate=accountCreditDetails.get(0).getExpiryDate();
		CardNum.setText(changedNumber);
		CardDate.setText(expiryDate);
		//this line for save the account details for DB and pay
		cardNum = accountCreditDetails.get(0).getCreditCardNumber();
		date = accountCreditDetails.get(0).getExpiryDate();
		CustomerCVV=accountCreditDetails.get(0).getCVV();
		
	}
    //Here we only show to the customer *************5454 we use this method only for secure
    public String ChangeCreditCard(String number)
    {
    	StringBuilder hideNumber = new StringBuilder(number);
    	for(int i =0;i<number.length()-4;i++)
    	{
    		hideNumber.setCharAt(i, '*');
    	}
    	return hideNumber.toString();
    }
    @FXML
    void initialize() {

		Image checkoutImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icons8-card-payment-50.png")));
		CheckoutImg.setImage(checkoutImg);
		Image boxImg  = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icons8-packing-50.png")));
		BoxImg.setImage(boxImg);
		Image mailImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icons8-letterbox-50 (1).png")));
		MailImg.setImage(mailImg);
		Image car1Img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icons8-mastercard-48.png")));
		Card1Img.setImage(car1Img);
		Image card2Img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icons8-visa-48.png")));
		Card2Img.setImage(card2Img);
		Image bitcoinImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icons8-bitcoin-48.png")));
		BitCoinImg.setImage(bitcoinImg);
		Image deliveryImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icons8-deliver-food-50 (1).png")));
		DeliveryImg.setImage(deliveryImg);
		Area.setItems(FXCollections.observableArrayList("North","East","Center","South"));
		delviryFee.setText("0.00");
		showCreditDetail();
    }
    @FXML
    void btnPay(MouseEvent event) {
    	//zaric methoda sbodeket skol hasdaot melim em lo meziga  msg matim
    	CheckPayDetailsNoEmpty();
    	String adress,reciverName,phone,cvv;
    	if(DeliveryFlag)
    	{
    		adress = Adress.getText();
    	}
    	reciverName = ReciverName.getText();
    	phone = Phone.getText();
    	cvv= CVV.getText();
    	if(!cvv.equals(CustomerCVV))
    	{
    		JOptionPane.showMessageDialog(null, "CVV Number Is Worng!!", "Error", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	else// here every thing ok we can move to  save the details in DB
    	{
    		//here we need query to save the order;
    		// and show him the next screen;
    		// BLa blAf
    	}
    	
    }
    void CheckPayDetailsNoEmpty()
	{
		if(DeliveryFlag=true)
		{
			if(Adress.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "One or more fields are empty!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		if(ReciverName.getText().isEmpty()||Phone.getText().isEmpty()||CVV.getText().isEmpty())
		{
			//need to put msg that Reciver Name is empty
			JOptionPane.showMessageDialog(null, "One or more fields are empty!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		 /////////////////////need to check the comobox and date in same way*/
	}
    
}
