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

public class CatalogTypeScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button preMade;

    @FXML
    private Button selfAss;

    @FXML
    private Button back;

    @FXML
    private Button checkout;


    @FXML
    void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage LoginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		LoginStage.setTitle("Customer Screen");
		LoginStage.setScene(scene);
		LoginStage.show();
    }

    @FXML
    void btnCheckout(MouseEvent event) {

    }

    @FXML
    void btnPreMade(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/PreMadeItems.fxml")));
		Scene scene = new Scene(parent);
		Stage LoginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		LoginStage.setTitle("Catalog Type Screen");
		LoginStage.setScene(scene);
		LoginStage.show();
    }

    @FXML
    void btnSelfAss(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/SelfAssemblyItems.fxml")));
		Scene scene = new Scene(parent);
		Stage LoginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		LoginStage.setTitle("Catalog Type Screen");
		LoginStage.setScene(scene);
		LoginStage.show();
    }

    @FXML
    void initialize() {
        assert preMade != null : "fx:id=\"preMade\" was not injected: check your FXML file 'CatalogTypeScreen.fxml'.";
        assert selfAss != null : "fx:id=\"selfAss\" was not injected: check your FXML file 'CatalogTypeScreen.fxml'.";
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'CatalogTypeScreen.fxml'.";
        assert checkout != null : "fx:id=\"checkout\" was not injected: check your FXML file 'CatalogTypeScreen.fxml'.";

    }
}
