package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Evgeny Branch manager can select month and year and report type or
 *         complaint year and quarter
 */
public class ReportsController {
	@FXML
	private Button View;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private CheckBox ComplaintCu;

	@FXML
	private CheckBox Monthly;

	@FXML
	private Button Back;

	@FXML
	private Text accountType;

	@FXML
	private Text userName;

	@FXML
	private ImageView avatarImg;

	@FXML
	private ComboBox<String> ComplaintQuart;

	@FXML
	private ComboBox<String> ComplaintYear;

	@FXML
	private ComboBox<String> MonthlyMonth;

	@FXML
	private ComboBox<String> MonthlyReport;

	@FXML
	private ComboBox<String> MonthlyYear;

	int FlagMonth = 0, FlagComplaint = 0;
	public static String StoreID;
	public static String Month;
	public static String Year;
	public static String Quarter;

	/**
	 * Sends the user back to the branch manager main screen
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/branchManager.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage managerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		managerStage.setTitle("Delivery Details");
		managerStage.setScene(scene);
		managerStage.show();
		managerStage.centerOnScreen();
	}

	/**
	 * Opens the selected report with the selected data.
	 * @param event
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@FXML
	void btnView(MouseEvent event) throws IOException, InterruptedException {

		if (FlagMonth == 1) {
			if (MonthlyReport.getValue().equals("Income")) {
				String monthlyReport = "Income";
				String monthlyMonth = MonthlyMonth.getValue();
				String monthlyYear = MonthlyYear.getValue();
				ArrayList<String> arr = new ArrayList<>();
				String ID = LoginScreenController.user.getID();// Here we have the manager ID.

				try {
					chat.accept(new Message(MessageType.GET_STORE_ID_BY_WORKER_ID, ID));
					if (AnalyzeMessageFromServer.getData().equals(null))
						return;

				} catch (Exception e) {
					return;
				}
				;
				String Store = (String) AnalyzeMessageFromServer.getData();
				StoreID = Store;
				Month = monthlyMonth;
				Year = monthlyYear;

				arr.add(Store);
				arr.add(monthlyMonth);
				arr.add(monthlyYear);

				chat.accept(new Message(MessageType.INCOME_REPORT, arr));

				((Node) event.getSource()).getScene().getWindow().hide();
				Parent parent = FXMLLoader
						.load(Objects.requireNonNull(getClass().getResource("/fxml/IncomeReport.fxml")));
				parent.getStylesheets().add("/css/styleNew.css");
				Scene scene = new Scene(parent);
				Stage IncomeReportStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				IncomeReportStage.setTitle("Income-Report");
				IncomeReportStage.setScene(scene);
				IncomeReportStage.show();

			} else if (MonthlyReport.getValue().equals("Orders")) {

				String monthlyReport = "Orders";
				String monthlyMonth = MonthlyMonth.getValue();
				String monthlyYear = MonthlyYear.getValue();

				ArrayList<String> arr = new ArrayList<>();
				String ID = LoginScreenController.user.getID();// Here we have the manager ID.

				try {
					chat.accept(new Message(MessageType.GET_STORE_ID_BY_WORKER_ID, ID));
					if (AnalyzeMessageFromServer.getData().equals(null))
						return;

				} catch (Exception e) {
					return;
				}
				;
				String Store = (String) AnalyzeMessageFromServer.getData();
				StoreID = Store;
				Month = monthlyMonth;
				Year = monthlyYear;

				arr.add(Store);
				arr.add(monthlyMonth);
				arr.add(monthlyYear);

				chat.accept(new Message(MessageType.ORDERS_REPORT, arr));

				((Node) event.getSource()).getScene().getWindow().hide();
				Parent parent = FXMLLoader
						.load(Objects.requireNonNull(getClass().getResource("/fxml/OrderReport.fxml")));
				parent.getStylesheets().add("/css/styleNew.css");
				Scene scene = new Scene(parent);
				Stage OrderReportStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				OrderReportStage.setTitle("Order-Report");
				OrderReportStage.setScene(scene);
				OrderReportStage.show();

			} else {
				JOptionPane.showMessageDialog(null, "Choose Report Type", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (FlagComplaint == 0) {
			JOptionPane.showMessageDialog(null, "Please Check one of the boxes", "Error", JOptionPane.ERROR_MESSAGE);

		}
		if (FlagComplaint == 1) {
			String complaintYear = ComplaintYear.getValue();
			String complaintQuarter = ComplaintQuart.getValue();

			ArrayList<String> arr2 = new ArrayList<>();
			String ID2 = LoginScreenController.user.getID();// Here we have the manager ID.

			try {
				chat.accept(new Message(MessageType.GET_STORE_ID_BY_WORKER_ID, ID2));
				if (AnalyzeMessageFromServer.getData().equals(null))
					return;

			} catch (Exception e) {
				return;
			}

			String Store2 = (String) AnalyzeMessageFromServer.getData();
			StoreID = Store2;
			Quarter = complaintQuarter;
			Year = complaintYear;
			arr2.add(Store2);
			arr2.add(complaintQuarter);
			arr2.add(complaintYear);
			chat.accept(new Message(MessageType.COMPLAINT_REPORT, arr2));

			((Node) event.getSource()).getScene().getWindow().hide();
			Parent parent = FXMLLoader
					.load(Objects.requireNonNull(getClass().getResource("/fxml/ComplaintReport.fxml")));
			parent.getStylesheets().add("/css/styleNew.css");
			Scene scene = new Scene(parent);
			Stage complaintReport = (Stage) ((Node) event.getSource()).getScene().getWindow();
			complaintReport.setTitle("Complaint-Report");
			complaintReport.setScene(scene);
			complaintReport.show();

		}

	}

	/**
	 * Checkbox for the complaint, disables other checkboxes.
	 * @param event
	 */
	@FXML
	void btnComplaint(MouseEvent event) {
		FlagComplaint = 1;
		FlagMonth = 0;
		if (ComplaintCu.isSelected()) {

			Monthly.setDisable(true);
			MonthlyYear.setDisable(true);
			MonthlyReport.setDisable(true);
			MonthlyMonth.setDisable(true);
		} else {
			Monthly.setDisable(false);
			MonthlyYear.setDisable(false);
			MonthlyReport.setDisable(false);
			MonthlyMonth.setDisable(false);
		}
	}

