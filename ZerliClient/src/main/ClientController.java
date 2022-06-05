package main;

import java.io.IOException;

import common.ChatIF;
import communication.Message;

/**
 * @author Evgeny
 * Sets up the connection between the client and the server
 */
public class ClientController implements ChatIF {
	public static int DEFAULT_PORT;
	ChatClient client;

	/**
	 * Connects current client to the server
	 * @param host
	 * @param port
	 */
	public ClientController(String host, int port) {
		try {
			this.client = new ChatClient(host, port, this);

		} catch (IOException exception) {
			System.out.println("Error: Can't setup connection!" + " Terminating client.");
			System.exit(1);
		}
	}

	/**
	 * Communication between the client and the server used to send/fetch data.
	 * @param str
	 */
	public void accept(Message str) {
		client.handleMessageFromClientUI(str);
	}

	/**
	 * Displays message
	 */
	public void display(String message) {
		System.out.println("> " + message);
	}
}