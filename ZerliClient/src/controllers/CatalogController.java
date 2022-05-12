package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import clientanalyze.AnalyzeMessageFromServer;
import communication.Message;
import communication.MessageType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import logic.Item;

import static controllers.IPScreenController.chat;


public class CatalogController {

    @FXML
    private ImageView CartImage;

    @FXML
    private ImageView ClockImage;

    @FXML
    private ImageView DeliveryImage;

    @FXML
    private Button Back;

    @FXML
    private Pane chosenFlowerCart;

    @FXML
    private ImageView flowerImage;

    @FXML
    private Label flowerName;

    @FXML
    private Label flowerPrice;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

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

    private void setChosenItem(Item item){
        flowerName.setText(item.getName());
        flowerPrice.setText("$"+item.getPrice());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(item.getImgSrc())));
        flowerImage.setImage(image);
    }

    @FXML
    void initialize() {
        int column=0;
        int row=1;
        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'Catalog.fxml'.";
        assert chosenFlowerCart != null : "fx:id=\"chosenFlowerCart\" was not injected: check your FXML file 'Catalog.fxml'.";
        assert flowerImage != null : "fx:id=\"flowerImage\" was not injected: check your FXML file 'Catalog.fxml'.";
        assert flowerName != null : "fx:id=\"flowerName\" was not injected: check your FXML file 'Catalog.fxml'.";
        assert flowerPrice != null : "fx:id=\"flowerPrice\" was not injected: check your FXML file 'Catalog.fxml'.";
        assert grid != null : "fx:id=\"grid\" was not injected: check your FXML file 'Catalog.fxml'.";
        assert scroll != null : "fx:id=\"scroll\" was not injected: check your FXML file 'Catalog.fxml'.";
        Image flower = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/clipart704080.png")));
        flowerImage.setImage(flower);
        Image clockImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/pngaaa.com-372753.png")));
        ClockImage.setImage(clockImage);
        Image cartImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/img_293282.png")));
        CartImage.setImage(cartImage);
        Image deliveryImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/delivery.png")));
        DeliveryImage.setImage(deliveryImage);

        ArrayList<Item> items=new ArrayList<>();
        chat.accept(new Message(MessageType.GET_ITEMS,null));
        items=(ArrayList<Item>) AnalyzeMessageFromServer.getData();

        if(items.size()>0){
            setChosenItem(items.get(0));
        }


         try{

        for(int i=0;i<items.size();i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/Item.fxml"));
            AnchorPane anchorPane=fxmlLoader.load();

            ItemController itemController = fxmlLoader.getController();
            itemController.setData(items.get(i));


            if(column==3){
                column=0;
                row++;

            }
            grid.add(anchorPane,column++,row);//(child,column,row)
            GridPane.setMargin(anchorPane,new Insets(10));

            //width

            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            grid.setMaxWidth(Region.USE_PREF_SIZE);

            //height
            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            grid.setMaxHeight(Region.USE_PREF_SIZE);

        }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }







    }


