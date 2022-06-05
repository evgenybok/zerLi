package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import logic.SingleSelfDelivery;

/**
 * @author Evgeny
 * Sets data for a single delivery to be used later with all deliveries.
 */
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

	/**
	 * Sets data for a single delivery
	 * @param singleSelfDelivery
	 */
	public void setData(SingleSelfDelivery singleSelfDelivery) {
		String s = String.valueOf(singleSelfDelivery.getOrderID());
		ordernum.setText(s);
		HandleID.setText(singleSelfDelivery.getHandle_id());
		customerDate.setText(singleSelfDelivery.getCustomerSupplyDate());
		SupplyDate.setText(singleSelfDelivery.getDeliverySupplyDate());
		Status.setText("Deliverd");
	}

}
