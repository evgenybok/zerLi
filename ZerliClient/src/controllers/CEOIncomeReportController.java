package controllers;

import static controllers.CEOIncomeController.FlagMonth;
import static controllers.CEOIncomeController.FlagStore1;
import static controllers.CEOIncomeController.FlagStore2;
import static controllers.CEOIncomeController.Month;
import static controllers.CEOIncomeController.Quarter;
import static controllers.CEOIncomeController.Quarter2;
import static controllers.CEOIncomeController.StoreID;
import static controllers.CEOIncomeController.StoreID2;
import static controllers.CEOIncomeController.Year;
import static controllers.CEOIncomeController.Year2;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @author Evgeny
 * Detailed branch report matching the data selected to view
 * shows income amount
 */
public class CEOIncomeReportController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button Back;

	@FXML
	private BarChart<String, Number> BarChart;

	@FXML
	private TextField BestWeek;

	@FXML
	private TextField WorstWeek;
	@FXML
	private Label BestweekOrMonth;

	@FXML
	private Label WorstweekOrMonth;

	@FXML
	private TextField TotalSales;

	@FXML
	private ImageView avatarImg;

	@FXML
	private Label branchLabel;

	@FXML
	private ImageView reportImg;

	
	/**
	 * Sends the user back to the ceo income screen
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CEOIncomeScreen.fxml")));
		parent.getStylesheets().add("/css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
	}

	/**
	 * Initializes data shown on graph
	 */
	@FXML
	void initialize() {
		Image complaintImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/imageofreport.png")));
		reportImg.setImage(complaintImage);
		Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/backArrow.png")));
		avatarImg.setImage(personImage);
		double maxWeek = 0;
		double minWeek = Double.MAX_VALUE;
		double maxWeek2 = 0;
		double minWeek2 = Double.MAX_VALUE;
		double sum = 0;

		if (FlagMonth == 1) {
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
			BarChart.setTitle("Chart Of " + Month + "/" + Year);

			chat.accept(new Message(MessageType.GRAPH_STATISTICS, arr));
			Graph_Stats = (ArrayList<String>) AnalyzeMessageFromServer.getData();

			if (Graph_Stats == null) {
				JOptionPane.showMessageDialog(null, "No such a report For Month " + Month + "of Year " + Year, "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

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
			BarChart.getData().add(series);

		}

		else if (FlagStore1 == 1 && FlagStore2 == 0) {

			ArrayList<String> arr = new ArrayList<>();
			ArrayList<String> StoreName = new ArrayList<>();
			ArrayList<String> Graph_Stats = new ArrayList<>();
			XYChart.Series<String, Number> series = new XYChart.Series();
			series.setName("Income");

			arr.add(StoreID);
			arr.add(Quarter);
			arr.add(Year);

			chat.accept(new Message(MessageType.GET_STORE_NAME_BY_ID, StoreID));
			StoreName = (ArrayList<String>) AnalyzeMessageFromServer.getData();
			branchLabel.setText(StoreName.get(0) + " Report");
			BarChart.setTitle("Chart Of " + Quarter + "/" + Year);

			chat.accept(new Message(MessageType.INCOME_QUARTER_GRAPH, arr));
			Graph_Stats = (ArrayList<String>) AnalyzeMessageFromServer.getData();
			if (Graph_Stats == null) {
				JOptionPane.showMessageDialog(null, "No such a report For Quarter " + Quarter + " of Year " + Year,
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			BestweekOrMonth.setText("Best Month");
			BestweekOrMonth.setStyle("-fx-font: 24 Berlin Sans FB Demi;");

			WorstweekOrMonth.setText("Worst Month");
			WorstweekOrMonth.setStyle("-fx-font: 24 Berlin Sans FB Demi;");

			for (int i = 1; i < Graph_Stats.size(); i++) {
				if (Double.valueOf(Graph_Stats.get(i)) > maxWeek) {
					maxWeek = Double.valueOf(Graph_Stats.get(i));
				}
				if (Double.valueOf(Graph_Stats.get(i)) < minWeek) {
					minWeek = Double.valueOf(Graph_Stats.get(i));
				}
				sum += Double.valueOf(Graph_Stats.get(i));
			}

			BestWeek.setText(String.valueOf(maxWeek) + " ILS");
			WorstWeek.setText(String.valueOf(minWeek) + " ILS");
			TotalSales.setText(String.valueOf(sum) + " ILS");

			series.getData().add(new XYChart.Data("Month1", Double.parseDouble(Graph_Stats.get(0))));
			series.getData().add(new XYChart.Data("Month2", Double.parseDouble(Graph_Stats.get(1))));
			series.getData().add(new XYChart.Data("Month3", Double.parseDouble(Graph_Stats.get(2))));
			BarChart.getData().add(series);
		}

		else if (FlagStore1 == 0 && FlagStore2 == 1) {

			ArrayList<String> arr = new ArrayList<>();
			ArrayList<String> StoreName = new ArrayList<>();
			ArrayList<String> Graph_Stats = new ArrayList<>();
			XYChart.Series<String, Number> series = new XYChart.Series();
			series.setName("Income");

			arr.add(StoreID2);
			arr.add(Quarter2);
			arr.add(Year2);

			chat.accept(new Message(MessageType.GET_STORE_NAME_BY_ID, StoreID2));
			StoreName = (ArrayList<String>) AnalyzeMessageFromServer.getData();
			branchLabel.setText(StoreName.get(0) + " Report");
			BarChart.setTitle("Chart Of " + Quarter2 + "/" + Year2);

			chat.accept(new Message(MessageType.INCOME_QUARTER_GRAPH, arr));
			Graph_Stats = (ArrayList<String>) AnalyzeMessageFromServer.getData();
			if (Graph_Stats == null) {
				JOptionPane.showMessageDialog(null, "No such a report For Quarter " + Quarter + " of Year " + Year,
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			BestweekOrMonth.setText("Best Month");
			BestweekOrMonth.setStyle("-fx-font: 24 Berlin Sans FB Demi;");

			WorstweekOrMonth.setText("Worst Month");
			WorstweekOrMonth.setStyle("-fx-font: 24 Berlin Sans FB Demi;");

			for (int i = 1; i < Graph_Stats.size(); i++) {
				if (Double.valueOf(Graph_Stats.get(i)) > maxWeek) {
					maxWeek = Double.valueOf(Graph_Stats.get(i));
				}
				if (Double.valueOf(Graph_Stats.get(i)) < minWeek) {
					minWeek = Double.valueOf(Graph_Stats.get(i));
				}
				sum += Double.valueOf(Graph_Stats.get(i));
			}

			BestWeek.setText(String.valueOf(maxWeek) + " ILS");
			WorstWeek.setText(String.valueOf(minWeek) + " ILS");
			TotalSales.setText(String.valueOf(sum) + " ILS");

			series.getData().add(new XYChart.Data("Month1", Double.parseDouble(Graph_Stats.get(0))));
			series.getData().add(new XYChart.Data("Month2", Double.parseDouble(Graph_Stats.get(1))));
			series.getData().add(new XYChart.Data("Month3", Double.parseDouble(Graph_Stats.get(2))));
			BarChart.getData().add(series);

		}

		else if (FlagStore1 == 1 && FlagStore2 == 0) {

			ArrayList<String> arr = new ArrayList<>();
			ArrayList<String> StoreName = new ArrayList<>();
			ArrayList<String> Graph_Stats = new ArrayList<>();
			XYChart.Series<String, Number> series = new XYChart.Series();
			series.setName("Income");

			arr.add(StoreID);
			arr.add(Quarter);
			arr.add(Year);

			chat.accept(new Message(MessageType.GET_STORE_NAME_BY_ID, StoreID));
			StoreName = (ArrayList<String>) AnalyzeMessageFromServer.getData();
			branchLabel.setText(StoreName.get(0) + " Report");
			BarChart.setTitle("Chart Of " + Quarter + "/" + Year);

			chat.accept(new Message(MessageType.INCOME_QUARTER_GRAPH, arr));
			Graph_Stats = (ArrayList<String>) AnalyzeMessageFromServer.getData();

			if ((Graph_Stats == null)) {
				JOptionPane.showMessageDialog(null, "No such a report For Quarter " + Quarter + " of Year " + Year,
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			BestweekOrMonth.setText("Best Month");
			BestweekOrMonth.setStyle("-fx-font: 24 Berlin Sans FB Demi;");

			WorstweekOrMonth.setText("Worst Month");
			WorstweekOrMonth.setStyle("-fx-font: 24 Berlin Sans FB Demi;");

			for (int i = 1; i < Graph_Stats.size(); i++) {
				if (Double.valueOf(Graph_Stats.get(i)) > maxWeek) {
					maxWeek = Double.valueOf(Graph_Stats.get(i));
				}
				if (Double.valueOf(Graph_Stats.get(i)) < minWeek) {
					minWeek = Double.valueOf(Graph_Stats.get(i));
				}
				sum += Double.valueOf(Graph_Stats.get(i));
			}

			BestWeek.setText(String.valueOf(maxWeek) + " ILS");
			WorstWeek.setText(String.valueOf(minWeek) + " ILS");
			TotalSales.setText(String.valueOf(sum) + " ILS");

			series.getData().add(new XYChart.Data("Month1", Double.parseDouble(Graph_Stats.get(0))));
			series.getData().add(new XYChart.Data("Month2", Double.parseDouble(Graph_Stats.get(1))));
			series.getData().add(new XYChart.Data("Month3", Double.parseDouble(Graph_Stats.get(2))));
			BarChart.getData().add(series);

		}

		else if (FlagStore1 == 1 && FlagStore2 == 1) {

			ArrayList<String> arr = new ArrayList<>();
			ArrayList<String> arr2 = new ArrayList<>();
			ArrayList<String> StoreName = new ArrayList<>();
			ArrayList<String> StoreName2 = new ArrayList<>();
			ArrayList<String> Graph_Stats = new ArrayList<>();
			ArrayList<String> Graph_Stats2 = new ArrayList<>();
			XYChart.Series<String, Number> series = new XYChart.Series();
			XYChart.Series<String, Number> series2 = new XYChart.Series();
			series.setName("Graph for Quarter " + Quarter);
			series2.setName("Graph for Quarter " + Quarter2);

			arr.add(StoreID);
			arr.add(Quarter);
			arr.add(Year);

			arr2.add(StoreID2);
			arr2.add(Quarter2);
			arr2.add(Year2);

			chat.accept(new Message(MessageType.GET_STORE_NAME_BY_ID, StoreID));
			StoreName = (ArrayList<String>) AnalyzeMessageFromServer.getData();
			chat.accept(new Message(MessageType.GET_STORE_NAME_BY_ID, StoreID2));
			StoreName2 = (ArrayList<String>) AnalyzeMessageFromServer.getData();

			branchLabel.setText(StoreName.get(0) + " " + StoreName2.get(0) + " Comparison Reports");
			if (Quarter.equals(Quarter2))
				BarChart.setTitle("Chart Of Quarter " + Quarter + " for Year" + Year);
			else
				BarChart.setTitle("Chart Of Quarter  " + Quarter + " And Quarter " + Quarter2 + " for Year " + Year);

			chat.accept(new Message(MessageType.INCOME_QUARTER_GRAPH, arr));
			Graph_Stats = (ArrayList<String>) AnalyzeMessageFromServer.getData();

			chat.accept(new Message(MessageType.INCOME_QUARTER_GRAPH, arr2));
			Graph_Stats2 = (ArrayList<String>) AnalyzeMessageFromServer.getData();

			if ((Graph_Stats == null) && (Graph_Stats2 != null)) {
				JOptionPane.showMessageDialog(null, "No such a report For Quarter " + Quarter + " of Year " + Year,
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			else if ((Graph_Stats2 == null) && (Graph_Stats != null)) {
				JOptionPane.showMessageDialog(null, "No such a report For Quarter " + Quarter2 + " of Year " + Year2,
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else if ((Graph_Stats == null) && (Graph_Stats2 == null)) {
				JOptionPane.showMessageDialog(null, "No such Reports For this information", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			for (int i = 1; i < Graph_Stats.size(); i++) {
				if (Double.valueOf(Graph_Stats.get(i)) > maxWeek) {
					maxWeek = Double.valueOf(Graph_Stats.get(i));
				}
				if (Double.valueOf(Graph_Stats.get(i)) < minWeek) {
					minWeek = Double.valueOf(Graph_Stats.get(i));
				}
				sum += Double.valueOf(Graph_Stats.get(i));
			}

			for (int i = 1; i < Graph_Stats2.size(); i++) {
				if (Double.valueOf(Graph_Stats2.get(i)) > maxWeek2) {
					maxWeek2 = Double.valueOf(Graph_Stats2.get(i));
				}
				if (Double.valueOf(Graph_Stats2.get(i)) < minWeek2) {
					minWeek2 = Double.valueOf(Graph_Stats2.get(i));
				}
				sum += Double.valueOf(Graph_Stats2.get(i));
			}

			if (maxWeek > maxWeek2)
				BestWeek.setText(String.valueOf(maxWeek) + " ILS");
			else
				BestWeek.setText(String.valueOf(maxWeek2) + " ILS");

			if (minWeek > minWeek2)
				WorstWeek.setText(String.valueOf(minWeek2) + " ILS");
			else
				WorstWeek.setText(String.valueOf(minWeek) + " ILS");

			TotalSales.setText(String.valueOf(sum) + " ILS");

			BestweekOrMonth.setText("Best Month");
			BestweekOrMonth.setStyle("-fx-font: 24 Berlin Sans FB Demi;");

			WorstweekOrMonth.setText("Worst Month");
			WorstweekOrMonth.setStyle("-fx-font: 24 Berlin Sans FB Demi;");

			series.getData().add(new XYChart.Data("Month1", Double.parseDouble(Graph_Stats.get(0))));
			series.getData().add(new XYChart.Data("Month2", Double.parseDouble(Graph_Stats.get(1))));
			series.getData().add(new XYChart.Data("Month3", Double.parseDouble(Graph_Stats.get(2))));

			series2.getData().add(new XYChart.Data("Month1", Double.parseDouble(Graph_Stats2.get(0))));
			series2.getData().add(new XYChart.Data("Month2", Double.parseDouble(Graph_Stats2.get(1))));
			series2.getData().add(new XYChart.Data("Month3", Double.parseDouble(Graph_Stats2.get(2))));

			BarChart.getData().add(series);
			BarChart.getData().add(series2);
			
		}

	}

}
