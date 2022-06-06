package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

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
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Evgeny
 * Allows the user to choose the data for the income report
 */
public class CEOIncomeController {

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
	private CheckBox Monthly;

	@FXML
	private ComboBox<String> MonthlyYear;

	@FXML
	private ComboBox<String> MonthlyMonth;

	@FXML
	private ComboBox<String> MonthlyStore;

	@FXML
	private CheckBox Quarterly_Store1;

	@FXML
	private HBox ComplaitBox;

	@FXML
	private ComboBox<String> Quarterly_Year1;

	@FXML
	private ComboBox<String> QuarterlyQuarter1;

	@FXML
	private ComboBox<String> QuarterlyStore1;

	@FXML
	private CheckBox Quarterly_Store2;

	@FXML
	private HBox ComplaitBox1;

	@FXML
	private ComboBox<String> Quarterly_Year2;

	@FXML
	private ComboBox<String> QuarterlyQuarter2;

	@FXML
	private ComboBox<String> QuarterlyStore2;

	@FXML
	private ImageView avatarImg;

	public static int FlagMonth = 0;
	public static int FlagStore1 = 0;
	public static int FlagStore2 = 0;

	public static String StoreID;
	public static String Month;
	public static String Year;
	public static String Quarter;

	public static String StoreID2;

	public static String Year2;
	public static String Quarter2;

	/**
	 * Sends the user back to the CEO main screen
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
	 * Checkbox for the monthly report
	 * @param event
	 */
	@FXML
	void btnMonthly(MouseEvent event) {

		FlagMonth = 1;
		FlagStore1 = 0;
		FlagStore2 = 0;

		if (Monthly.isSelected()) {

			Quarterly_Store1.setDisable(true);
			Quarterly_Store2.setDisable(true);
			QuarterlyStore1.setDisable(true);
			QuarterlyStore2.setDisable(true);
			Quarterly_Year1.setDisable(true);
			Quarterly_Year2.setDisable(true);
			QuarterlyQuarter1.setDisable(true);
			QuarterlyQuarter2.setDisable(true);
			QuarterlyStore1.setDisable(true);
			QuarterlyStore2.setDisable(true);
			MonthlyYear.getSelectionModel().selectLast();
			MonthlyMonth.getSelectionModel().selectFirst();
			MonthlyStore.getSelectionModel().selectFirst();
		} else {
			Quarterly_Store1.setDisable(false);
			Quarterly_Store2.setDisable(false);
			QuarterlyStore1.setDisable(false);
			QuarterlyStore2.setDisable(false);
			Quarterly_Year1.setDisable(false);
			Quarterly_Year2.setDisable(false);
			QuarterlyQuarter1.setDisable(false);
			QuarterlyQuarter2.setDisable(false);
			QuarterlyStore1.setDisable(false);
			QuarterlyStore2.setDisable(false);
			MonthlyYear.getSelectionModel().clearSelection();
			MonthlyMonth.getSelectionModel().clearSelection();
			MonthlyStore.getSelectionModel().clearSelection();
		}

	}

	/**
	 * Checkbox for the first quarterly report
	 * @param event
	 */
	@FXML
	void btnQuarterly_1(MouseEvent event) {
		FlagMonth = 0;
		FlagStore1 = 1;
		if (Quarterly_Store1.isSelected()) {
			Monthly.setDisable(true);
			MonthlyYear.setDisable(true);
			MonthlyStore.setDisable(true);
			MonthlyMonth.setDisable(true);
			Quarterly_Year1.getSelectionModel().selectLast();
			QuarterlyQuarter1.getSelectionModel().selectFirst();
			QuarterlyStore1.getSelectionModel().selectFirst();
		} else {
			Monthly.setDisable(false);
			MonthlyYear.setDisable(false);
			MonthlyStore.setDisable(false);
			MonthlyMonth.setDisable(false);
			Quarterly_Year1.getSelectionModel().clearSelection();
			QuarterlyQuarter1.getSelectionModel().clearSelection();
			QuarterlyStore1.getSelectionModel().clearSelection();
		}

	}

