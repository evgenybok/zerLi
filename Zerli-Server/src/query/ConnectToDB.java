
package query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import controllers.ServerController;
import mainserver.EchoServer;


/**
 * @author Evgeny
 * this class contains the connection to the DB.
 *
 */
public class ConnectToDB extends ServerController{
	private static final long serialVersionUID = -2568961513728853135L;
	public static Connection conn;

	public static Connection connect(String UserName, String Password,String connection) throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			EchoServer.serverController.addText("Driver definition succeed");
		} catch (Exception ex) {
			//handle the error
            EchoServer.serverController.addText("Driver definition failed");

		}

		try {
			conn = DriverManager.getConnection(connection, UserName,
					Password);

			EchoServer.serverController.addText("SQL Connection succeed");
			EchoServer.serverController.addText("Server Connected");


		} catch (SQLException ex) {
			EchoServer.serverController.addText("SQLException: " + ex.getMessage());
			EchoServer.serverController.addText("SQLState: " + ex.getSQLState());
			EchoServer.serverController.addText("VendorError: " + ex.getErrorCode());
			EchoServer.serverController.addText("Error! Could't listen for clients!");
			throw new Exception();
		}
		return conn;
		
	}


}

