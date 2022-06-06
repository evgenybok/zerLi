package controllers;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mainserver.EchoServer;
import mainserver.ServerConnection;
import query.ConnectToDB;
import query.ConnectToExternalDB;
import query.Query;

/**
 * @author Evgeny
 * Server screen, connects to the SQL DB and allows import of external DB, opens server socket.
 */
@SuppressWarnings({ "unused", "serial" })
public class ServerController extends JFrame {
	private EchoServer echoServer;
	private Connection conn = null;
	private Connection connExternalDB = null;
	public Stage stage;
	final public static int DEFAULT_PORT = 5555;
	private String externalDBpath = "jdbc:mysql://localhost/externaldb?serverTimezone=IST";

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

	@FXML
	private ImageView ServerImage;

	@FXML
	private Button importData;

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

	/**
	 * Initialize screen to show data
	 * @throws UnknownHostException
	 */
	@FXML
	void initialize() throws UnknownHostException {

		this.TableIP.setCellValueFactory(new PropertyValueFactory<>("ipAddress"));
		this.TableHost.setCellValueFactory(new PropertyValueFactory<>("hostName"));
		this.TableStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		this.getConnectedClients().setItems(clients);
		getIP();
		Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mainscreen.jpg")));
		ServerImage.setImage(image);

	}

	/**
	 * Console setter
	 * @param console
	 */
	public void setConsole(TextArea console) {
		this.console = console;
	}

	/**
	 * Console Getter
	 * @return
	 */
	public TextArea getConsole() {
		return console;
	}

	/**
	 * IP getter
	 * @throws UnknownHostException
	 */
	public void getIP() throws UnknownHostException {
		InetAddress ia = InetAddress.getLocalHost();
		String str = ia.getHostAddress();
		IP.setText(str);
	}

	/**
	 * Opens socket and the server
	 * @param event
	 * @throws UnknownHostException
	 */
	@FXML
	void ClickOnConnect(MouseEvent event) throws UnknownHostException {
		ServerConnection.startServer(Port.getText().toString(), this);
		String username, password, dbName;
		if (DBPassword.getText().isEmpty()) {
			addText("Fill Password!");
		} else {
			username = DBUser.getText().toString();
			password = DBPassword.getText().toString();
			dbName = DBName.getText().toString();
			try {
				conn = ConnectToDB.connect(username, password, dbName);
				Disconnect.setDisable(false);
				Connect.setDisable(true);
				DBUser.setDisable(true);
				DBPassword.setDisable(true);
				DBName.setDisable(true);
				Port.setDisable(true);
				importData.setDisable(false);
				Query.DisconnectAll(); // Disconnects all currently logged in users.
			} catch (Exception e) {
				Disconnect.setDisable(true);
				Connect.setDisable(false);
			}
			;
		}
	}

	/**
	 * Starts the server screen
	 * @param primaryStage
	 * @throws Exception
	 */
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/controllers/server.fxml")));
		Scene scene = new Scene(root);
		Image icon = new Image("icon.png");
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("ZerLi");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Prints text in server console in chosen format
	 * @param msg
	 */
	public void addText(String msg) {
		String timeStamp = new SimpleDateFormat("[dd.MM.yyyy]  [HH:mm:ss]  ").format(Calendar.getInstance().getTime());
		Platform.runLater(() -> console.appendText(timeStamp + msg + "\n"));
	}

	/**
	 * Disconnects the server and closes socket
	 * @param event
	 */
	@FXML
	void ClickOnDisconnect(MouseEvent event) {
		ServerConnection.stopServer(this);
		Disconnect.setDisable(true);
		Connect.setDisable(false);
		ConnectedClients.getItems().clear();
		ConnectedClients.refresh();
		addText("Server Disconnected");

	}

	/**
	 * Get info from external database
	 * @param event
	 */
	@FXML
	void btnImportData(MouseEvent event) {
		try {
			connExternalDB = ConnectToDB.connect(DBUser.getText().toString(), DBPassword.getText().toString(),
					externalDBpath);
			ConnectToExternalDB.connectionToDB(conn);
			ConnectToExternalDB.connectionToExternalDB(connExternalDB);
			ConnectToExternalDB.getDataFromExternalDB();
			ConnectToExternalDB.insertToZerliDB();
			console.appendText("Successfully imported the external database");
		} catch (Exception e) {
			console.appendText("No data in the externalDB!");
		}
		;

	}

	/**
	 * Closes server screen
	 * @param event
	 */
	@FXML
	void CloseApp(MouseEvent event) {
		stage = (Stage) MainStage.getScene().getWindow();
		stage.close();
		System.exit(1);

	}

}
