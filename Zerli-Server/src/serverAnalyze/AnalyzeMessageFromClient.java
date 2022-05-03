package serverAnalyze;

import communication.Message;
import communication.MessageAnswer;
import communication.MessageType;
import controllers.ConnectedClientsController;
import ocsf.server.ConnectionToClient;
import query.Query;

public class AnalyzeMessageFromClient {

	public static Message parsing(Message receivedMessage, ConnectionToClient client) {
		switch (receivedMessage.getMessageType()) {

		case CONFIRM_IP:
			ConnectedClientsController.addClient(client, receivedMessage);
			return new Message(MessageType.CONFIRM_IP, receivedMessage.getMessageAnswer(), null);

		case EXIT:
			ConnectedClientsController.editClient(client, receivedMessage);
			return new Message(MessageType.EXIT, receivedMessage.getMessageAnswer(), null);

		case LOGIN:
			if (Query.Login((String) receivedMessage.getMessageData(), client))
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			else
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
			return new Message(MessageType.LOGIN, receivedMessage.getMessageAnswer(), null);

		case LOGOUT:
			if (Query.Disconnect(client))
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			else
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
			return new Message(MessageType.LOGOUT, receivedMessage.getMessageAnswer(), null);

		case UPDATE:
			if (Query.Update((String) receivedMessage.getMessageData()))
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			else
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
			return new Message(MessageType.UPDATE, receivedMessage.getMessageAnswer(), null);

		case GET_ORDERS:
			String orders = Query.GetOrders();
			if (orders.equals("ERROR")) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				return new Message(MessageType.GET_ORDERS, receivedMessage.getMessageAnswer(), null); // Not implemented
																										// yet
			} else {
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(orders);
				return new Message(MessageType.GET_ORDERS, receivedMessage.getMessageAnswer(),
						receivedMessage.getMessageData());
			}
		case GET_SELECTED_ORDER:
			String order = Query.getSelectedOrder((int) receivedMessage.getMessageData());
			if (order.equals("ERROR")) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				return new Message(MessageType.GET_SELECTED_ORDER, receivedMessage.getMessageAnswer(), null); // Not
																												// implemented
																												// yet
			} else {
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(order);
				return new Message(MessageType.GET_SELECTED_ORDER, receivedMessage.getMessageAnswer(),
						receivedMessage.getMessageData());
			}

		default:
			return new Message(MessageType.ERROR, null);
		}
	}

}
