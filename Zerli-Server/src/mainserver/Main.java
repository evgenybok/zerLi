package mainserver;

import controllers.ServerController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	public static Stage mainStage;
	@Override

	public void start(Stage primaryStage) {
		mainStage = primaryStage;
		ServerController controller = new ServerController();
		//Singleton for the server
		SingleServer singleServer=new SingleServer();
		try {
			controller.start(primaryStage);
		} catch (Exception e) {
			System.out.println("Error,exiting program");
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}