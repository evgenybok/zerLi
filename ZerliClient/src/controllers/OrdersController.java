package controllers;

import clientanalyze.AnalyzeMessageFromServer;
import communication.Message;
import communication.MessageType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.SingleOrder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static controllers.IPScreenController.chat;

public class OrdersController {

    @FXML
    private Label lblZerLi;

    @FXML
    private Label lblStartMsg;

    @FXML
    private Text UserName;

    @FXML
    private ImageView PersonImage;

    @FXML
    private Text AccountType;

    @FXML
    private Text AccountStatus;

    @FXML
    private Button Back;

    @FXML
    private VBox OrdersLayout;

    @FXML
    private Label orderNum;

    @FXML
    private Label price;

    @FXML
    private Label shop;

    @FXML
    private Label orderDate;

    @FXML
    private Label deliveryDate;

    @FXML
    private Label supplyType;

    @FXML
    private Label status;

    @FXML
    private Label refund;

    @FXML
    void btnBack(MouseEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerScreen.fxml")));
        Scene scene = new Scene(parent);
        Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customerStage.setTitle("Customer");
        customerStage.setScene(scene);
        customerStage.show();
        customerStage.centerOnScreen();


    }


    @FXML
    void initialize() {
        assert lblZerLi != null : "fx:id=\"lblZerLi\" was not injected: check your FXML file 'ViewOrders.fxml'.";
        assert lblStartMsg != null : "fx:id=\"lblStartMsg\" was not injected: check your FXML file 'ViewOrders.fxml'.";
        assert UserName != null : "fx:id=\"UserName\" was not injected: check your FXML file 'ViewOrders.fxml'.";
        assert PersonImage != null : "fx:id=\"PersonImage\" was not injected: check your FXML file 'ViewOrders.fxml'.";
        assert AccountType != null : "fx:id=\"AccountType\" was not injected: check your FXML file 'ViewOrders.fxml'.";
        assert AccountStatus != null : "fx:id=\"AccountStatus\" was not injected: check your FXML file 'ViewOrders.fxml'.";
        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'ViewOrders.fxml'.";
        assert OrdersLayout != null : "fx:id=\"OrdersLayout\" was not injected: check your FXML file 'ViewOrders.fxml'.";
        assert orderNum != null : "fx:id=\"orderNum\" was not injected: check your FXML file 'ViewOrders.fxml'.";
        assert price != null : "fx:id=\"price\" was not injected: check your FXML file 'ViewOrders.fxml'.";
        assert shop != null : "fx:id=\"shop\" was not injected: check your FXML file 'ViewOrders.fxml'.";
        assert orderDate != null : "fx:id=\"orderDate\" was not injected: check your FXML file 'ViewOrders.fxml'.";
        assert deliveryDate != null : "fx:id=\"deliveryDate\" was not injected: check your FXML file 'ViewOrders.fxml'.";
        assert supplyType != null : "fx:id=\"supplyType\" was not injected: check your FXML file 'ViewOrders.fxml'.";
        assert status != null : "fx:id=\"status\" was not injected: check your FXML file 'ViewOrders.fxml'.";
        assert refund != null : "fx:id=\"refund\" was not injected: check your FXML file 'ViewOrders.fxml'.";

        List<SingleOrder> Orders =new ArrayList<SingleOrder>();
        chat.accept(new Message(MessageType.GET_ORDERS, null));
        Orders = (ArrayList<SingleOrder>) AnalyzeMessageFromServer.getData();

        try{

        for(int i=0;i<Orders.size();i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/SingleOrder.fxml"));
            HBox hBox=fxmlLoader.load();
            SingleOrderController singleOrderController = fxmlLoader.getController();
            singleOrderController.setData(Orders.get(i));
            OrdersLayout.getChildren().add(hBox);
        }
        }
        catch(IOException e){
            e.printStackTrace();
        }



    }

}
