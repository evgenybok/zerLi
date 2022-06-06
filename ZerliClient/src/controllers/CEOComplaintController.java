package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Evgeny
 * Allows the user to choose the data for the complaint report
 */
public class CEOComplaintController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Text userName;

	@FXML
	private Text accountType;

	@FXML
	private Button Back;

	@FXML
	private CheckBox Complaints;

	@FXML
	private ComboBox<String> ComplaintYear;

	@FXML
	private ComboBox<String> ComplaintQuart;

	@FXML
	private ComboBox<String> PickStore;

	@FXML
	private Button View;

	@FXML
	private ImageView avatarImg;

	int FlagComplaint = 0;

	public static String StoreID;
	public static String Quarter;
	public static String Year;

	/**
	 * Sends the user back to the ceo main screen
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnBack(MouseEvent event) throws IOException {

		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CEOScreenNew.fxml")));
		parent.getStylesheets().add("/css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage complaintStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		complaintStage.setScene(scene);
		complaintStage.show();
		complaintStage.centerOnScreen();

	}

	/**
	 * Checkbox for the complaint report
	 * @param event
	 */
	@FXML
	void btnComplaint(MouseEvent event) {
		if(Complaints.isSelected())
		{
			ComplaintYear.getSelectionModel().selectLast();
			ComplaintQuart.getSelectionModel().selectFirst();
			PickStore.getSelectionModel().selectFirst();
		}
		else if(!Complaints.isSelected())
		{
			ComplaintYear.getSelectionModel().clearSelection();
			ComplaintQuart.getSelectionModel().clearSelection();
			PickStore.getSelectionModel().clearSelection();
		}
	}

	/**
	 * Views the report with the selected data
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnView(MouseEvent event) throws IOException {

		if (PickStore.getSelectionModel().isEmpty() || ComplaintQuart.getSelectionModel().isEmpty()
				|| ComplaintYear.getSelectionModel().isEmpty() || !Complaints.isSelected()) {

			JOptionPane.showMessageDialog(null, "Please Check the ComplaintBox And Fill Missing Fields", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			StoreID = PickStore.getValue();
			Quarter = ComplaintQuart.getValue();
			Year = ComplaintYear.getValue();

		}
		if(!Complaints.isSelected())
			return;
		try {
			((Node) event.getSource()).getScene().getWindow().hide();
			Parent parent = FXMLLoader
					.load(Objects.requireNonNull(getClass().getResource("/fxml/CEOComplaintReport.fxml")));
			parent.getStylesheets().add("/css/styleNew.css");
			Scene scene = new Scene(parent);
			Stage OrderReportStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			OrderReportStage.setScene(scene);
			OrderReportStage.show();
		} catch (Exception e) {
			return;
		}

	}

	/**
	 * Initializes data shown on screen
	 */
	@FXML
	void initialize() {
		userName.setText(LoginScreenController.user.getUsername()); // userName
		Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
		avatarImg.setImage(personImage);
		ComplaintYear.setItems(FXCollections.observableArrayList("2010", "2011", "2012", "2013", "2014", "2015", "2016",
				"2017", "2018", "2019", "2020", "2021", "2022"));
		ComplaintQuart.setItems(FXCollections.observableArrayList("01", "02", "03", "04"));
		PickStore.setItems(FXCollections.observableArrayList("2000", "2001", "2002", "2003", "2004", "2005", "2006"));

	}
}
