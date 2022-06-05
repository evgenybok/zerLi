package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import logic.SingleSelfDelivery;

public class SingleSelfDeliveryController {

	@FXML
	private Label HandleID;

	@FXML
	private Label Status;

	@FXML
	private Label SupplyDate;

	@FXML
	private Label customerDate;

	@FXML
	private Label ordernum;

	@FXML
	void initialize() {

	}

	public void setData(SingleSelfDelivery singleSelfDelivery) {
		String s = String.valueOf(singleSelfDelivery.getOrderID());
		ordernum.setText(s);
		HandleID.setText(singleSelfDelivery.getHandle_id());
		customerDate.setText(singleSelfDelivery.getCustomerSupplyDate());
		SupplyDate.setText(singleSelfDelivery.getDeliverySupplyDate());
		Status.setText("Deliverd");
	}

}
