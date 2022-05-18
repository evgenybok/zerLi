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
        assert ordernum != null : "fx:id=\"ordernum\" was not injected: check your FXML file 'SingleOrder.fxml'.";
        assert price != null : "fx:id=\"price\" was not injected: check your FXML file 'SingleOrder.fxml'.";
        assert storename != null : "fx:id=\"storename\" was not injected: check your FXML file 'SingleOrder.fxml'.";
        assert orderdate != null : "fx:id=\"orderdate\" was not injected: check your FXML file 'SingleOrder.fxml'.";
        assert supplydate != null : "fx:id=\"supplydate\" was not injected: check your FXML file 'SingleOrder.fxml'.";
        assert supplytype != null : "fx:id=\"supplytype\" was not injected: check your FXML file 'SingleOrder.fxml'.";
        assert status != null : "fx:id=\"status\" was not injected: check your FXML file 'SingleOrder.fxml'.";
        assert refund != null : "fx:id=\"active\" was not injected: check your FXML file 'SingleOrder.fxml'.";

    }

    public void setData(SingleOrder singleOrder){

        ordernum.setText(Integer.toString(singleOrder.getOrderNumber()));
        price.setText(Double.toString(singleOrder.getPrice()));
        orderdate.setText(singleOrder.getOrderDate());
        supplydate.setText(singleOrder.getSupplyDate());
        supplytype.setText(singleOrder.getSupplyType());
        status.setText(singleOrder.getStatus());
        refund.setText(singleOrder.getRefund());
        storename.setText(singleOrder.getStoreId());



    }



}
