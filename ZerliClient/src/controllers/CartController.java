package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class CartController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lblTitle;

	@FXML
	private TableView<String> tblCart;

	@FXML
	private Button btnRemove;


	@FXML
	void clkRemove(MouseEvent event) {

	}

	@FXML
	void initialize() {
		/*
		 * Not implemented yet query to get data from user's info and put it in the
		 * tableview
		 */
		assert lblTitle != null : "fx:id=\"lblTitle\" was not injected: check your FXML file 'CartScreen.fxml'.";
		assert tblCart != null : "fx:id=\"tblCart\" was not injected: check your FXML file 'CartScreen.fxml'.";
		assert btnRemove != null : "fx:id=\"btnRemove\" was not injected: check your FXML file 'CartScreen.fxml'.";

	}
}
