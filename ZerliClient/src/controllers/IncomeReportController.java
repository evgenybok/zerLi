package controllers;

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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static controllers.IPScreenController.chat;
import static controllers.ReportsController.*;

/**
 * @author Evgeny
 * Branch manager can view his store's income report and its data here.
 */
public class IncomeReportController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button Back;

	@FXML
	private LineChart<String, Number> lineChart;
	@FXML
	private TextField BestWeek;

	@FXML
	private TextField WorstWeek;

	@FXML
	private TextField TotalSales;
	@FXML
	private Label branchLabel;
	@FXML
    private ImageView backImage;
	@FXML
    private ImageView reportImage;

	/**
	 * Sends the user back to the branch manager main screen.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/ReportsNew.fxml")));
		parent.getStylesheets().add("css/styleNew.css");

		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Reports Screen");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
	}

	/**
	 * Initializes data shown on screen
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	void initialize() {
		double maxWeek = 0;
		double minWeek = Double.MAX_VALUE;
		int sum = 0;

		ArrayList<String> arr = new ArrayList<>();
		ArrayList<String> StoreName = new ArrayList<>();
		ArrayList<String> Graph_Stats = new ArrayList<>();
		XYChart.Series<String, Number> series = new XYChart.Series();
		series.setName("Income");

		arr.add(StoreID);
		arr.add(Month);
		arr.add(Year);

		chat.accept(new Message(MessageType.GET_STORE_NAME_BY_ID, StoreID));
		StoreName = (ArrayList<String>) AnalyzeMessageFromServer.getData();
		branchLabel.setText(StoreName.get(0) + " Report");
		lineChart.setTitle("Chart Of " + Month + "/" + Year);

		chat.accept(new Message(MessageType.GRAPH_STATISTICS, arr));
		Graph_Stats = (ArrayList<String>) AnalyzeMessageFromServer.getData();

		for (int i = 1; i < Graph_Stats.size(); i++) {
			if (Double.valueOf(Graph_Stats.get(i)) > maxWeek) {
				maxWeek = Double.valueOf(Graph_Stats.get(i));
			}
			if (Double.valueOf(Graph_Stats.get(i)) < minWeek) {
				minWeek = Double.valueOf(Graph_Stats.get(i));
			}
			sum += Double.valueOf(Graph_Stats.get(i));
		}

		BestWeek.setText(String.valueOf(maxWeek) + "ILS");
		WorstWeek.setText(String.valueOf(minWeek) + "ILS");
		TotalSales.setText(String.valueOf(sum) + "ILS");

		series.getData().add(new XYChart.Data("Week1", Double.parseDouble(Graph_Stats.get(1))));
		series.getData().add(new XYChart.Data("Week2", Double.parseDouble(Graph_Stats.get(2))));
		series.getData().add(new XYChart.Data("Week3", Double.parseDouble(Graph_Stats.get(3))));
		series.getData().add(new XYChart.Data("Week4", Double.parseDouble(Graph_Stats.get(4))));
		lineChart.getData().add(series);
		Image backarrow = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/backArrow.png")));
		backImage.setImage(backarrow);
		Image reportimage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/imageofreport.png")));
		reportImage.setImage(reportimage);
	}

}
