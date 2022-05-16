package controllers;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddGreetingController {

    @FXML
    private ImageView GreetingImage;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtGreeting;

    @FXML
    private Button done;

    @FXML
    void btnDone(MouseEvent event) {
        Stage stage = (Stage) done.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        Image greetingimage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Greeting.jpg")));
        GreetingImage.setImage(greetingimage);

    }
}
