package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import clientanalyze.AnalyzeMessageFromServer;
import communication.Message;
import communication.MessageType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.SingleWorker;

/**
 * @author Evgeny
 * Updates the role of workers, only the manager can access this screen.
 */
public class EditWorkerRoleController {

	@FXML
	private Text userName;

	@FXML
	private Text accountType;

	@FXML
	private Button Back;

	@FXML
	private TextField IdText;

	@FXML
	private ImageView Search;

	@FXML
	private Label UserID;

	@FXML
	private Label FirstName;

	@FXML
	private Label LastName;

	@FXML
	private Label Action;

	@FXML
	private VBox UsersLayout;
	
    @FXML
    private ImageView avatarImg;

	/**
	 * Returns the user back to the branch manager main screen.
	 * @param event
	 */
	@FXML
	void btnBack(MouseEvent event) {
		((Node) event.getSource()).getScene().getWindow().hide();
		try {
			Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/branchManager.fxml")));
			parent.getStylesheets().add("css/styleNew.css");
			Scene scene = new Scene(parent);
			Stage managerScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
			managerScreen.setTitle("Branch Manager Screen");
			managerScreen.setScene(scene);
			managerScreen.show();
			managerScreen.centerOnScreen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Searches for a specific worker using worker ID number
	 * @param event
	 */
	@SuppressWarnings("unchecked")
	@FXML
	void btnSearch(MouseEvent event) {
		ArrayList<SingleWorker> workers = new ArrayList<>();
		String idSearch = IdText.getText();
		UsersLayout.getChildren().clear();
		if (idSearch.isEmpty()) {
			initialize();
		}
		try {
			chat.accept(new Message(MessageType.GET_WORKERS, idSearch));
			if (AnalyzeMessageFromServer.getData().equals(null)) // Incorrect username / password
				return;
		} catch (Exception e) {
			return;
		}
		workers = (ArrayList<SingleWorker>) AnalyzeMessageFromServer.getData();
		try {
			for (int i = 0; i < workers.size(); i++) {
				if(workers.get(i).getID().equals(idSearch))
				{
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/SingleWorkerScreen.fxml"));
				HBox hBox = fxmlLoader.load();
				SingleWorkerController singleWorkerController = fxmlLoader.getController();
				singleWorkerController.setData(workers.get(i));
				UsersLayout.getChildren().add(hBox);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Initializes data shown on screen.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	void initialize() {
		Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
		avatarImg.setImage(personImage);
		userName.setText(LoginScreenController.user.getUsername());
		ArrayList<SingleWorker> workers = new ArrayList<>();
		chat.accept(new Message(MessageType.GET_WORKERS, null));
		workers = (ArrayList<SingleWorker>) AnalyzeMessageFromServer.getData();
		try {
			if(workers!=null)
			{
			for (int i = 0; i < workers.size(); i++) {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/fxml/SingleWorkerScreen.fxml"));
					HBox hBox = fxmlLoader.load();
					SingleWorkerController singleWorkerController = fxmlLoader.getController();
					singleWorkerController.setData(workers.get(i));
					UsersLayout.getChildren().add(hBox);
			}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
