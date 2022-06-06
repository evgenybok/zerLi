package controllers;

import static controllers.CEOComplaintController.Quarter;
import static controllers.CEOComplaintController.StoreID;
import static controllers.CEOComplaintController.Year;
import static controllers.IPScreenController.chat;

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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CEOComplaintReportController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField BranchText;

	@FXML
	private TextField MaxComplaints;

	@FXML
	private TextField MinComplaints;

	@FXML
	private TextField TotalComplaints;

	@FXML
	private Button Back;

	@FXML
	private ImageView avatarImg;

	@FXML
	private Text userName;

	@FXML
	private BarChart<String, Number> BarChart;

	@FXML
	void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader
				.load(Objects.requireNonNull(getClass().getResource("/fxml/CEOComplaintScreen.fxml")));
		parent.getStylesheets().add("/css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage complaintStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		complaintStage.setScene(scene);
		complaintStage.show();
		complaintStage.centerOnScreen();

	}

	@FXML
	void initialize() throws IOException {
		Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
		avatarImg.setImage(personImage);
		userName.setText(LoginScreenController.user.getUsername());
		ArrayList<String> arrComplaint = new ArrayList<>();
		ArrayList<String> StoreName1 = new ArrayList<>();
		ArrayList<String> complaint_Graph_Stats = new ArrayList<>();
		XYChart.Series<String, Number> series1 = new XYChart.Series();
		int maxComplaint = 0;
		int minComplaint = Integer.MAX_VALUE;
		int total = 0;

		series1.setName("Complaint");

		arrComplaint.add(StoreID);
		arrComplaint.add(Quarter);
		arrComplaint.add(Year);

		chat.accept(new Message(MessageType.GET_STORE_NAME_BY_ID, StoreID));
		StoreName1 = (ArrayList<String>) AnalyzeMessageFromServer.getData();

		BranchText.setText(StoreName1.get(0) + " Report");
		BarChart.setTitle("Chart For Quarter:  " + Quarter + "/" + Year);

		chat.accept(new Message(MessageType.COMPLAINT_GRAPH_STATISTICS, arrComplaint));
		complaint_Graph_Stats = (ArrayList<String>) AnalyzeMessageFromServer.getData();

		if (complaint_Graph_Stats == null) {
			return;
		}

		for (int i = 1; i < complaint_Graph_Stats.size(); i++) {
			if (Integer.valueOf(complaint_Graph_Stats.get(i)) > maxComplaint) {
				maxComplaint = Integer.valueOf(complaint_Graph_Stats.get(i));
			}
			if (Integer.valueOf(complaint_Graph_Stats.get(i)) < minComplaint) {
				minComplaint = Integer.valueOf(complaint_Graph_Stats.get(i));
			}
			total += Integer.valueOf(complaint_Graph_Stats.get(i));
		}

		MaxComplaints.setText(String.valueOf(maxComplaint));
		MinComplaints.setText(String.valueOf(minComplaint));
		TotalComplaints.setText(String.valueOf(total));

		series1.getData().add(new XYChart.Data("Month1", Integer.parseInt(complaint_Graph_Stats.get(1))));
		series1.getData().add(new XYChart.Data("Month2", Integer.parseInt(complaint_Graph_Stats.get(2))));
		series1.getData().add(new XYChart.Data("Month3", Integer.parseInt(complaint_Graph_Stats.get(3))));
		BarChart.getData().add(series1);

	}
}
