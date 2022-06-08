package controllers;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mainserver.EchoServer;
import mainserver.ServerConnection;
import query.ConnectToDB;
import query.ConnectToExternalDB;
import query.GetOrderQuery;
import query.Query;
import query.ReportQuery;
import query.StoresQuery;
import query.complaintQuery;

/**
 * @author Evgeny Server screen, connects to the SQL DB and allows import of
 *         external DB, opens server socket.
 */
@SuppressWarnings({ "unused", "serial" })
public class ServerController extends JFrame {
	Runnable r = new ReportTime();
	public static boolean flag = true;
	Thread t = new Thread(r);
	Runnable r2 = new QuartReport();
	public static boolean flag2 = true;
	Thread t2 = new Thread(r2);
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
	 * 
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
	 * 
	 * @param console
	 */
	public void setConsole(TextArea console) {
		this.console = console;
	}

	/**
	 * Console Getter
	 * 
	 * @return
	 */
	public TextArea getConsole() {
		return console;
	}

	/**
	 * IP getter
	 * 
	 * @throws UnknownHostException
	 */
	public void getIP() throws UnknownHostException {
		InetAddress ia = InetAddress.getLocalHost();
		String str = ia.getHostAddress();
		IP.setText(str);
	}

	/**
	 * Opens socket and the server
	 * 
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
		try {
			t.start();
			t2.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Starts the server screen
	 * 
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
	 * 
	 * @param msg
	 */
	public void addText(String msg) {
		String timeStamp = new SimpleDateFormat("[dd.MM.yyyy]  [HH:mm:ss]  ").format(Calendar.getInstance().getTime());
		if (console != null)
			Platform.runLater(() -> console.appendText(timeStamp + msg + "\n"));
	}

	/**
	 * Disconnects the server and closes socket
	 * 
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
		importData.setDisable(true);

	}

	/**
	 * Get info from external database
	 * 
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
			addText("Successfully imported the external database");
		} catch (Exception e) {
			addText("No data in the externalDB!");
		}
		;

	}

	/**
	 * Closes server screen
	 * 
	 * @param event
	 */
	@FXML
	void CloseApp(MouseEvent event) {
		stage = (Stage) MainStage.getScene().getWindow();
		stage.close();
		System.exit(1);

	}

	/**
	 * @author Evgeny Running thread to create report every month automatically
	 */
	class ReportTime implements Runnable {
		@Override
		public void run() {
			while (true) {
				Date today = new Date();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(today);
				calendar.add(Calendar.MONTH, 1);
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				calendar.add(Calendar.DATE, -1);
				Date lastDayOfMonth = calendar.getTime();
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String Today = sdf.format(today);
				String lastdayOfMonth = sdf.format(lastDayOfMonth);
				if (!Today.equals(lastdayOfMonth)) {
					try {
						Thread.sleep(1000 * 86400);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					flag = true;
					if (flag == true) {
						flag = false;
						String date[] = Today.split("-", 3);
						ArrayList<String> list = StoresQuery.GetStoresIdinList();
						for (int i = 0; i < list.size(); i++) {
							ReportQuery.CreateIncomeReports(list.get(i), date[1], date[0]);
						}
						// here we need to create the other monthly reports
						for (int i = 0; i < list.size(); i++) {
							ReportQuery.CreateOrderReports(list.get(i), date[1], date[0]);
						}
					}

					try {
						Thread.sleep(1000 * 86400);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// return;
				}
			}
		}

	}

	/**
	 * @author Evgeny Running thread to create quarterly report every month
	 *         automatically
	 */
	class QuartReport implements Runnable {

		@Override
		public void run() {

			while (true) {
				Date t = new Date();
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String today = sdf.format(t);
				String str[] = today.split("-", 3);
				String quar = str[1] + "-" + str[2];
				String quar2 = "03-31";
				String qurter = null;
				if (str[1].equals("01") || str[1].equals("02") || str[1].equals("03"))
					qurter = "01";
				if (str[1].equals("04") || str[1].equals("05") || str[1].equals("06"))
					qurter = "02";
				if (str[1].equals("07") || str[1].equals("08") || str[1].equals("09"))
					qurter = "03";
				if (str[1].equals("10") || str[1].equals("11") || str[1].equals("12"))
					qurter = "04";
				if (quar.equals("03-31") || quar.equals("06-30") || quar.equals("09-30") || quar.equals("12-30")) {
					flag2 = true;
					if (flag2) {
						flag2 = false;
						ArrayList<String> list = StoresQuery.GetStoresIdinList();
						for (int i = 0; i < list.size(); i++) {
							try {
								ReportQuery.CreateComplaintQuarterReports(list.get(i), quar, str[0]);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}

					try {
						Thread.sleep(1000 * 86400);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						Thread.sleep(1000 * 86400);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		}

	}

}