	/**
	 * Checkbox for the second quarterly report
	 * @param event
	 */
	@FXML
	void btnQuarterly_2(MouseEvent event) {
		FlagMonth = 0;
		FlagStore2 = 1;

		if (Quarterly_Store2.isSelected()) {
			Monthly.setDisable(true);
			MonthlyYear.setDisable(true);
			MonthlyStore.setDisable(true);
			MonthlyMonth.setDisable(true);
			Quarterly_Year2.getSelectionModel().selectLast();
			QuarterlyQuarter2.getSelectionModel().selectFirst();
			QuarterlyStore2.getSelectionModel().selectFirst();
		} else {
			Monthly.setDisable(false);
			MonthlyYear.setDisable(false);
			MonthlyStore.setDisable(false);
			MonthlyMonth.setDisable(false);
			Quarterly_Year2.getSelectionModel().clearSelection();
			QuarterlyQuarter2.getSelectionModel().clearSelection();
			QuarterlyStore2.getSelectionModel().clearSelection();
		}

	}

	/**
	 * Opens the report with the selected data to view
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnView(MouseEvent event) throws IOException {

		if (FlagMonth == 1) {

			String monthlyMonth = MonthlyMonth.getValue();
			String monthlyYear = MonthlyYear.getValue();
			String StorePick = MonthlyStore.getValue();

			StoreID = StorePick;
			Month = monthlyMonth;
			Year = monthlyYear;

		}

		if (FlagStore1 == 1) {

			String quarterlyYear = Quarterly_Year1.getValue();
			String quarterlyQuarter = QuarterlyQuarter1.getValue();
			String StorePick = QuarterlyStore1.getValue();

			StoreID = StorePick;
			Quarter = quarterlyQuarter;
			Year = quarterlyYear;

		}
		if (FlagStore2 == 1) {

			String quarterlyYear = Quarterly_Year2.getValue();
			String quarterlyQuarter = QuarterlyQuarter2.getValue();
			String StorePick = QuarterlyStore2.getValue();

			StoreID2 = StorePick;
			Quarter2 = quarterlyQuarter;
			Year2 = quarterlyYear;

		}
		if(FlagMonth==0 && FlagStore1==0 && FlagStore2==0)
			return;

		try {
			((Node) event.getSource()).getScene().getWindow().hide();
			Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CEOIncomeReport.fxml")));
			parent.getStylesheets().add("/css/styleNew.css");
			Scene scene = new Scene(parent);
			Stage OrderReportStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			OrderReportStage.setScene(scene);
			OrderReportStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		MonthlyYear.setItems(FXCollections.observableArrayList("2010", "2011", "2012", "2013", "2014", "2015", "2016",
				"2017", "2018", "2019", "2020", "2021", "2022"));
		MonthlyMonth.setItems(FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07", "08", "09",
				"10", "11", "12"));
		Quarterly_Year1.setItems(FXCollections.observableArrayList("2010", "2011", "2012", "2013", "2014", "2015",
				"2016", "2017", "2018", "2019", "2020", "2021", "2022"));
		Quarterly_Year2.setItems(FXCollections.observableArrayList("2010", "2011", "2012", "2013", "2014", "2015",
				"2016", "2017", "2018", "2019", "2020", "2021", "2022"));

		QuarterlyQuarter1.setItems(FXCollections.observableArrayList("01", "02", "03", "04"));
		QuarterlyQuarter2.setItems(FXCollections.observableArrayList("01", "02", "03", "04"));

		MonthlyStore
				.setItems(FXCollections.observableArrayList("2000", "2001", "2002", "2003", "2004", "2005", "2006"));
		QuarterlyStore1
				.setItems(FXCollections.observableArrayList("2000", "2001", "2002", "2003", "2004", "2005", "2006"));
		QuarterlyStore2
				.setItems(FXCollections.observableArrayList("2000", "2001", "2002", "2003", "2004", "2005", "2006"));
		userName.setText(LoginScreenController.user.getUsername());// set The username to the specific branchManager.

	}
}
