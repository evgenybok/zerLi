package mainserver;

import controllers.ServerController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	public static Stage mainStage;

	@Override

	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		ServerController controller = new ServerController();
		controller.start(primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
