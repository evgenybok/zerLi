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

public class CEOOrderController {

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
	private Button View;

	@FXML
	private CheckBox Orders;

	@FXML
	private ComboBox<String> OrderYear;

	@FXML
	private ComboBox<String> OrderMonth;

	@FXML
	private ComboBox<String> PickStore;

	@FXML
	private ImageView avatarImg;

	public static String StoreID;
	public static String Month;
	public static String Year;

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

	@FXML
	void btnOrders(MouseEvent event) {
		if(Orders.isSelected())
		{
			OrderYear.getSelectionModel().selectLast();
			OrderMonth.getSelectionModel().selectFirst();
			PickStore.getSelectionModel().selectFirst();
		}
		else if(!Orders.isSelected())
		{
			OrderYear.getSelectionModel().clearSelection();
			OrderMonth.getSelectionModel().clearSelection();
			PickStore.getSelectionModel().clearSelection();
		}
	}

	@FXML
	void btnView(MouseEvent event) throws IOException {

		if (PickStore.getSelectionModel().isEmpty() || OrderMonth.getSelectionModel().isEmpty()
				|| OrderMonth.getSelectionModel().isEmpty() || !Orders.isSelected()) {

			JOptionPane.showMessageDialog(null, "Please Check the ComplaintBox And Fill Missing Fields", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			StoreID = PickStore.getValue();
			Month = OrderMonth.getValue();
			Year = OrderYear.getValue();

		}
		if(!Orders.isSelected())
			return;

		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CEOOrderReport.fxml")));
		parent.getStylesheets().add("/css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage OrderReportStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		OrderReportStage.setScene(scene);
		OrderReportStage.show();

	}

	@FXML
	void initialize() {
		userName.setText(LoginScreenController.user.getUsername()); // userName
		Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
		avatarImg.setImage(personImage);
		OrderYear.setItems(FXCollections.observableArrayList("2010", "2011", "2012", "2013", "2014", "2015", "2016",
				"2017", "2018", "2019", "2020", "2021", "2022"));
		OrderMonth.setItems(FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07", "08", "9", "10",
				"11", "12"));
		PickStore.setItems(FXCollections.observableArrayList("2000", "2001", "2002", "2003", "2004", "2005", "2006"));

	}
}
