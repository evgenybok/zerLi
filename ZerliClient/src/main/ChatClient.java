package main;

import java.io.IOException;

import clientanalyze.AnalyzeMessageFromServer;
import common.ChatIF;
import communication.Message;
import ocsf.client.AbstractClient;

public class ChatClient extends AbstractClient {

	ChatIF clientUI;
	public static boolean waitingForResponse = false;
	public static boolean Success = false;

	public ChatClient(String host, int port, ChatIF clientUI) throws IOException {
		super(host, port);
		this.clientUI = clientUI;
		try {
			openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		waitingForResponse = false;
		if (msg instanceof Message) {
			try {
				AnalyzeMessageFromServer.Message(msg);
				
			} catch (Exception e) {
				System.out.println("Failed To Send Message To Server");
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method handles all data coming from the UI
	 *
	 * @param message The message from the UI.
	 */
	public void handleMessageFromClientUI(Object message) {
		try {
			waitingForResponse = true;
			openConnection();
			sendToServer(message);
			// wait for response
			while (waitingForResponse) {
				try {
				Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			clientUI.display("Could not send message to Server:Terminate Client," + e);
			quit();
		}
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}
}
//End of ChatClient class
