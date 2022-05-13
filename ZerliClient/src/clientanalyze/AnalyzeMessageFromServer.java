package clientanalyze;

import javax.swing.JOptionPane;
import java.util.ArrayList;

import communication.Message;
import communication.MessageAnswer;
import javafx.application.Platform;
import logic.Item;
import logic.User;
import logic.Order;

public class AnalyzeMessageFromServer {
	private static Object res;

	public static void Message(Object msg) throws Exception {
		Message receivedMessage = (Message) msg;

		switch (receivedMessage.getMessageType()) {

		case CONFIRM_IP:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.NOT_SUCCEED) {
				JOptionPane.showMessageDialog(null, "Already Logged in", "Error", JOptionPane.ERROR_MESSAGE);
				Platform.exit();
				System.exit(0);
			} else {
				if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
					break;
				}
			}
			break;

		case EXIT:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED)
				break;
			else if (receivedMessage.getMessageAnswer() == MessageAnswer.NOT_SUCCEED) // Not implemented yet
				break;

		case LOGIN:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();
				break;
			} else if (receivedMessage.getMessageAnswer() == MessageAnswer.NOT_SUCCEED) {
				res = null;
				JOptionPane.showMessageDialog(null, "Wrong username or password", "Error", JOptionPane.ERROR_MESSAGE);
				break;
			}
			break;

		case LOGOUT:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED)
				return;
			else // Not implemented yet
				break;

		case UPDATE:

			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED)
				JOptionPane.showMessageDialog(null, "Updated Successfuly!", "Update", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Could not update!", "Update", JOptionPane.INFORMATION_MESSAGE);

			break;

		case GET_ORDERS:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();
			} else {
				res = null;
			}
			return;

		case GET_SELECTED_ORDER:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = (String) receivedMessage.getMessageData();
			} else {
				res = null;
			}
			return;
		case GET_USERS:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();
			}

		case GET_ITEMS:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;

		case GET_ACCOUNT_DETAILS:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;

		default:
			break;

		}

	}

	@SuppressWarnings("unchecked")
	public static Object getData() {
		if (res instanceof String)
			return (String) res;
		if (res instanceof User)
			return (User) res;
		if (res instanceof ArrayList<?>) {
			try {
			if (((ArrayList<?>) res).get(0) instanceof User)
				return (ArrayList<User>) res;
			if (((ArrayList<?>) res).get(0) instanceof Order)
				return (ArrayList<Order>) res;
			if (((ArrayList<?>) res).get(0) instanceof Item)
				return (ArrayList<Item>) res;
			if (((ArrayList<?>) res).get(0) instanceof String)
				return (ArrayList<String>) res;
			}
			catch (IndexOutOfBoundsException e) {
			};
		}
		return null;

	}

}
