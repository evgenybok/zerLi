package main;

import controllers.IPScreenController;
import javafx.application.Application;
import javafx.stage.Stage;
//poop
//shaked
//Projecct mzdayen
public class ClientUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        IPScreenController ipScreen = new IPScreenController();
        ipScreen.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
