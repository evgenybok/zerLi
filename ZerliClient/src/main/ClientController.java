package main;

import java.io.IOException;

import common.ChatIF;
import communication.Message;

public class ClientController implements ChatIF {
	public static int DEFAULT_PORT;
	ChatClient client;

	public ClientController(String host, int port) {
		try {
			this.client = new ChatClient(host, port, this);

		} catch (IOException exception) {
			System.out.println("Error: Can't setup connection!" + " Terminating client.");
			System.exit(1);
		}
	}

	public void accept(Message str) {
		client.handleMessageFromClientUI(str);
	}

	public void display(String message) {
		System.out.println("> " + message);
	}
}