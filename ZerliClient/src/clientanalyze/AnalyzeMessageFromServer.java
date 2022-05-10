package clientanalyze;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import communication.Message;
import communication.MessageAnswer;
import javafx.application.Platform;
import logic.Order;

public class AnalyzeMessageFromServer {
	private static String res;


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
				res = receivedMessage.getMessageData().toString();
				break;
			} else if (receivedMessage.getMessageAnswer() == MessageAnswer.NOT_SUCCEED) {
				res = "false";
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
				//res = (String) receivedMessage.getMessageData();
				Object list2= receivedMessage.getMessageData();
			}
			else
			{
				//Not implemented yet
			}
			return;
			
		case GET_SELECTED_ORDER:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = (String) receivedMessage.getMessageData();
			}
			else 
			{
				//Not implemented yet
			}
			return;
			
		default:
			break;

		}
	}

	public static Object getData() {
		return res;
	}

}
