package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.util.Objects;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.SingleWorker;

/**
 * @author Evgeny
 * Branch manager can update the roles of workers on this screen.
 */
public class SingleWorkerController {

    @FXML
    private Label UserID;

    @FXML
    private Label FirstName;

    @FXML
    private Label LastName;

    @FXML
    private ComboBox<String> roleCombo;

    @FXML
    private Button updateRole;
    
    @FXML
    private Label role;


    /**
     * Updates in DB the role of the chosen worker to a new selected role
     * @param event
     */
    @FXML
    void btnUpdateRole(MouseEvent event) {

    	String role = roleCombo.getValue();
		String updateDetails = role + "@" + UserID.getText();
		if(!(role.equals("worker")) &&!(role.equals("Delivery")) && !(role.equals("customer specialist")) && 
				!(role.equals("customer service")) && !(role.equals("Marketing")))
		{
			JOptionPane.showMessageDialog(null, "Wrong user role selected!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			chat.accept(new Message(MessageType.UPDATE_ROLE_BY_MANAGER, updateDetails));
			if (AnalyzeMessageFromServer.getData().equals(null)) // Incorrect username / password
				return;
		} catch (Exception e) {
			return;
		}
		JOptionPane.showMessageDialog(null, "User Role Has Changed Successfully", "Info",
				JOptionPane.INFORMATION_MESSAGE);
		try {
			Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/EditWorkerRoleScreen.fxml")));
			parent.getStylesheets().add("/css/styleNew.css");
			Scene scene = new Scene(parent);
			Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			customerStage.setTitle("Customer");
			customerStage.setScene(scene);
			customerStage.show();
			customerStage.centerOnScreen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
	 * Sets data on single worker on screen
	 * @param singleWorker
	 */
	public void setData(SingleWorker singleWorker) {
		UserID.setText(singleWorker.getID());
		FirstName.setText(singleWorker.getFirstName());
		LastName.setText(singleWorker.getLastName());
		role.setText(singleWorker.getRole());
	}
	
	/**
	 * Initializes shown table
	 */
	@FXML
	void initialize() {
		roleCombo.setItems(FXCollections.observableArrayList("worker", "Delivery","customer specialist", "customer service", "Marketing"));
		roleCombo.getSelectionModel().selectFirst();
	}
}
