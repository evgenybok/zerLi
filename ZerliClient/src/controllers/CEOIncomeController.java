package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static controllers.IPScreenController.chat;

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

    public static int FlagMonth=0;
    public static int FlagStore1=0;
    public static int FlagStore2=0;

    public static String StoreID;
    public static String Month;
    public static String Year;
    public static String Quarter;

    public static String StoreID2;

    public static String Year2;
    public static String Quarter2;

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
    void btnMonthly(MouseEvent event) {

        FlagMonth = 1;
        FlagStore1=0;
        FlagStore2=0;

        if(Monthly.isSelected()) {

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
        }
        else {
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
        }


    }

    @FXML
    void btnQuarterly_1(MouseEvent event) {
        FlagMonth = 0;
        FlagStore1=1;
        if(Quarterly_Store1.isSelected()){
            Monthly.setDisable(true);
            MonthlyYear.setDisable(true);
            MonthlyStore.setDisable(true);
            MonthlyMonth.setDisable(true);
        }
        else {
            Monthly.setDisable(false);
            MonthlyYear.setDisable(false);
            MonthlyStore.setDisable(false);
            MonthlyMonth.setDisable(false);

        }


    }

    @FXML
    void btnQuarterly_2(MouseEvent event) {
        FlagMonth = 0;
        FlagStore2=1;

        if(Quarterly_Store2.isSelected()){
            Monthly.setDisable(true);
            MonthlyYear.setDisable(true);
            MonthlyStore.setDisable(true);
            MonthlyMonth.setDisable(true);
        }
        else {
            Monthly.setDisable(false);
            MonthlyYear.setDisable(false);
            MonthlyStore.setDisable(false);
            MonthlyMonth.setDisable(false);

        }

    }

    @FXML
    void btnView(MouseEvent event) throws IOException {

        if(FlagMonth==1){

            String monthlyMonth = MonthlyMonth.getValue();
            String monthlyYear = MonthlyYear.getValue();
            String StorePick=MonthlyStore.getValue();

            StoreID = StorePick;
            Month = monthlyMonth;
            Year = monthlyYear;


        }

        if(FlagStore1==1){

            String quarterlyYear = Quarterly_Year1.getValue();
            String quarterlyQuarter = QuarterlyQuarter1.getValue();
            String StorePick=QuarterlyStore1.getValue();

            StoreID = StorePick;
            Quarter= quarterlyQuarter;
            Year = quarterlyYear;

        }
        if(FlagStore2==1){

            String quarterlyYear = Quarterly_Year2.getValue();
            String quarterlyQuarter = QuarterlyQuarter2.getValue();
            String StorePick=QuarterlyStore2.getValue();

            StoreID2 = StorePick;
            Quarter2= quarterlyQuarter;
            Year2 = quarterlyYear;

        }


        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CEOIncomeReport.fxml")));
        parent.getStylesheets().add("/css/styleNew.css");
        Scene scene = new Scene(parent);
        Stage OrderReportStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OrderReportStage.setScene(scene);
        OrderReportStage.show();


    }

    @FXML
    void initialize() {
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'CEOIncomeScreen.fxml'.";
        assert accountType != null : "fx:id=\"accountType\" was not injected: check your FXML file 'CEOIncomeScreen.fxml'.";
        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'CEOIncomeScreen.fxml'.";
        assert View != null : "fx:id=\"View\" was not injected: check your FXML file 'CEOIncomeScreen.fxml'.";
        assert Monthly != null : "fx:id=\"Monthly\" was not injected: check your FXML file 'CEOIncomeScreen.fxml'.";
        assert MonthlyYear != null : "fx:id=\"MonthlyYear\" was not injected: check your FXML file 'CEOIncomeScreen.fxml'.";
        assert MonthlyMonth != null : "fx:id=\"MonthlyMonth\" was not injected: check your FXML file 'CEOIncomeScreen.fxml'.";
        assert MonthlyStore != null : "fx:id=\"MonthlyStore\" was not injected: check your FXML file 'CEOIncomeScreen.fxml'.";
        assert Quarterly_Store1 != null : "fx:id=\"Quarterly_Store1\" was not injected: check your FXML file 'CEOIncomeScreen.fxml'.";
        assert ComplaitBox != null : "fx:id=\"ComplaitBox\" was not injected: check your FXML file 'CEOIncomeScreen.fxml'.";
        assert Quarterly_Year1 != null : "fx:id=\"Quarterly_Year1\" was not injected: check your FXML file 'CEOIncomeScreen.fxml'.";
        assert QuarterlyQuarter1 != null : "fx:id=\"QuarterlyQuarter1\" was not injected: check your FXML file 'CEOIncomeScreen.fxml'.";
        assert QuarterlyStore1 != null : "fx:id=\"QuarterlyStore1\" was not injected: check your FXML file 'CEOIncomeScreen.fxml'.";
        assert Quarterly_Store2 != null : "fx:id=\"Quarterly_Store2\" was not injected: check your FXML file 'CEOIncomeScreen.fxml'.";
        assert ComplaitBox1 != null : "fx:id=\"ComplaitBox1\" was not injected: check your FXML file 'CEOIncomeScreen.fxml'.";
        assert Quarterly_Year2 != null : "fx:id=\"Quarterly_Year2\" was not injected: check your FXML file 'CEOIncomeScreen.fxml'.";
        assert QuarterlyQuarter2 != null : "fx:id=\"QuarterlyQuarter2\" was not injected: check your FXML file 'CEOIncomeScreen.fxml'.";
        assert QuarterlyStore2 != null : "fx:id=\"QuarterlyStore2\" was not injected: check your FXML file 'CEOIncomeScreen.fxml'.";


        MonthlyYear.setItems(FXCollections.observableArrayList("2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018",
                "2019", "2020", "2021", "2022"));
        MonthlyMonth.setItems(FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07", "08", "09",
                "10", "11", "12"));
        Quarterly_Year1.setItems(FXCollections.observableArrayList("2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018",
                "2019", "2020", "2021", "2022"));
        Quarterly_Year2.setItems(FXCollections.observableArrayList("2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018",
                "2019", "2020", "2021", "2022"));

        QuarterlyQuarter1.setItems(FXCollections.observableArrayList("01", "02", "03", "04"));
        QuarterlyQuarter2.setItems(FXCollections.observableArrayList("01", "02", "03", "04"));

        MonthlyStore.setItems(FXCollections.observableArrayList("2000","2001","2002","2003","2004","2005","2006"));
        QuarterlyStore1.setItems(FXCollections.observableArrayList("2000","2001","2002","2003","2004","2005","2006"));
        QuarterlyStore2.setItems(FXCollections.observableArrayList("2000","2001","2002","2003","2004","2005","2006"));
        userName.setText(LoginScreenController.user.getUsername());//set The username to the specific branchManager.

    }
}
