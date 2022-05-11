package serverAnalyze;

import java.util.ArrayList;

import communication.Message;
import communication.MessageAnswer;
import communication.MessageType;
import controllers.ConnectedClientsController;
import logic.Order;
import logic.User;
import ocsf.server.ConnectionToClient;
import query.Query;

public class AnalyzeMessageFromClient {

	public static Message parsing(Message receivedMessage, ConnectionToClient client) {
		User user;
		ArrayList<Order> Orders;
		ArrayList<User> UsersArr;
		switch (receivedMessage.getMessageType()) {

		case CONFIRM_IP:
			ConnectedClientsController.addClient(client, receivedMessage);
			return new Message(MessageType.CONFIRM_IP, receivedMessage.getMessageAnswer(), null);

		case EXIT:
			ConnectedClientsController.editClient(client, receivedMessage);
			return new Message(MessageType.EXIT, receivedMessage.getMessageAnswer(), null);

		case LOGIN:
			user =Query.Login((User)receivedMessage.getMessageData());
			if (user.isLoggedIn()) {
				receivedMessage.setMessageData(user);
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			} else
			{
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
			}
			return new Message(MessageType.LOGIN, receivedMessage.getMessageAnswer(),receivedMessage.getMessageData());
			

		case LOGOUT:
            if (Query.Disconnect((User) receivedMessage.getMessageData()))
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
			Orders = Query.GetOrders();
		/*	if (orders.equals("ERROR")) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				return new Message(MessageType.GET_ORDERS, receivedMessage.getMessageAnswer(), null); // Not implemented
																										// yet
			} else {*/
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(Orders);
				return new Message(MessageType.GET_ORDERS, receivedMessage.getMessageAnswer(),
						receivedMessage.getMessageData());
		//	}
		case GET_SELECTED_ORDER:
			int ordnum=(int) receivedMessage.getMessageData();
			String order = Query.getSelectedOrder((int) receivedMessage.getMessageData());
			order = Query.getItemName(order);
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
		case GET_USERS:
            UsersArr=Query.GetUsersDB();
            receivedMessage.setMessageData(UsersArr);
            receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
            return new Message(MessageType.GET_USERS, receivedMessage.getMessageAnswer(), receivedMessage.getMessageData());

		default:
			return new Message(MessageType.ERROR, null);
		}
	}

}
