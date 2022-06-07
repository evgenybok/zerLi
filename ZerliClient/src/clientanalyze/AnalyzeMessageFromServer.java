package clientanalyze;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import communication.Message;
import communication.MessageAnswer;
import logic.Account;
import logic.Complain;
import logic.Item;
import logic.SingleComplaint;
import logic.SingleDelivery;
import logic.SingleManageOrder;
import logic.SingleOrder;
import logic.SurveyAnswer;
import logic.SingleSelfDelivery;
import logic.SingleUser;
import logic.SingleWorker;
import logic.User;

/**
 * @author Evgeny This class Analyses the messages sent from the server and
 *         handles them accordingly.
 *
 */
public class AnalyzeMessageFromServer {
	private static Object res;

	/**
	 * Based on case type in msg, uses switch case to handle the case and sends a
	 * response back to the client if needed.
	 * 
	 * @param msg - Message class, contains the type,answer and data of the message
	 *            received from the server.
	 * @throws Exception
	 */
	public static void Message(Object msg) throws Exception {
		Message receivedMessage = (Message) msg;

		switch (receivedMessage.getMessageType()) {

		case CONFIRM_IP:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.NOT_SUCCEED) {
				res = "Failed";
			} else {
				res = "Success";
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
				if (receivedMessage.getMessageData().equals("Wrong")) {
					JOptionPane.showMessageDialog(null, "Wrong username or password!", "Error",
							JOptionPane.ERROR_MESSAGE);
					res = null;
				} else if (receivedMessage.getMessageData().equals("Logged In")) {
					JOptionPane.showMessageDialog(null, "Already Logged in!", "Error", JOptionPane.ERROR_MESSAGE);
					res = null;
				} else
					res = null;
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

		case CANCEL_ORDER:
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

		case UPDATE_CATALOG:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;

		case ADD_NEW_ITEM:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;

		case DELETE_ITEM:
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
		case GET_ORDER_BY_ID:
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
		case UPDATE_USED_REFUND:
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
		case GET_USERID_BY_ORDERID:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case INSERT_NEW_COMPLAIN:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
		case GET_SURVEY_NUMBER:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case INSERT_NEW_SURVEY:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();
			} else {
				res = null;
			}
			return;
		case CHECK_EXIST_QOMPLAIN:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case CHECK_ORDER_BY_USERID:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case CHECK_IF_USERNAME_EXIST:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case INSERT_NEW_USER:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case INSERT_NEW_ACCOUNT:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case GET_COMPLAINTS:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case GET_COMPLAINT_BY_ID:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case UPDATE_REFUND:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case UPDATE_STATUS_COMPLAINT:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case GET_SINGLE_DELIVERY:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;

		case GET_STORE_ORDERS:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case GET_SINGLE_DELIVERY_BY_STORE_ID:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case GET_SINGLE_DELIVERY_BY_ORDER_ID:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case UPDATE_DELIVERY_STATUS:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case INSERT_TO_DELIVERY_TABLE:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case UPDATE_REFUND_BY_ORDERID:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case VIEW_SELF_DELIVERY_DETAILS:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;

		case GET_SURVEY_ANSWERS:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;

		case GET_STORE_ID_BY_WORKER_ID:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;

		case GRAPH_STATISTICS:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;

		case INCOME_REPORT:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;

		case GET_STORE_NAME_BY_ID:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;

		case SAVE_ANALYSIS:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case GET_USERS_BY_ID:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case GET_MANAGER_ORDERS:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case GET_ORDER_BY_ORDER_ID:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case GET_USER_BY_USER_ID:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case UPDATE_STATUS_BY_MANAGER:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case UPDATE_ORDER_STATUS_BY_MANAGER:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case CANCEL_ORDER_STATUS_BY_MANAGER:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case UPDATE_ORDERS_AMOUNT:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case GET_WORKERS:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();
			} else {
				res = null;
			}
			return;
		case UPDATE_ROLE_BY_MANAGER:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;

		case GET_STORE_ID_BY_ORDER_ID:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
			
		case GET_ALL_COMPLAINTS:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();
				
			} else {
				res = null;
			}
			return;
			
		case UPDATE_REMINDER_FOR_HANDLER:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();
				
			} else {
				res = null;
			}
			return;
		case  GET_SURVEY_QUS_BY_SURVEYNUM:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();
			} else {
				res = null;
			}
			return;
		case  UPDATE_SURVEY_ANS:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case COMPLAINT_REPORT:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;

		case COMPLAINT_GRAPH_STATISTICS:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;

		case ORDERS_REPORT:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;

		case ORDER_GRAPH_STATISTICS:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;

		case INCOME_QUARTER_GRAPH:
			if (receivedMessage.getMessageAnswer() == MessageAnswer.SUCCEED) {
				res = receivedMessage.getMessageData();

			} else {
				res = null;
			}
			return;
		case GET_SURVEY_NUMBERS_COMBO:
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
		if (res instanceof Complain)
			return (Complain) res;
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
				if (((ArrayList<?>) res).get(0) instanceof Complain)
					return (ArrayList<Complain>) res;
				if (((ArrayList<?>) res).get(0) instanceof SingleComplaint)
					return (ArrayList<SingleComplaint>) res;
				if (((ArrayList<?>) res).get(0) instanceof SingleDelivery)
					return (ArrayList<SingleDelivery>) res;
				if (((ArrayList<?>) res).get(0) instanceof SurveyAnswer)
					return (ArrayList<SurveyAnswer>) res;
				if (((ArrayList<?>) res).get(0) instanceof SingleSelfDelivery)
					return (ArrayList<SingleSelfDelivery>) res;
				if (((ArrayList<?>) res).get(0) instanceof SingleUser)
					return (ArrayList<SingleUser>) res;
				if (((ArrayList<?>) res).get(0) instanceof SingleManageOrder)
					return (ArrayList<SingleManageOrder>) res;
				if (((ArrayList<?>) res).get(0) instanceof SingleWorker)
					return (ArrayList<SingleWorker>) res;

			} catch (IndexOutOfBoundsException e) {
				return null;
			}
			;
		}
		return null;

	}

}
