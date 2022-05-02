package controllers;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import javax.swing.JFrame;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import mainserver.EchoServer;
import mainserver.ServerConnection;
import query.ConnectToDB;
import query.Query;

@SuppressWarnings({ "unused", "serial" })
public class ServerController extends JFrame {
	private EchoServer echoServer;
	private Connection conn = null;
	public Stage stage;
	final public static int DEFAULT_PORT = 5555;

	@FXML
	private Button CloseApp;

	public Button getConnect() {
		return Connect;
	}

	public void setConnect(boolean connect) {
		Connect.setDisable(connect);
	}

	public TableView<ClientDoc> getConnectedClients() {
		return ConnectedClients;
	}

	public void setConnectedClients(TableView<ClientDoc> connectedClients) {
		ConnectedClients = connectedClients;
	}

	@FXML
	private TableView<ClientDoc> ConnectedClients;

	@FXML
	private TableColumn<ClientDoc, String> TableHost;

	@FXML
	private TableColumn<ClientDoc, String> TableIP;

	@FXML
	private TableColumn<ClientDoc, String> TableStatus;

	@FXML
	private Button Connect;

	@FXML
	private TextField DBName;

	@FXML
	private PasswordField DBPassword;

	@FXML
	private TextField DBUser;

	public Button getDisconnect() {
		return Disconnect;
	}

	public void setDisconnect(boolean disconnect) {
		Disconnect.setDisable(disconnect);
	}

	@FXML
	private Button Disconnect;

	@FXML
	private TextField IP;

	@FXML
	private HBox MainStage;

	@FXML
	private TextField Port;

	@FXML
	private TextArea console;

	public static ObservableList<ClientDoc> clients = FXCollections.observableArrayList();

	@FXML
	void initialize() throws UnknownHostException {

		this.TableIP.setCellValueFactory(new PropertyValueFactory<>("ipAddress"));
		this.TableHost.setCellValueFactory(new PropertyValueFactory<>("hostName"));
		this.TableStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		this.getConnectedClients().setItems(clients);
		getIP();

	}

	public void setConsole(TextArea console) {
		this.console = console;
	}

	public TextArea getConsole() {
		return console;
	}

	public void getIP() throws UnknownHostException {
		InetAddress ia = InetAddress.getLocalHost();
		String str = ia.getHostAddress();
		IP.setText(str);
	}

	@FXML
	void ClickOnConnect(MouseEvent event) throws UnknownHostException {
		ServerConnection.startServer(null, this);
		String username, password;
		if (DBPassword.getText().isEmpty()) {
			addText("Fill Password!");
		} else {
			username = "root";
			password = DBPassword.getText().toString();
			try {
				Connection connection = ConnectToDB.connect(username, password);
				Disconnect.setDisable(false);
				Connect.setDisable(true);
				Query.DisconnectAll();	//Disconnects all currently logged in users.
			} catch (Exception e) {
				Disconnect.setDisable(true);
				Connect.setDisable(false);
			}
			;
		}
	}

	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/controllers/server.fxml")));
		Scene scene = new Scene(root);
		Image icon = new Image("icon.png");
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("ZerLi");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void addText(String msg) {
		String timeStamp = new SimpleDateFormat("[dd.MM.yyyy]  [HH:mm:ss]  ").format(Calendar.getInstance().getTime());
		Platform.runLater(() -> console.appendText(timeStamp + msg + "\n"));
	}

	@FXML
	void ClickOnDisconnect(MouseEvent event) {
		ServerConnection.stopServer(this);
		Disconnect.setDisable(true);
		Connect.setDisable(false);
		ConnectedClients.getItems().clear();
		ConnectedClients.refresh();
		addText("Server Disconnected");

	}

	@FXML
	void CloseApp(MouseEvent event) {
		stage = (Stage) MainStage.getScene().getWindow();
		stage.close();
		System.exit(1);

	}

}
