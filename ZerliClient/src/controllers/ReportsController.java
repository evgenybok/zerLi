package controllers;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
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

public class ReportsController  {
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
    private ComboBox<?> ComplaintQuart;

    @FXML
    private ComboBox<?> ComplaintYear;
    
    @FXML
    private ComboBox<?> MonthlyMonth;

    @FXML
    private ComboBox<?> MonthlyReport;

    @FXML
    private ComboBox<?> MonthlyYear;
    @FXML
    private HBox ComplaitBox;

    @FXML
    void btnBack(MouseEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/branchManager.fxml")));
		Scene scene = new Scene(parent);
		Stage deliveryDetailsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		deliveryDetailsStage.setTitle("Delivery Details");
		deliveryDetailsStage.setScene(scene);
		deliveryDetailsStage.show();
		deliveryDetailsStage.centerOnScreen();
    }
    @FXML
    void btnView(MouseEvent event) {

    }


    @FXML
    void btnComplaint(MouseEvent event) {
    	if(ComplaintCu.isSelected()) {
    		Monthly.setDisable(true);
    		MonthlyYear.setDisable(true);
    		MonthlyReport.setDisable(true);
    		MonthlyMonth.setDisable(true);
    	}
    	else
    		Monthly.setDisable(false);
    		MonthlyYear.setDisable(false);
    		MonthlyReport.setDisable(false);
    		MonthlyMonth.setDisable(false);
    }

    @FXML
    void btnMonthly(MouseEvent event) {
    	if(Monthly.isSelected()) {
    		ComplaintCu.setDisable(true);
    		ComplaitBox.setDisable(true);
    	}
    	else
    		ComplaintCu.setDisable(false);
    		ComplaitBox.setDisable(false);
    }
    @FXML
    void initialize() {
    }

}

