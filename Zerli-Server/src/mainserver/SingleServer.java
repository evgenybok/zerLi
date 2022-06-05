package mainserver;

import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.JFrame;

/**
 * @author Evgeny
 * Singleton for the server to create only one instance at a time.
 *
 */
public class SingleServer extends JFrame implements Runnable {
	// Server singleton
	private static final long serialVersionUID = 1L;
	private static final int PORT = 5555;

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		SingleServer singleServer = new SingleServer();
	}

	public SingleServer() {
		super();
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			System.exit(1);
			e.printStackTrace();
		}
	}
}
