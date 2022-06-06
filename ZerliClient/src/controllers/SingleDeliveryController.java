package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.SingleDelivery;

/**
 * @author Evgeny
 * Single delivery used when delivery user views orders data and confirm delivery
 */
public class SingleDeliveryController {
	public static String order_id;
	public static String customersupplydate;

	@FXML
	private Button Confirm;

	@FXML
	private Label address;

	@FXML
	private Label dOrder;

	@FXML
	private Label orderid;

	@FXML
	private Label reciverName;

	@FXML
	private Label reciverPhone;

	@FXML
	private Label storeName;

	@FXML
	private Label supplydate;

	/**
	 * Confirm the delivery of the order
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void ConfirmBtn(MouseEvent event) throws IOException {
		customersupplydate = supplydate.getText();
		order_id = orderid.getText();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("/fxml/AprroveDeliveryScreen.fxml")));
		Parent root1 = (Parent) fxmlLoader.load();
		root1.getStylesheets().add("/css/styleNew.css");
		Stage cartDetailsScreen = new Stage();
		cartDetailsScreen.initModality(Modality.APPLICATION_MODAL);
		cartDetailsScreen.setTitle("Approve Delivery");
		cartDetailsScreen.setScene((new Scene(root1)));
		cartDetailsScreen.show();
		cartDetailsScreen.centerOnScreen();
	}

	/**
	 * Sets data for the current shown order
	 * @param singleDelivery
	 */
	public void setData(SingleDelivery singleDelivery) {
		String res = String.valueOf(singleDelivery.getOrderid());
		orderid.setText(res);
		storeName.setText(singleDelivery.getStorename());
		supplydate.setText(singleDelivery.getSupplydate());
		reciverName.setText(singleDelivery.getRecivername());
		reciverPhone.setText(singleDelivery.getReciverphone());
		dOrder.setText(singleDelivery.getDorder());
		address.setText(singleDelivery.getAddress());
	}

}
