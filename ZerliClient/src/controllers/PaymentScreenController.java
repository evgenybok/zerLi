package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import clientanalyze.AnalyzeMessageFromServer;
import communication.Message;
import communication.MessageType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import logic.Order;

public class PaymentScreenController {
	String userID = LoginScreenController.user.getID();
	String cardNum, date, CustomerCVV, Dorder;
	double totalPrice = Double.parseDouble(CartController.totalItemsPrice.getText().substring(1));
	double totalPriceAfterDeliveryFee;
	boolean DeliveryFlag = false, DeliveryExpressFlag = false;
	@FXML
	private ResourceBundle resources;

	@FXML
	private DatePicker DateSupply;
	@FXML
	private TextField SupplyTime;
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
	private DatePicker Dates;

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
	private TextField totalPriceLabel;
	@FXML
	private CheckBox ExpPick;

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
		if (DeliveryBox.isSelected()) {
			PickUpBox.setDisable(true);
			ExpPick.setDisable(true);
			delviryFee.setText("12.90");
			changeAmount(12.90);
			DeliveryFlag = true;
			DeliveryExpressFlag = false;
		} else {
			DeliveryFlag = false;
			DeliveryExpressFlag = false;
			ExpPick.setDisable(false);
			delviryFee.setText("0.00");
			changeAmount(0.00);
			PickUpBox.setDisable(false);
		}
	}

	@FXML
	void PickBtn(MouseEvent event) {

		if (PickUpBox.isSelected()) {
			DeliveryBox.setDisable(true);
			ExpPick.setDisable(true);
			Adress.setDisable(true);
			DeliveryFlag = false;
			DeliveryExpressFlag = false;
			delviryFee.setText("0.00");
			changeAmount(0.00);
		} else {
			DeliveryFlag = false;
			DeliveryExpressFlag = false;
			DeliveryBox.setDisable(false);
			ExpPick.setDisable(false);
			Adress.setDisable(false);
			delviryFee.setText("0.00");
			changeAmount(0.00);
		}
	}

	@FXML
	void expressPickBtn(MouseEvent event) {
		if (ExpPick.isSelected()) {
			DeliveryBox.setDisable(true);
			PickUpBox.setDisable(true);
			DeliveryExpressFlag = true;
			DeliveryFlag = true;
			delviryFee.setText("12.90");
			changeAmount(12.90);
		} else {
			DeliveryFlag = false;
			DeliveryExpressFlag = false;
			delviryFee.setText("0.00");
			changeAmount(0.00);
			PickUpBox.setDisable(false);
			DeliveryBox.setDisable(false);
		}
	}

	@SuppressWarnings("unchecked")
	@FXML
	void GetStoreName(MouseEvent event) {
		ArrayList<String> storeName = new ArrayList<>();
		String area = Area.getValue();
		try {
			chat.accept(new Message(MessageType.GET_STORE_NAME, area));
			if (AnalyzeMessageFromServer.getData().equals(null)) // Incorrect username / password
				return;

		} catch (Exception e) {
			return;
		}
		;
		storeName = (ArrayList<String>) AnalyzeMessageFromServer.getData();
		StoreName.setItems(FXCollections.observableArrayList(storeName));
	}

	@SuppressWarnings("unchecked")
	public void showCreditDetail() {
		ArrayList<Account> accountCreditDetails = new ArrayList<>();
		try {
			chat.accept(new Message(MessageType.GET_ACCOUNT_DETAILS, userID));
			if (AnalyzeMessageFromServer.getData().equals(null))
				return;

		} catch (Exception e) {
			return;
		}
		;
		accountCreditDetails = (ArrayList<Account>) AnalyzeMessageFromServer.getData();
		String changedNumber = ChangeCreditCard(accountCreditDetails.get(0).getCreditCardNumber());
		String expiryDate = accountCreditDetails.get(0).getExpiryDate();
		CardNum.setText(changedNumber);
		CardDate.setText(expiryDate);
		cardNum = accountCreditDetails.get(0).getCreditCardNumber();
		date = accountCreditDetails.get(0).getExpiryDate();
		CustomerCVV = accountCreditDetails.get(0).getCVV();
	}

	@FXML
	void initialize() {

		Image checkoutImg = new Image(
				Objects.requireNonNull(getClass().getResourceAsStream("/images/icons8-card-payment-50.png")));
		CheckoutImg.setImage(checkoutImg);
		Image boxImg = new Image(
				Objects.requireNonNull(getClass().getResourceAsStream("/images/icons8-packing-50.png")));
		BoxImg.setImage(boxImg);
		Image mailImg = new Image(
				Objects.requireNonNull(getClass().getResourceAsStream("/images/icons8-letterbox-50 (1).png")));
		MailImg.setImage(mailImg);
		Image car1Img = new Image(
				Objects.requireNonNull(getClass().getResourceAsStream("/images/icons8-mastercard-48.png")));
		Card1Img.setImage(car1Img);
		Image card2Img = new Image(
				Objects.requireNonNull(getClass().getResourceAsStream("/images/icons8-visa-48.png")));
		Card2Img.setImage(card2Img);
		Image bitcoinImg = new Image(
				Objects.requireNonNull(getClass().getResourceAsStream("/images/icons8-bitcoin-48.png")));
		BitCoinImg.setImage(bitcoinImg);
		Image deliveryImg = new Image(
				Objects.requireNonNull(getClass().getResourceAsStream("/images/icons8-deliver-food-50 (1).png")));
		DeliveryImg.setImage(deliveryImg);
		Area.setItems(FXCollections.observableArrayList("North", "East", "Center", "South"));
		changeAmount(0.00);
		delviryFee.setText("0.00");
		showCreditDetail();
	}

	@FXML
	void btnPay(MouseEvent event) throws IOException {

		CheckPayDetailsNoEmpty();
		String adress, reciverName, phone, cvv;
		if (DeliveryFlag) {
			adress = Adress.getText();
		} else {
			adress = null;
		}
		cvv = CVV.getText();
		if (!cvv.equals(CustomerCVV)) {
			JOptionPane.showMessageDialog(null, "CVV Number Is Wrong!!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			Order.orderCount++;
			String greeting = AddGreetingController.Greeting;
			String storeid = getStoreId();
			Date orderDate = new Date();
			String orderDateString = convertToDate(orderDate.toString());
			String su = DateSupply.getValue().toString();
			if (!checkSupplyTime()) // Checking if supply time values are right
			{
				JOptionPane.showMessageDialog(null, "Date format is wrong!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String tu = SupplyTime.getText(); // change
			String supplyDateTime = change(su, tu);
			if(supplyDateTime==null)
			{
				JOptionPane.showMessageDialog(null, "Date has already passed!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Dorder = CheckExpressDelivery();
			String Status = "WaitForApprove";
			String supplytype = CheckWhichSupplyMethod();
			double refund = 0;
			reciverName = ReciverName.getText();
			phone = Phone.getText();
			Order order = new Order(Order.orderCount, totalPriceAfterDeliveryFee, greeting, storeid, orderDateString,
					supplyDateTime, Status, supplytype, userID, refund, adress, reciverName, phone, Dorder);

			try {
				chat.accept(new Message(MessageType.INSERT_NEW_ORDER, order));
				
				/* Empty the current cart in checkout */
				CatalogController.selectedProducts.clear();
				CatalogController.itemToAmount.clear();
				CustomCatalogController.customItemInCart.clear();
				CustomCatalogController.bouquetCounter=0;

				/*
				 * CustomCatalogController.itemToAmount.clear();
				 * CustomCatalogController.selectedProducts.clear();
				 */
				((Node) event.getSource()).getScene().getWindow().hide();
				Parent parent = FXMLLoader
						.load(Objects.requireNonNull(getClass().getResource("/fxml/OrderComplete.fxml")));
				Scene scene = new Scene(parent);
				Stage deliveryDetailsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				deliveryDetailsStage.setTitle("Shopping Cart");
				deliveryDetailsStage.setScene(scene);
				deliveryDetailsStage.show();
				deliveryDetailsStage.centerOnScreen();

			} catch (Exception e) {
				return;
			}
			;
			return;
		}

	}

	boolean checkSupplyTime() {
		if (SupplyTime.getText().length() != 5)
			return false;
		String temp=SupplyTime.getText();
		if(!(temp.charAt(2)==':'))
		{
			temp=temp.substring(0,2) + ":" + temp.substring(3,5);
		}
		String[] time = temp.split(":");
		int hour = Integer.parseInt(time[0]);
		int minutes = Integer.parseInt(time[1]);
		if (((hour > 0 && hour < 24) && (minutes > 0 && minutes < 60)) && time[1].length() == 2)
			return true;
		else
			return false;
	}

	void CheckPayDetailsNoEmpty() {

		if (DeliveryFlag == true) {
			if (Adress.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "One or more fields are empty!", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		if (ReciverName.getText().isEmpty() || Phone.getText().isEmpty() || CVV.getText().isEmpty()) {
			// need to put msg that Reciver Name is empty
			JOptionPane.showMessageDialog(null, "One or more fields are empty!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		///////////////////// need to check the comobox and date in same way*/
	}

	private String getStoreId() {
		String storeName = StoreName.getValue();
		try {
			chat.accept(new Message(MessageType.GET_STORE_ID, storeName));
			if (AnalyzeMessageFromServer.getData().equals(null)) // Incorrect username / password
				return null;

		} catch (Exception e) {
			return null;
		}
		;
		String storeid = (String) AnalyzeMessageFromServer.getData();
		return storeid;

	}

	void changeAmount(Double deliveryFee) {
		totalPriceAfterDeliveryFee = totalPrice + deliveryFee;
		totalPriceLabel.setText(Double.toString(totalPriceAfterDeliveryFee));
	}

	private String CheckWhichSupplyMethod() {
		if (DeliveryFlag) {
			return "Delivery";
		}
		return "PickUp";
	}

	//????
	public Date calSupplyDate() {
		LocalDate localDate = DateSupply.getValue();
		LocalDate today = LocalDate.now();
		if (localDate.isBefore(today))
			return null;
		Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
		Date date = Date.from(instant);
		int hour = Integer.parseInt(SupplyTime.getText().split(":")[0]);
		int min = Integer.parseInt(SupplyTime.getText().split(":")[1]);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, min);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	public String convertToDate(String mydate) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:00");
		mydate = sdf.format(date);
		return mydate;
	}

	public String change(String date, String time) {
		LocalDate localDate = DateSupply.getValue();
		LocalDate today = LocalDate.now();
		if (localDate.isBefore(today))
			return null;
		String ans = date.replace('.', '-');
		ans = ans + " " + time + ":00";
		return ans;
	}

	// Here we only show to the customer *************5454 we use this method only
	// for secure
	public String ChangeCreditCard(String number) {
		StringBuilder hideNumber = new StringBuilder(number);
		for (int i = 0; i < number.length() - 4; i++) {
			hideNumber.setCharAt(i, '*');
		}
		return hideNumber.toString();
	}

	private String CheckExpressDelivery() {
		if (DeliveryExpressFlag) {
			return "Express";
		}
		return "Regular";
	}
}
