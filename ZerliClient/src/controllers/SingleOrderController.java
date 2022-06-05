package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import logic.SingleOrder;

public class SingleOrderController {

	@FXML
	private Label ordernum;

	@FXML
	private Label price;

	@FXML
	private Label storename;

	@FXML
	private Label orderdate;

	@FXML
	private Label supplydate;

	@FXML
	private Label supplytype;

	@FXML
	private Label status;

	@FXML
	private Label refund;

	@FXML
	void initialize() {

	}

	public void setData(SingleOrder singleOrder) {

		ordernum.setText(Integer.toString(singleOrder.getOrderNumber()));
		price.setText(Double.toString(singleOrder.getPrice()));
		orderdate.setText(singleOrder.getOrderDate());
		supplydate.setText(singleOrder.getSupplyDate());
		supplytype.setText(singleOrder.getSupplyType());
		status.setText(singleOrder.getStatus());
		if (singleOrder.getStatus().equals("Cancelled") || singleOrder.getStatus().equals("Compensation")) {
			refund.setText(Double.toString(singleOrder.getRefund()));
		} else
			refund.setText("");
		storename.setText(singleOrder.getStoreId());
	}

}
