package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.Account;
import logic.Order;

/**
 * @author Evgeny Checkout screen where the user can choose the specifics of his
 *         delivery,address,personal information and payment method.
 */
public class PaymentScreenController {
	String userID = LoginScreenController.user.getID();
	String cardNum, date, CustomerCVV, Dorder;
	double totalPrice = Double.parseDouble(CartController.totalItemsPrice.getText().substring(1));
	double totalPriceAfterDeliveryFee;
	boolean DeliveryFlag = false, DeliveryExpressFlag = false;
	public static String phoneNumber;

	@FXML
	private TextField Adress;

	@FXML
	private ComboBox<String> Area;

	@FXML
	private Button Back;

	@FXML
	private ImageView BitCoinImg;

	@FXML
	private ImageView BoxImg;

	@FXML
	private TextField CVV;

	@FXML
	private ImageView Card1Img;

	@FXML
	private ImageView Card2Img;

	@FXML
	private TextField CardDate;

	@FXML
	private TextField CardNum;

	@FXML
	private ImageView CheckoutImg;

	@FXML
	private DatePicker DateSupply;

	@FXML
	private CheckBox DeliveryBox;

	@FXML
	private ImageView DeliveryImg;

	@FXML
	private CheckBox ExpPick;

	@FXML
	private ImageView MailImg;

	@FXML
	private Button Pay;

	@FXML
	private TextField Phone;

	@FXML
	private CheckBox PickUpBox;

	@FXML
	private TextField ReciverName;

	@FXML
	private ComboBox<String> StoreName;

	@FXML
	private TextField SupplyTime;

	@FXML
	private Label amountToPay;

	@FXML
	private Label creditAmount;

	@FXML
	private Text delviryFee;

	@FXML
	private Label discount;

	@FXML
	private Label lblTotalAmount;

	@FXML
	private Label lblZerLiCredit;

	@FXML
	private Label msgLabel;

	private double refundUsed = 0;
	private double finalPriceToPay = 0;

