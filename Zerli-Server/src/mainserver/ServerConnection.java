package mainserver;

import java.io.IOException;

import controllers.ServerController;

/***
 * ServerConnection this class handle connection to server
 *
 */
public class ServerConnection {
	final public static int DEFAULT_PORT = 5555;
	public static EchoServer server;

	/**
	 * start the server for listening to client
	 * 
	 * @param userport
	 * @param serverController
	 */
	public static void startServer(String userport, ServerController serverController) {
		int port = 0;
		try {
			port = Integer.parseInt(userport);// convert string to integer
		} catch (Throwable t) {
			port = DEFAULT_PORT;
		}
		if (server == null) {
			server = new EchoServer(port);
			EchoServer.serverController = serverController;
		}
		if (!server.isListening()) {
			try {

				server.listen();
			} catch (Exception ex) {
				System.out.println("ERROR - Could not listen for clients!");
			}
		}
	}

	/**
	 * This method stop the server and all connections with clients
	 * 
	 * @param serverController the gui controller
	 */
	public static void stopServer(ServerController serverController) {
		if (server == null) {
			serverController.addText("The server already OFF");
			return;
		}
		if (server.isListening()) {
			try {
				server.close(); // close the server and all connection with the clients
			} catch (IOException e) {
				e.printStackTrace();
			}
			server.stopListening();
		}
	}
}