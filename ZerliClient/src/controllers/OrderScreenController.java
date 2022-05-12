package controllers;

import static controllers.IPScreenController.chat;

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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.Order;

public class OrderScreenController {

	@FXML
	private ImageView OrderScreenImage;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lblOrders;
	@FXML
	private TableView<Order> Orders;

	@FXML
	private TextField txtColor;

	@FXML
	private TextField txtOrder;

	@FXML
	private TextField txtDate;

	@FXML
	private Button Update;

	@FXML
	private Button Back;

	public TableView<Order> getOrders() {
		return Orders;
	}

	public void setOrders(TableView<Order> orders) {
		Orders = orders;
	}

	@FXML
	private TableColumn<Order, Integer> orderNum;

	@FXML
	private TableColumn<Order, Double> price;

	@FXML
	private TableColumn<Order, String> greetingCard;

	@FXML
	private TableColumn<Order, String> color;

	@FXML
	private TableColumn<Order, String> orderType;

	@FXML
	private TableColumn<Order, String> shop;

	@FXML
	private TableColumn<Order, String> date;

	@FXML
	private TableColumn<Order, String> deliveryDate;

	@FXML
	private TableColumn<Order, String> orderDate;

	@FXML
	private TableColumn<Order, String> orderStatus;

	@FXML
	private TableColumn<Order, String> supplyType;
	
    @FXML
    private TableColumn<Order, String> refund;

	public static ObservableList<Order> orders = FXCollections.observableArrayList();

	/**
	 * event double click on any order to view its details
	 * 
	 * @param
	 * @throws IOException
	 */
	@FXML
	void openOrderInfo(MouseEvent event) throws IOException {
		if (event.getClickCount() == 2) {
			ObservableList<Order> orderList;
			try {
				orderList = Orders.getSelectionModel().getSelectedItems();
				int orderNumber = orderList.get(0).getOrderNumber();
				chat.accept(new Message(MessageType.GET_SELECTED_ORDER, orderNumber));
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("/fxml/OrderDetails.fxml")));
				Parent root1 = (Parent) fxmlLoader.load();
				Stage orderDetailsStage = new Stage();
				orderDetailsStage.initModality(Modality.APPLICATION_MODAL);
				orderDetailsStage.setTitle("Order Details");
				orderDetailsStage.setScene((new Scene(root1)));
				orderDetailsStage.show();
				orderDetailsStage.centerOnScreen();
			} catch (Exception e) {
			}
			;
		}
	}

	@FXML
	void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		this.getOrders().getItems().clear();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Customer");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
	}

	@FXML
	void btnUpdate(MouseEvent event) {

		String orderNumber = txtOrder.getText();
		String Color = txtColor.getText();
		String Date = txtDate.getText();
		if (orderNumber.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter a valid order number!", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			StringBuilder str = new StringBuilder();
			str.append(orderNumber);
			str.append("#");
			str.append(Color);
			str.append("#");
			str.append(Date);
			chat.accept(new Message(MessageType.UPDATE, str.toString()));
			this.getOrders().getItems().clear();
			initialize();
			this.getOrders().refresh();
		}
	}

	@SuppressWarnings("unchecked")
	@FXML
	void initialize() {

		Image orderscreenimage = new Image(
				Objects.requireNonNull(getClass().getResourceAsStream("/images/CustomerSubScreen.jpg")));
		OrderScreenImage.setImage(orderscreenimage);
		ArrayList<Order> orderList = new ArrayList<>();
		chat.accept(new Message(MessageType.GET_ORDERS, null));
		orderList = (ArrayList<Order>) AnalyzeMessageFromServer.getData();
		for (Order s : orderList)
			orders.add(s);
		this.orderNum.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
		this.price.setCellValueFactory(new PropertyValueFactory<>("price"));
		this.greetingCard.setCellValueFactory(new PropertyValueFactory<>("greetingCard"));
		this.color.setCellValueFactory(new PropertyValueFactory<>("Color"));
		this.orderType.setCellValueFactory(new PropertyValueFactory<>("dOrder"));
		this.shop.setCellValueFactory(new PropertyValueFactory<>("shop"));
		this.deliveryDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		this.orderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		this.orderStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
		this.supplyType.setCellValueFactory(new PropertyValueFactory<>("SupplyType"));
		this.refund.setCellValueFactory(new PropertyValueFactory<>("Refund"));

		this.getOrders().setItems(orders);
        assert OrderScreenImage != null : "fx:id=\"OrderScreenImage\" was not injected: check your FXML file 'OrderScreen.fxml'.";
        assert lblOrders != null : "fx:id=\"lblOrders\" was not injected: check your FXML file 'OrderScreen.fxml'.";
        assert Orders != null : "fx:id=\"Orders\" was not injected: check your FXML file 'OrderScreen.fxml'.";
        assert orderNum != null : "fx:id=\"orderNum\" was not injected: check your FXML file 'OrderScreen.fxml'.";
        assert price != null : "fx:id=\"price\" was not injected: check your FXML file 'OrderScreen.fxml'.";
        assert greetingCard != null : "fx:id=\"greetingCard\" was not injected: check your FXML file 'OrderScreen.fxml'.";
        assert color != null : "fx:id=\"color\" was not injected: check your FXML file 'OrderScreen.fxml'.";
        assert orderType != null : "fx:id=\"orderType\" was not injected: check your FXML file 'OrderScreen.fxml'.";
        assert shop != null : "fx:id=\"shop\" was not injected: check your FXML file 'OrderScreen.fxml'.";
        assert supplyType != null : "fx:id=\"delivery\" was not injected: check your FXML file 'OrderScreen.fxml'.";
        assert deliveryDate != null : "fx:id=\"deliveryDate\" was not injected: check your FXML file 'OrderScreen.fxml'.";
        assert orderDate != null : "fx:id=\"orderDate\" was not injected: check your FXML file 'OrderScreen.fxml'.";
        assert orderStatus != null : "fx:id=\"orderStatus\" was not injected: check your FXML file 'OrderScreen.fxml'.";
        assert refund != null : "fx:id=\"refund\" was not injected: check your FXML file 'OrderScreen.fxml'.";
        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'OrderScreen.fxml'.";
        assert txtColor != null : "fx:id=\"txtColor\" was not injected: check your FXML file 'OrderScreen.fxml'.";
        assert txtOrder != null : "fx:id=\"txtOrder\" was not injected: check your FXML file 'OrderScreen.fxml'.";
        assert txtDate != null : "fx:id=\"txtDate\" was not injected: check your FXML file 'OrderScreen.fxml'.";
        assert Update != null : "fx:id=\"Update\" was not injected: check your FXML file 'OrderScreen.fxml'.";

	}
}