	/**
	 * closes current screen and opens the Cart screen.
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CartScreen.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		parent.getStylesheets().add("css/transTextArea.css");
		Scene scene = new Scene(parent);
		Stage deliveryDetailsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		deliveryDetailsStage.setTitle("Shopping Cart");
		deliveryDetailsStage.setScene(scene);
		deliveryDetailsStage.show();
		deliveryDetailsStage.centerOnScreen();
	}

	/**
	 * Marks the selection of delivery and disables all others.
	 * 
	 * @param event
	 */
	@FXML
	void DeliveryBtn(MouseEvent event) {
		if (DeliveryBox.isSelected()) {
			PickUpBox.setDisable(true);
			ExpPick.setDisable(true);
			delviryFee.setText("12.00");
			changeAmount(12.00);
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

	/**
	 * Marks the selection of pickup and disables all others.
	 * 
	 * @param event
	 */
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

	/**
	 * Marks the selection of express delivery and disables all others.
	 * 
	 * @param event
	 */
	@FXML
	void expressPickBtn(MouseEvent event) {
		if (ExpPick.isSelected()) {
			DeliveryBox.setDisable(true);
			PickUpBox.setDisable(true);
			DeliveryExpressFlag = true;
			DeliveryFlag = false;
			delviryFee.setText("12.00");
			changeAmount(12.00);
			Date currDate = new Date(System.currentTimeMillis());
			Calendar supplyDateCalendar = Calendar.getInstance();
			supplyDateCalendar.setTime(currDate);
			supplyDateCalendar.add(Calendar.HOUR, 3);
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String temp = formatter1.format(supplyDateCalendar.getTime());
			String[] split = temp.split(" ");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(split[0], formatter);
			SupplyTime.setText(split[1]);
			DateSupply.setValue(localDate);

			SupplyTime.setDisable(true);
			SupplyTime.setOpacity(1.0);

			DateSupply.setDisable(true);
			DateSupply.setOpacity(1.0);
		} else {
			DeliveryFlag = false;
			DeliveryExpressFlag = false;
			delviryFee.setText("0.00");
			changeAmount(0.00);
			PickUpBox.setDisable(false);
			DeliveryBox.setDisable(false);
			SupplyTime.setDisable(false);
			DateSupply.setDisable(false);
		}
	}

	/**
	 * Gets the store name from the DB.
	 * 
	 * @param event
	 */
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

	/**
	 * Sets permanant fields of the user from the DB.
	 */
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

	/**
	 * Finishes the payment and adds the order to the DB based on the parameters
	 * entered.
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnPay(MouseEvent event) throws IOException {
		if (StoreName.getSelectionModel().isEmpty()) {
			msgLabel.setText("Store name was not chosen!");
			msgLabel.setVisible(true);
			new java.util.Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					msgLabel.setVisible(false);
				}
			}, 2000);
			return;
		}
		if (Adress.getText().isEmpty()) {
			msgLabel.setText("Address field is empty!");
			msgLabel.setVisible(true);
			new java.util.Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					msgLabel.setVisible(false);
				}
			}, 2000);
			return;
		}
		if (ReciverName.getText().isEmpty()) {
			msgLabel.setText("Receiver name field is empty!");
			msgLabel.setVisible(true);
			new java.util.Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					msgLabel.setVisible(false);
				}
			}, 2000);
			return;
		}
		if (!PickUpBox.isSelected() && !DeliveryBox.isSelected() && !ExpPick.isSelected()) {
			msgLabel.setText("Delivery method was not selected!");
			msgLabel.setVisible(true);
			new java.util.Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					msgLabel.setVisible(false);
				}
			}, 2000);
			return;
		}
		

		CheckPayDetailsNoEmpty();
		String adress, reciverName, phone, cvv;
		if (DeliveryFlag || DeliveryExpressFlag) {
			adress = Adress.getText();
		} else {
			adress = null;
		}
		cvv = CVV.getText();
		if (!cvv.equals(CustomerCVV)) {
			msgLabel.setText("CVV Number Is Wrong!");
			msgLabel.setVisible(true);
			new java.util.Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					msgLabel.setVisible(false);
				}
			}, 2000);
			return;
		} else {
			Order.orderCount++;
			String greeting = AddGreetingController.Greeting;
			String storeid = getStoreId();
			Date orderDate = new Date();
			String orderDateString = convertToDate(orderDate.toString());
			if (DateSupply.getValue() == null) {
				msgLabel.setText("Date format is wrong!");
				msgLabel.setVisible(true);
				new java.util.Timer().schedule(new java.util.TimerTask() {
					@Override
					public void run() {
						msgLabel.setVisible(false);
					}
				}, 2000);
				return;
			}
		
			String su = DateSupply.getValue().toString();
			if (!checkSupplyTime()) // Checking if supply time values are right
			{
				msgLabel.setText("Date format is wrong!");
				msgLabel.setVisible(true);
				new java.util.Timer().schedule(new java.util.TimerTask() {
					@Override
					public void run() {
						msgLabel.setVisible(false);
					}
				}, 2000);
				return;
			}
			String tu = SupplyTime.getText(); // change
			for (int i = 0; i < tu.length(); i++) {
				if (i == 2 && tu.charAt(i) != ':') {
					msgLabel.setText("Time format is wrong!");
					msgLabel.setVisible(true);
					new java.util.Timer().schedule(new java.util.TimerTask() {
						@Override
						public void run() {
							msgLabel.setVisible(false);
						}
					}, 2000);
					return;
				} else if (!(tu.charAt(i) >= '0' && tu.charAt(i) <= '9') && i != 2) {
					msgLabel.setText("Time format is wrong!");
					msgLabel.setVisible(true);
					new java.util.Timer().schedule(new java.util.TimerTask() {
						@Override
						public void run() {
							msgLabel.setVisible(false);
						}
					}, 2000);
					return;
				}
			}
			String supplyDateTime;
			if (change(su, tu) == null) {
				msgLabel.setText("Delivery takes at least 3 hours!");
				msgLabel.setVisible(true);
				new java.util.Timer().schedule(new java.util.TimerTask() {
					@Override
					public void run() {
						msgLabel.setVisible(false);
					}
				}, 2000);
				return;
			}
			supplyDateTime = change(su, tu);
			if (DeliveryFlag) {
				Date currDate = new Date(System.currentTimeMillis());
				Calendar currentCalendar = Calendar.getInstance();
				currentCalendar.setTime(currDate);
				currentCalendar.add(Calendar.HOUR_OF_DAY, 3);
				try {
					Date time3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(supplyDateTime);
					Calendar supplyDateCalendar = Calendar.getInstance();
					supplyDateCalendar.setTime(time3);

					Date x = supplyDateCalendar.getTime();
					if (x.before(currentCalendar.getTime())) {
						msgLabel.setText("Delivery takes at least 3 hours!");
						msgLabel.setVisible(true);
						new java.util.Timer().schedule(new java.util.TimerTask() {
							@Override
							public void run() {
								msgLabel.setVisible(false);
							}
						}, 2000);
						return;
					}

				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			Dorder = CheckExpressDelivery();
			String Status = "Pending";
			String supplytype = CheckWhichSupplyMethod();
			double refund = 0;
			reciverName = ReciverName.getText();
			phone = Phone.getText();
			if (phone.length() != 10) {
				msgLabel.setText("Phone number must be 10 digits!");
				msgLabel.setVisible(true);
				new java.util.Timer().schedule(new java.util.TimerTask() {
					@Override
					public void run() {
						msgLabel.setVisible(false);
					}
				}, 2000);
				return;
			}
			Order order = new Order(Order.orderCount, finalPriceToPay, greeting, storeid, orderDateString,
					supplyDateTime, Status, supplytype, userID, refund, adress, reciverName, phone, Dorder);
			try {
				chat.accept(new Message(MessageType.UPDATE_ORDERS_AMOUNT, CustomerScreenController.fullAcc));

				chat.accept(new Message(MessageType.INSERT_NEW_ORDER, order));
				if (refundUsed > 0) {
					StringBuilder sb = new StringBuilder();
					sb.append(refundUsed);
					sb.append("@");
					sb.append(CustomerScreenController.userID);
					chat.accept(new Message(MessageType.UPDATE_USED_REFUND, sb.toString()));
				}
				phoneNumber = phone;
				/* Empty the current cart in checkout */
				CatalogController.selectedProducts.clear();
				CatalogController.itemToAmount.clear();
				CustomCatalogController.customItemInCart.clear();
				CustomCatalogController.bouquetCounter = 0;

				AddGreetingController.Greeting = null;
				((Node) event.getSource()).getScene().getWindow().hide();
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("/fxml/OrderComplete.fxml")));
				Parent root1 = (Parent) fxmlLoader.load();
				root1.getStylesheets().add("css/styleNew.css");
				Stage cartDetailsScreen = new Stage();
				cartDetailsScreen.initModality(Modality.APPLICATION_MODAL);
				cartDetailsScreen.initStyle(StageStyle.UNDECORATED);
				cartDetailsScreen.setTitle("Shopping Cart");
				cartDetailsScreen.setScene((new Scene(root1)));
				cartDetailsScreen.show();
				cartDetailsScreen.centerOnScreen();
			} catch (Exception e) {
				return;
			}
			;
			return;
		}

	}

	/**
	 * Checks if the supply time entered is valid.
	 * 
	 * @return true if valid, false otherwise.
	 */
	boolean checkSupplyTime() {
		if (SupplyTime.getText().length() != 5)
			return false;
		String temp = SupplyTime.getText();
		if (!(temp.charAt(2) == ':')) {
			temp = temp.substring(0, 2) + ":" + temp.substring(3, 5);
		}
		String[] time = temp.split(":");
		int hour;
		int minutes;
		try {
			hour = Integer.parseInt(time[0]);
			minutes = Integer.parseInt(time[1]);
			if (((hour >= 0 && hour <= 23) && (minutes >= 0 && minutes < 60)) && time[1].length() == 2)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Checking for empty fields input
	 */
	void CheckPayDetailsNoEmpty() {
		msgLabel.setText("One or more fields are empty!");
		if (DeliveryFlag == true) {
			if (Adress.getText().isEmpty()) {
				msgLabel.setVisible(true);
				new java.util.Timer().schedule(new java.util.TimerTask() {
					@Override
					public void run() {
						msgLabel.setVisible(false);
					}
				}, 2000);
				return;
			}
		}
		if (ReciverName.getText().isEmpty() || Phone.getText().isEmpty() || CVV.getText().isEmpty()) {
			msgLabel.setVisible(true);
			new java.util.Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					msgLabel.setVisible(false);
				}
			}, 2000);
			msgLabel.setVisible(true);
			new java.util.Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					msgLabel.setVisible(false);
				}
			}, 2000);
			return;
		}
	}

	/**
	 * Gets store ID from the DB.
	 * 
	 * @return store ID number
	 */
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

	/**
	 * Chanages order amount based on the refund avaliable in the account.
	 * 
	 * @param deliveryFee
	 */
	void changeAmount(Double deliveryFee) {
		totalPriceAfterDeliveryFee = totalPrice + deliveryFee;
		double temp = totalPrice - CustomerScreenController.accountZerliCredit;
		double toReduce = 0;
		if (temp < 0) {
			if (CustomerScreenController.fullAcc.getOrdersAmount() == 0) {
				totalPriceAfterDeliveryFee *= 0.8;
				totalPriceAfterDeliveryFee = Math.floor(totalPriceAfterDeliveryFee);
			}
			toReduce = CustomerScreenController.accountZerliCredit + temp;
			amountToPay.setText("\u20AA" + Double.toString(totalPriceAfterDeliveryFee - toReduce) + " ("
					+ totalPriceAfterDeliveryFee + "-" + toReduce + ")");
			refundUsed = toReduce;
			finalPriceToPay = totalPriceAfterDeliveryFee - toReduce;
		} else {
			if (CustomerScreenController.fullAcc.getOrdersAmount() == 0) {
				totalPriceAfterDeliveryFee *= 0.8;
				totalPriceAfterDeliveryFee = Math.floor(totalPriceAfterDeliveryFee);
			}
			amountToPay.setText("\u20AA"
					+ Double.toString(totalPriceAfterDeliveryFee - CustomerScreenController.accountZerliCredit) + " ("
					+ totalPriceAfterDeliveryFee + "-" + CustomerScreenController.accountZerliCredit + ")");
			refundUsed = CustomerScreenController.accountZerliCredit;
			finalPriceToPay = totalPriceAfterDeliveryFee - CustomerScreenController.accountZerliCredit;
		}

	}

	/**
	 * Checking supply method.
	 * 
	 * @return String pickup / delivery.
	 */
	private String CheckWhichSupplyMethod() {
		if (DeliveryFlag) {
			return "Delivery";
		} else if (DeliveryExpressFlag) {
			return "Express";
		}
		return "PickUp";
	}

	/**
	 * Calculating the supply date
	 * 
	 * @return new supply date.
	 */
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

	/**
	 * Converts string to the given format
	 * 
	 * @param mydate received date to convert
	 * @return date with the given format
	 */
	public String convertToDate(String mydate) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:00");
		mydate = sdf.format(date);
		return mydate;
	}

	/*
	 * changes the date and time's format
	 * 
	 * @param date
	 * 
	 * @param time
	 * 
	 * @return new date and time format
	 */
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
	// for secure payment
	/**
	 * Hides the credit card number except for the final 4 digits.
	 * 
	 * @param number credit card number
	 * @return new partially hidden credit card number
	 */
	public String ChangeCreditCard(String number) {
		StringBuilder hideNumber = new StringBuilder(number);
		for (int i = 0; i < number.length() - 4; i++) {
			hideNumber.setCharAt(i, '*');
		}
		return hideNumber.toString();
	}

	/**
	 * Checking delivery type.
	 * 
	 * @return String express / regular delivery.
	 */
	private String CheckExpressDelivery() {
		if (DeliveryExpressFlag) {
			return "Express";
		}
		return "Regular";
	}

	/**
	 * data initialization
	 */
	@FXML
	void initialize() {
		msgLabel.setVisible(false);
		if (CustomerScreenController.fullAcc.getOrdersAmount() == 0) {
			discount.setVisible(true);
		} else
			discount.setVisible(false);
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
		Area.getSelectionModel().selectFirst();
		StoreName.getSelectionModel().selectFirst();
		changeAmount(0.00);
		delviryFee.setText("0.00");
		showCreditDetail();
		creditAmount.setText((Double.toString(CustomerScreenController.accountZerliCredit)));
	}
}
