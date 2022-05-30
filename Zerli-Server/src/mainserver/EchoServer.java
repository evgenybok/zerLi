package mainserver;

import java.io.IOException;

import communication.Message;
import controllers.ServerController;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import serverAnalyze.AnalyzeMessageFromClient;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */

public class EchoServer extends AbstractServer {
    // Class variables *************************************************

    /**
     * The default port to listen on.
     */
    final public static int DEFAULT_PORT = 5555;
    public static ServerController serverController;
    private Message message;



    // Constructors ****************************************************

    /**
     * Constructs an instance of the echo server.
     *
     * @param port The port number to connect on.
     */
    public EchoServer(int port) {
        super(port);
    }

    // Instance methods ************************************************

    /**
     * This method handles any messages received from the client.
     *
     * @param msg    The message received from the client.
     * @param client The connection from which the message originated.
     */
    public void handleMessageFromClient(Object msg, ConnectionToClient client) {
        if (msg instanceof Message) {
            message = AnalyzeMessageFromClient.parsing((Message) msg, client);
            try {
                client.sendToClient(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("Not Handled");

    }


    // Class methods ***************************************************

    /**
     * This method is responsible for the creation of the server instance (there is
     * no UI in this phase).
     *
     * @param args The port number to listen on. Defaults to 5555 if no argument
     *             is entered.
     */
    public static void main(String[] args) {
        int port = 0; // Port to listen on

        try {
            port = Integer.parseInt(args[0]); // Get port from command line
        } catch (Throwable t) {
            port = DEFAULT_PORT; // Set port to 5555
        }

        EchoServer sv = new EchoServer(port);

        try {
            sv.listen(); // Start listening for connections
        } catch (Exception ex) {
        	serverController.addText("ERROR - Could not listen for clients!");
        }
    }
    

}
//End of EchoServer class
