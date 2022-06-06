package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import clientanalyze.AnalyzeMessageFromServer;
import communication.Message;
import communication.MessageType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.SingleUser;

/**
 * @author Evgeny
 * A single user for the manager screen to show data and update status.
 */
public class SingleUserController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label FirstName;

	@FXML
	private Label LastName;

	@FXML
	private Label Status;

	@FXML
	private Label UserID;
	@FXML
	private ComboBox<String> StatusCombo;

	/**
	 * Updates status of customer
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnSubmit(MouseEvent event) throws IOException {
		String Status = StatusCombo.getValue();
		String updateDetails = Status + "@" + UserID.getText();
		try {
			chat.accept(new Message(MessageType.UPDATE_STATUS_BY_MANAGER, updateDetails));
			if (AnalyzeMessageFromServer.getData().equals(null)) // Incorrect username / password
				return;
		} catch (Exception e) {
			return;
		}
		JOptionPane.showMessageDialog(null, "User Status Has Changed Successfully", "Info",
				JOptionPane.INFORMATION_MESSAGE);
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/EditUsers.fxml")));
		parent.getStylesheets().add("/css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Customer");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();

	}

	/**
	 * Sets the right data for a single user from the DB.
	 * @param singleUser
	 */
	public void setData(SingleUser singleUser) {
		UserID.setText(singleUser.getUserId());
		FirstName.setText(singleUser.getFirstName());
		LastName.setText(singleUser.getLastName());
		Status.setText(singleUser.getStatus());
	}

	/**
	 * Initializes shown table
	 */
	@FXML
	void initialize() {
		StatusCombo.setItems(FXCollections.observableArrayList("Active", "Frozen"));
		StatusCombo.getSelectionModel().selectFirst();
	}

}
