package controllers;

import static controllers.CEOOrderController.Month;
import static controllers.CEOOrderController.StoreID;
import static controllers.CEOOrderController.Year;
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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * @author Evgeny
 * Detailed report matching the data selected to view
 * shows order amount
 */
public class CEOOrderReportController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label branchLabel;

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private TextField BestWeek;

    @FXML
    private TextField WorstWeek;

    @FXML
    private TextField TotalOrders;

    @FXML
    private Button Back;

    /**
     * Sends the user back to the ceo main screen
     * @param event
     * @throws IOException
     */
    @FXML
    void btnBack(MouseEvent event) throws IOException {

        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CEOOrderScreen.fxml")));
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
        int maxWeek=0;
        int minWeek= Integer.MAX_VALUE;
        int sum=0;

        ArrayList<String> arr=new ArrayList<>();
        ArrayList<String>StoreName=new ArrayList<>();
        ArrayList<String>Graph_Stats=new ArrayList<>();
        XYChart.Series<String,Number> series=new XYChart.Series();
        series.setName("Order");

        arr.add(StoreID);
        arr.add(Month);
        arr.add(Year);

        chat.accept(new Message(MessageType.GET_STORE_NAME_BY_ID,StoreID));
        StoreName= (ArrayList<String>) AnalyzeMessageFromServer.getData();
        branchLabel.setText(StoreName.get(0)+" Report");
        lineChart.setTitle("Chart Of "+Month+"/"+Year);



        chat.accept(new Message(MessageType.ORDER_GRAPH_STATISTICS,arr));
        Graph_Stats= (ArrayList<String>) AnalyzeMessageFromServer.getData();
        if(Graph_Stats==null){
            JOptionPane.showMessageDialog(null, "No Complaint Report For this Period", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for(int i=1;i<Graph_Stats.size();i++){
            if(Integer.valueOf(Graph_Stats.get(i))>maxWeek){
                maxWeek=Integer.valueOf(Graph_Stats.get(i));
            }
            if(Double.valueOf(Graph_Stats.get(i))<minWeek){
                minWeek=Integer.valueOf(Graph_Stats.get(i));
            }
            sum+=Integer.valueOf(Graph_Stats.get(i));
        }

        BestWeek.setText(String.valueOf(maxWeek));
        WorstWeek.setText(String.valueOf(minWeek));
        TotalOrders.setText(String.valueOf(sum));

        
        try {
			series.getData().add(new XYChart.Data("Week1",Integer.parseInt(Graph_Stats.get(1))));
			series.getData().add(new XYChart.Data("Week2",Integer.parseInt(Graph_Stats.get(2))));
			series.getData().add(new XYChart.Data("Week3",Integer.parseInt(Graph_Stats.get(3))));
			series.getData().add(new XYChart.Data("Week4",Integer.parseInt(Graph_Stats.get(4))));
			lineChart.getData().add(series);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}