	/**
	 * Checkbox for the month of report, disables other checkboxes.
	 * @param event
	 */
	@FXML
	void btnMonthly(MouseEvent event) {
		FlagMonth = 1;
		FlagComplaint = 0;
		if (Monthly.isSelected()) {

			ComplaintCu.setDisable(true);
			ComplaintQuart.setDisable(true);
			ComplaintYear.setDisable(true);
		} else {
			ComplaintCu.setDisable(false);
			ComplaintYear.setDisable(false);
			ComplaintQuart.setDisable(false);
		}
	}

	/**
	 * Initializes data shown on screen
	 */
	@FXML
	void initialize() {
		Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
		avatarImg.setImage(personImage);
		userName.setText(LoginScreenController.user.getUsername());
		MonthlyYear.setItems(FXCollections.observableArrayList("2010", "2011", "2012", "2013", "2014", "2015", "2016",
				"2017", "2018", "2019", "2020", "2021", "2022"));
		MonthlyMonth.setItems(FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07", "08", "09",
				"10", "11", "12"));
		MonthlyReport.setItems(FXCollections.observableArrayList("Income", "Orders"));
		ComplaintYear.setItems(FXCollections.observableArrayList("2010", "2011", "2012", "2013", "2014", "2015", "2016",
				"2017", "2018",

				"2019", "2020", "2021", "2022"));
		ComplaintQuart.setItems(FXCollections.observableArrayList("01", "02", "03", "04"));
		MonthlyYear.getSelectionModel().selectLast();
		MonthlyMonth.getSelectionModel().selectFirst();
		MonthlyReport.getSelectionModel().selectFirst();
		ComplaintYear.getSelectionModel().selectLast();
		ComplaintQuart.getSelectionModel().selectFirst();
		userName.setText(LoginScreenController.user.getUsername());// set The username to the specific branchManager.

	}

}
