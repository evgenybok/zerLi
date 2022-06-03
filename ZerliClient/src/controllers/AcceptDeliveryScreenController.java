package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import clientanalyze.AnalyzeMessageFromServer;
import communication.Message;
import communication.MessageType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.SingleDelivery;
import logic.SingleOrder;

public class AcceptDeliveryScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Back;

    @FXML
    private VBox DeliveryLayout;
    
    @FXML
    private Text accountType;

    @FXML
    private TextField orderIDtext;

    @FXML
    private TextField storeText;

    @FXML
    private Text userName;
    @FXML
    public void SearchByOrderID(MouseEvent event) {
    	if(orderIDtext.getText().isEmpty())
    	{
    		DeliveryLayout.getChildren().clear();
    		initialize();
    	}
    	else
    	{
    		DeliveryLayout.getChildren().clear();
    		try {
    			chat.accept(new Message(MessageType.GET_SINGLE_DELIVERY_BY_ORDER_ID,orderIDtext.getText()));
    			if (AnalyzeMessageFromServer.getData().equals(null)) 
    				return;

    		} catch (Exception e) {
    			return;
    		};
    		
    		ArrayList<SingleDelivery> list = (ArrayList<SingleDelivery>)AnalyzeMessageFromServer.getData();
    		try {
    			for (int i = 0; i < list.size(); i++) {
    				FXMLLoader fxmlLoader = new FXMLLoader();
    				fxmlLoader.setLocation(getClass().getResource("/fxml/SingleDelivery.fxml"));
    				HBox hBox = fxmlLoader.load();
    				SingleDeliveryController singleDeliveryController = fxmlLoader.getController();
    				singleDeliveryController.setData(list.get(i));
    				DeliveryLayout.getChildren().add(hBox);

    			}
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }
    @FXML
    void SearchByStoreID(MouseEvent event) {
    	if(storeText.getText().isEmpty())
    	{
    		DeliveryLayout.getChildren().clear();
    		initialize();
    	}
    	else
    	{
    		DeliveryLayout.getChildren().clear();
    		try {
    			chat.accept(new Message(MessageType.GET_SINGLE_DELIVERY_BY_STORE_ID,storeText.getText()));
    			if (AnalyzeMessageFromServer.getData().equals(null)) 
    				return;

    		} catch (Exception e) {
    			return;
    		};
    		
    		ArrayList<SingleDelivery> list = (ArrayList<SingleDelivery>)AnalyzeMessageFromServer.getData();
    		try {
    			for (int i = 0; i < list.size(); i++) {
    				FXMLLoader fxmlLoader = new FXMLLoader();
    				fxmlLoader.setLocation(getClass().getResource("/fxml/SingleDelivery.fxml"));
    				HBox hBox = fxmlLoader.load();
    				SingleDeliveryController singleDeliveryController = fxmlLoader.getController();
    				singleDeliveryController.setData(list.get(i));
    				DeliveryLayout.getChildren().add(hBox);

    			}
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	
    }

    @FXML
    void btnBack(MouseEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/DeliveryLoginScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Delivery Screen");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
    }

    @FXML
    void initialize() {
        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'DeliveryScreen.fxml'.";
        assert DeliveryLayout != null : "fx:id=\"DeliveryLayout\" was not injected: check your FXML file 'DeliveryScreen.fxml'.";
        assert accountType != null : "fx:id=\"accountType\" was not injected: check your FXML file 'DeliveryScreen.fxml'.";
        assert orderIDtext != null : "fx:id=\"orderIDtext\" was not injected: check your FXML file 'DeliveryScreen.fxml'.";
        assert storeText != null : "fx:id=\"storeText\" was not injected: check your FXML file 'DeliveryScreen.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'DeliveryScreen.fxml'.";
        userName.setText(LoginScreenController.user.getFirstName());
        InsertToTable();
    }
    public void InsertToTable()
    {
    	ArrayList<SingleDelivery> list= new ArrayList<>();
    	chat.accept(new Message(MessageType.GET_SINGLE_DELIVERY,null));
    	list = (ArrayList<SingleDelivery>) AnalyzeMessageFromServer.getData();
		try {
			for (int i = 0; i < list.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/SingleDelivery.fxml"));
				HBox hBox = fxmlLoader.load();
				SingleDeliveryController singleDeliveryController = fxmlLoader.getController();
				singleDeliveryController.setData(list.get(i));
				DeliveryLayout.getChildren().add(hBox);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    

}
