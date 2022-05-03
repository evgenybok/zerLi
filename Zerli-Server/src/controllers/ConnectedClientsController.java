package controllers;

import communication.Message;
import communication.MessageAnswer;
import ocsf.server.ConnectionToClient;

public class ConnectedClientsController {

    public static void addClient(ConnectionToClient client, Message message) {
        ClientDoc clientDoc = new ClientDoc(client);
        if (!ServerController.clients.contains(clientDoc))
            ServerController.clients.add(clientDoc);
        else {
            int indexOfClient = ServerController.clients.indexOf(clientDoc);
            if (ServerController.clients.get(indexOfClient).getStatus().equals("Disconnected")) {
                clientDoc.setStatus("Connected");
                ServerController.clients.remove(indexOfClient);
                ServerController.clients.add(clientDoc);
            } else {	
                message.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
                return;
            }
        }
        message.setMessageAnswer(MessageAnswer.SUCCEED);
    }

    public static void editClient(ConnectionToClient client, Message message) {
        ClientDoc clientDoc = new ClientDoc(client);
        if (ServerController.clients.contains(clientDoc)) {
            int indexOfClient = ServerController.clients.indexOf(clientDoc);
            if (ServerController.clients.get(indexOfClient).getStatus().equals("Connected")) {
                clientDoc.setStatus("Disconnected");
                ServerController.clients.remove(indexOfClient);
                ServerController.clients.add(clientDoc);
            }

        }
        message.setMessageAnswer(MessageAnswer.SUCCEED);
    }

}