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
        assert HandleID != null : "fx:id=\"HandleID\" was not injected: check your FXML file 'singleSelfDelivery.fxml'.";
        assert Status != null : "fx:id=\"Status\" was not injected: check your FXML file 'singleSelfDelivery.fxml'.";
        assert SupplyDate != null : "fx:id=\"SupplyDate\" was not injected: check your FXML file 'singleSelfDelivery.fxml'.";
        assert customerDate != null : "fx:id=\"customerDate\" was not injected: check your FXML file 'singleSelfDelivery.fxml'.";
        assert ordernum != null : "fx:id=\"ordernum\" was not injected: check your FXML file 'singleSelfDelivery.fxml'.";
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
