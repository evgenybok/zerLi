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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.SingleDelivery;

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
    private ImageView avatarImg;

	@FXML
	public void SearchByOrderID(MouseEvent event) {
		if (orderIDtext.getText().isEmpty()) {
			DeliveryLayout.getChildren().clear();
			initialize();
		} else {
			DeliveryLayout.getChildren().clear();
			try {
				chat.accept(new Message(MessageType.GET_SINGLE_DELIVERY_BY_ORDER_ID, orderIDtext.getText()));
				if (AnalyzeMessageFromServer.getData().equals(null))
					return;

			} catch (Exception e) {
				return;
			}
			;

			ArrayList<SingleDelivery> list = (ArrayList<SingleDelivery>) AnalyzeMessageFromServer.getData();
			try {
				for (int i = 0; i < list.size(); i++) {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/fxml/SingleDelivery.fxml"));
					HBox hBox = fxmlLoader.load();
					SingleDeliveryController singleDeliveryController = fxmlLoader.getController();
					singleDeliveryController.setData(list.get(i));
					DeliveryLayout.getChildren().add(hBox);

				}
			} catch (Exception e) {
				
			}
		}
	}

	@FXML
	void SearchByStoreID(MouseEvent event) {
		if (storeText.getText().isEmpty()) {
			DeliveryLayout.getChildren().clear();
			initialize();
		} else {
			DeliveryLayout.getChildren().clear();
			try {
				chat.accept(new Message(MessageType.GET_SINGLE_DELIVERY_BY_STORE_ID, storeText.getText()));
				if (AnalyzeMessageFromServer.getData().equals(null))
					return;

			} catch (Exception e) {
				return;
			}
			;

			ArrayList<SingleDelivery> list = (ArrayList<SingleDelivery>) AnalyzeMessageFromServer.getData();
			try {
				for (int i = 0; i < list.size(); i++) {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/fxml/SingleDelivery.fxml"));
					HBox hBox = fxmlLoader.load();
					SingleDeliveryController singleDeliveryController = fxmlLoader.getController();
					singleDeliveryController.setData(list.get(i));
					DeliveryLayout.getChildren().add(hBox);

				}
			} catch (Exception e) {
				
			}
		}

	}

	@FXML
	void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader
				.load(Objects.requireNonNull(getClass().getResource("/fxml/DeliveryLoginScreen.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Delivery Screen");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
	}

	@FXML
	void initialize() {
		Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
		avatarImg.setImage(personImage);
		userName.setText(LoginScreenController.user.getUsername());
		InsertToTable();
	}

	public void InsertToTable() {
		ArrayList<SingleDelivery> list = new ArrayList<>();
		chat.accept(new Message(MessageType.GET_SINGLE_DELIVERY, null));
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
		} catch (Exception e) {
			
		}
	}

}
