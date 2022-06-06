package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

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
import logic.SingleSelfDelivery;

/**
 * @author Evgeny Delivery user can view the orders he delivered here.
 */
public class DeliverySelfOrderController {

	@FXML
	private Button Back;

	@FXML
	private VBox DeliveryLayout;

	@FXML
	private Text accountType;

	@FXML
	private ImageView avatarImg;

	@FXML
	private TextField orderIDtext;

	@FXML
	private Text userName;

	/**
	 * Shows the selected order in the table.
	 * 
	 * @param event
	 */
	@FXML
	void SearchByOrderID(MouseEvent event) {
		if (orderIDtext.getText().isEmpty()) {
			DeliveryLayout.getChildren().clear();
			initialize();
		} else {
			DeliveryLayout.getChildren().clear();
			try {
				chat.accept(new Message(MessageType.VIEW_SELF_DELIVERY_DETAILS_BY_ORDERID, orderIDtext.getText()));
				if (AnalyzeMessageFromServer.getData().equals(null))
					return;

			} catch (Exception e) {
				return;
			}
			;

			ArrayList<SingleSelfDelivery> list = (ArrayList<SingleSelfDelivery>) AnalyzeMessageFromServer.getData();
			try {
				if(!(list==null)) {
				for (int i = 0; i < list.size(); i++) {
					if(list.get(i).getOrderID()==(Integer.parseInt(orderIDtext.getText())))
					{
						FXMLLoader fxmlLoader = new FXMLLoader();
						fxmlLoader.setLocation(getClass().getResource("/fxml/singleSelfDelivery.fxml"));
						HBox hBox = fxmlLoader.load();
						SingleSelfDeliveryController singleSelfDeliveryController = fxmlLoader.getController();
						singleSelfDeliveryController.setData(list.get(i));
						DeliveryLayout.getChildren().add(hBox);
					}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Sends the user back to the Delivery main screen.
	 * 
	 * @param event
	 * @throws IOException
	 */
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

	/**
	 * Initializes data
	 */
	@FXML
	void initialize() {
		Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
		avatarImg.setImage(personImage);
		userName.setText(LoginScreenController.user.getUsername());
		InsertToTable();
	}

	/**
	 * Initializes data shown on screen
	 */
	@SuppressWarnings("unchecked")
	public void InsertToTable() {
		ArrayList<SingleSelfDelivery> list = new ArrayList<>();
		chat.accept(new Message(MessageType.VIEW_SELF_DELIVERY_DETAILS, LoginScreenController.user.getID()));
		list = (ArrayList<SingleSelfDelivery>) AnalyzeMessageFromServer.getData();
		try {
			if(!(list==null)) {
				for (int i = 0; i < list.size(); i++) {

					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/fxml/singleSelfDelivery.fxml"));
					HBox hBox = fxmlLoader.load();
					SingleSelfDeliveryController singleSelfDeliveryController = fxmlLoader.getController();
					singleSelfDeliveryController.setData(list.get(i));
					DeliveryLayout.getChildren().add(hBox);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}