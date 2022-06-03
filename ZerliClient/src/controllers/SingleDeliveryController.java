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
import logic.SingleComplaint;
import logic.SingleDelivery;

public class SingleDeliveryController {
	public static  String order_id;
	public static  String customersupplydate;
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
    void ConfirmBtn(MouseEvent event) throws IOException {
    	customersupplydate= supplydate.getText();
    	order_id=orderid.getText();
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("/fxml/AprroveDeliveryScreen.fxml")));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage cartDetailsScreen = new Stage();
		cartDetailsScreen.initModality(Modality.APPLICATION_MODAL);
		cartDetailsScreen.setTitle("Approve Delivery");
		cartDetailsScreen.setScene((new Scene(root1)));
		cartDetailsScreen.show();
		cartDetailsScreen.centerOnScreen();	
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

