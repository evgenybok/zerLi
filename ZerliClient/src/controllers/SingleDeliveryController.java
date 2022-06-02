package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import logic.SingleComplaint;
import logic.SingleDelivery;

public class SingleDeliveryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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

    @FXML
    void ConfirmBtn(MouseEvent event) {
    	
    }
    public void setData(SingleDelivery singleDelivery)
    {
    	String res= String.valueOf(singleDelivery.getOrderid());
    	orderid.setText(res);
    	storeName.setText(singleDelivery.getStorename());
    	supplydate.setText(singleDelivery.getSupplydate());
    	reciverName.setText(singleDelivery.getRecivername());
    	reciverPhone.setText(singleDelivery.getReciverphone());
    	dOrder.setText(singleDelivery.getDorder());
    	address.setText(singleDelivery.getAddress());
    }
    @FXML
    void initialize() {
        assert Confirm != null : "fx:id=\"Confirm\" was not injected: check your FXML file 'singleDelivery.fxml'.";
        assert address != null : "fx:id=\"address\" was not injected: check your FXML file 'singleDelivery.fxml'.";
        assert dOrder != null : "fx:id=\"dOrder\" was not injected: check your FXML file 'singleDelivery.fxml'.";
        assert orderid != null : "fx:id=\"orderid\" was not injected: check your FXML file 'singleDelivery.fxml'.";
        assert reciverName != null : "fx:id=\"reciverName\" was not injected: check your FXML file 'singleDelivery.fxml'.";
        assert reciverPhone != null : "fx:id=\"reciverPhone\" was not injected: check your FXML file 'singleDelivery.fxml'.";
        assert storeName != null : "fx:id=\"storeName\" was not injected: check your FXML file 'singleDelivery.fxml'.";
        assert supplydate != null : "fx:id=\"supplydate\" was not injected: check your FXML file 'singleDelivery.fxml'.";

    }

}

