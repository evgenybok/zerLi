package main;

import controllers.IPScreenController;
import javafx.application.Application;
import javafx.stage.Stage;
/**
 * @author Evgeny
 *This class opens the client screen and allows access to the system
 */
public class ClientUI extends Application {

    /**
     * Open the IP configuration screen.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        IPScreenController ipScreen = new IPScreenController();
        ipScreen.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
