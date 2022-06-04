
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
    void btnIncome(MouseEvent event) throws IOException {

        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/IncomeReport.fxml")));
        Scene scene = new Scene(parent);
        Stage IncomeReports = (Stage) ((Node) event.getSource()).getScene().getWindow();
        IncomeReports.setTitle("IncomeReports");
        IncomeReports.setScene(scene);
        IncomeReports.show();
        IncomeReports.centerOnScreen();


    }

    @FXML
    void btnOrders(MouseEvent event) {

    }

    @FXML
    void initialize() {

    }

}
