package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.SingleDelivery;
import logic.SingleManageOrder;
import logic.SingleUser;

public class EditUsersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Action;

    @FXML
    private Button Back;

    @FXML
    private Label FirstName;

    @FXML
    private TextField IdText;

    @FXML
    private Label LastName;

    @FXML
    private ImageView Search;

    @FXML
    private Label Status;

    @FXML
    private Label UserID;

    @FXML
    private VBox UsersLayout;

    @FXML
    private Text accountType;

    @FXML
    private Text userName;

    @FXML
    void btnBack(MouseEvent event) throws IOException {
      	((Node) event.getSource()).getScene().getWindow().hide();
    		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/branchManager.fxml")));
    		parent.getStylesheets().add("css/styleNew.css");
    		Scene scene = new Scene(parent);
    		Stage managerScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
    		managerScreen.setTitle("Branch Manager Screen");
    		managerScreen.setScene(scene);
    		managerScreen.show();
    		managerScreen.centerOnScreen();
    }

    @FXML
    void btnSearch(MouseEvent event) {
    	ArrayList<SingleUser> order = new ArrayList<>();
    	String idSearch = IdText.getText();
    	UsersLayout.getChildren().clear();
    	if(idSearch.isEmpty())
    	{
    		initialize();
    	}
    	try {
    		chat.accept(new Message(MessageType.GET_USER_BY_USER_ID, idSearch));
			if (AnalyzeMessageFromServer.getData().equals(null)) // Incorrect username / password
				return;
    	}catch(Exception e) {
    		return;
    	}
    	order = (ArrayList<SingleUser>) AnalyzeMessageFromServer.getData();
    	try {
			for (int i = 0; i < order.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/SingleUser.fxml"));
				HBox hBox = fxmlLoader.load();
				SingleUserController singleUserController = fxmlLoader.getController();
				singleUserController.setData(order.get(i));
				UsersLayout.getChildren().add(hBox);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
        InsertToTable();
    }
    public void InsertToTable()
    {
    	ArrayList<SingleUser> list= new ArrayList<>();
    	chat.accept(new Message(MessageType.GET_USERS_BY_ID,null));
    	list = (ArrayList<SingleUser>) AnalyzeMessageFromServer.getData();
		try {
			for (int i = 0; i < list.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/SingleUser.fxml"));
				HBox hBox = fxmlLoader.load();
				SingleUserController singleUserController = fxmlLoader.getController();
				singleUserController.setData(list.get(i));
				UsersLayout.getChildren().add(hBox);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
