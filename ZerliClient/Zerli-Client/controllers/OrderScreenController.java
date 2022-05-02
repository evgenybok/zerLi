package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class OrderScreenController {

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
	private TableColumn<Order, String> deliveryDate;

	@FXML
	private TableColumn<Order, String> orderDate;

	public static ObservableList<Order> orders = FXCollections.observableArrayList();

	@FXML
	void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		this.getOrders().getItems().clear();
		chat.accept(new Message(MessageType.LOGOUT, null));

		Parent parent = FXMLLoader
				.load(Objects.requireNonNull(getClass().getResource("/controllers/CustomerScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage LoginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		LoginStage.setTitle("Login Screen");
		LoginStage.setScene(scene);
		LoginStage.show();

	}

	@FXML
	void btnUpdate(MouseEvent event) {
		String orderNumber = txtOrder.getText();
		String Color = txtColor.getText();
		String Date = txtDate.getText();
		if (orderNumber.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter a valid order number!", "Error", JOptionPane.ERROR_MESSAGE);
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

	@FXML
	void initialize() {

		chat.accept(new Message(MessageType.GET_ORDERS, null));
		String data = AnalyzeMessageFromServer.getData();
		while (!data.equals("&")) {
			String[] orderdata = data.split("#", 8);
			String[] newdata = orderdata[7].split("@", 2);
			// Remove null values
			if (orderdata[2].equals("null"))
				orderdata[2] = " ";
			if (orderdata[3].equals("null"))
				orderdata[3] = " ";
			if (orderdata[4].equals("null"))
				orderdata[4] = " ";
			data = newdata[1];
			int orderNumber = Integer.parseInt(orderdata[0]);
			double price = Double.parseDouble(orderdata[1]);
			orders.add(new Order(orderNumber, price, orderdata[2], orderdata[3], orderdata[4], orderdata[5],
					orderdata[6], newdata[0]));
		}

		this.orderNum.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
		this.price.setCellValueFactory(new PropertyValueFactory<>("price"));
		this.greetingCard.setCellValueFactory(new PropertyValueFactory<>("greetingCard"));
		this.color.setCellValueFactory(new PropertyValueFactory<>("Color"));
		this.orderType.setCellValueFactory(new PropertyValueFactory<>("dOrder"));
		this.shop.setCellValueFactory(new PropertyValueFactory<>("shop"));
		this.deliveryDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		this.orderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		this.getOrders().setItems(orders);
		assert lblOrders != null : "fx:id=\"lblOrders\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert Orders != null : "fx:id=\"Orders\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert orderNum != null : "fx:id=\"orderNum\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert price != null : "fx:id=\"price\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert greetingCard != null
				: "fx:id=\"greetingCard\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert color != null : "fx:id=\"color\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert orderType != null : "fx:id=\"orderType\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert shop != null : "fx:id=\"shop\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert deliveryDate != null
				: "fx:id=\"deliveryDate\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert orderDate != null : "fx:id=\"orderDate\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert Update != null : "fx:id=\"Update\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert txtColor != null : "fx:id=\"txtColor\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert txtOrder != null : "fx:id=\"txtOrder\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert txtDate != null : "fx:id=\"txtDate\" was not injected: check your FXML file 'OrderScreen.fxml'.";

	}
}
