package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class CEOScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button viewReports;

    @FXML
    private Button viewQuaReports;

    @FXML
    private Label lblUserPortal;

    @FXML
    private Label lblStartMsg;

    @FXML
    private Text userName;

    @FXML
    private Text accountStatus;

    @FXML
    private Button Logout;

    @FXML
    private Label lblStatus;

    @FXML
    private Label accountType;

    @FXML
    void btnLogout(MouseEvent event) {

    }

    @FXML
    void btnViewQuaReports(MouseEvent event) {

    }

    @FXML
    void btnViewReports(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert viewReports != null : "fx:id=\"viewReports\" was not injected: check your FXML file 'CEOScreen.fxml'.";
        assert viewQuaReports != null : "fx:id=\"viewQuaReports\" was not injected: check your FXML file 'CEOScreen.fxml'.";
        assert lblUserPortal != null : "fx:id=\"lblUserPortal\" was not injected: check your FXML file 'CEOScreen.fxml'.";
        assert lblStartMsg != null : "fx:id=\"lblStartMsg\" was not injected: check your FXML file 'CEOScreen.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'CEOScreen.fxml'.";
        assert accountStatus != null : "fx:id=\"accountStatus\" was not injected: check your FXML file 'CEOScreen.fxml'.";
        assert Logout != null : "fx:id=\"Logout\" was not injected: check your FXML file 'CEOScreen.fxml'.";
        assert lblStatus != null : "fx:id=\"lblStatus\" was not injected: check your FXML file 'CEOScreen.fxml'.";
        assert accountType != null : "fx:id=\"accountType\" was not injected: check your FXML file 'CEOScreen.fxml'.";

    }
}
