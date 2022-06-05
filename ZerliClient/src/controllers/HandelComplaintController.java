package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import logic.SingleComplaint;

public class HandelComplaintController {

	@FXML
	private Button Back;

	@FXML
	private VBox ComplaintLayout;

	@FXML
	private TextField IdSearch;

	@FXML
	private Text accountType;

	@FXML
	private ImageView avatarImg;

	@FXML
	private Text userName;

	@FXML
	void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		chat.accept(new Message(MessageType.LOGOUT, LoginScreenController.user));
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerService.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage csScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
		csScreen.setTitle("Customer Service Screen");
		csScreen.setScene(scene);
		csScreen.show();
		csScreen.centerOnScreen();
	}

	@SuppressWarnings("unchecked")
	@FXML
	void initialize() {
		Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
		avatarImg.setImage(personImage);
		userName.setText(LoginScreenController.user.getUsername());
		this.accountType.setText("Customer Service"); // accountType
		this.userName.setText(LoginScreenController.user.getUsername()); // userName
		List<SingleComplaint> complaint = new ArrayList<>();
		chat.accept(new Message(MessageType.GET_COMPLAINTS, LoginScreenController.user.getID()));
		complaint = (ArrayList<SingleComplaint>) AnalyzeMessageFromServer.getData();
		if (!(complaint == null)) {
			try {
				for (int i = 0; i < complaint.size(); i++) {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/fxml/SingleComplaintScreen.fxml"));
					HBox hBox = fxmlLoader.load();
					SingleComplaintController singleComplaintController = fxmlLoader.getController();
					singleComplaintController.setData(complaint.get(i));
					ComplaintLayout.getChildren().add(hBox);

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@FXML
	void Serach(MouseEvent event) {
		ArrayList<SingleComplaint> complain = new ArrayList<>();
		String userid = IdSearch.getText();
		ComplaintLayout.getChildren().clear();
		if (IdSearch.getText().isEmpty()) {
			initialize();
		}
		try {
			chat.accept(new Message(MessageType.GET_COMPLAINT_BY_ID, userid));
			if (AnalyzeMessageFromServer.getData().equals(null))
				return;

		} catch (Exception e) {
			return;
		}
		;
		complain = (ArrayList<SingleComplaint>) AnalyzeMessageFromServer.getData();
		try {

			for (int i = 0; i < complain.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/SingleComplaintScreen.fxml"));
				HBox hBox = fxmlLoader.load();
				SingleComplaintController singleComplaintController = fxmlLoader.getController();
				singleComplaintController.setData(complain.get(i));
				ComplaintLayout.getChildren().add(hBox);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
