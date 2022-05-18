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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ReportScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Back;

    @FXML
    private Button ComplaintsReport;

    @FXML
    private Button IncomReport;

    @FXML
    private Button OrderReport;

    @FXML
    void btnBack(MouseEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/branchManager.fxml")));
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Customer");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
    }

    @FXML
    void btnComplaint(MouseEvent event) {

    }

    @FXML
    void btnIncom(MouseEvent event) {

    }

    @FXML
    void btnOrders(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'BranchManagerReports.fxml'.";
        assert ComplaintsReport != null : "fx:id=\"ComplaintsReport\" was not injected: check your FXML file 'BranchManagerReports.fxml'.";
        assert IncomReport != null : "fx:id=\"IncomReport\" was not injected: check your FXML file 'BranchManagerReports.fxml'.";
        assert OrderReport != null : "fx:id=\"OrderReport\" was not injected: check your FXML file 'BranchManagerReports.fxml'.";

    }

}
