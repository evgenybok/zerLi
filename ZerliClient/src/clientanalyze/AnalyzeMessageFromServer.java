package clientanalyze;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import communication.Message;
import communication.MessageAnswer;
import logic.Account;
import logic.Item;
import logic.SingleOrder;
import logic.User;

public class AnalyzeMessageFromServer {
	private static Object res;

	public static void Message(Object msg) throws Exception {
		Message receivedMessage = (Message) msg;

		switch (receivedMessage.getMessageType()) {

		case CONFIRM_IP:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.NOT_SUCCEED) {
				res="Failed";
			} else {
				res="Success";
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

		case GET_PREMADE_ITEMS:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
			
		case GET_SELFASSEMBLY_ITEMS:
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
		case GET_STORE_NAME:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case INSERT_NEW_ORDER:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case GET_STORE_ID:
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
			if (((ArrayList<?>) res).get(0) instanceof SingleOrder)
				return (ArrayList<SingleOrder>) res;
			if (((ArrayList<?>) res).get(0) instanceof Item)
				return (ArrayList<Item>) res;
			if (((ArrayList<?>) res).get(0) instanceof String)
				return (ArrayList<String>) res;
			if (((ArrayList<?>) res).get(0) instanceof Account)
				return (ArrayList<Account>) res;
			}
			catch (IndexOutOfBoundsException e) {
			};
		}
		return null;

	}

}
