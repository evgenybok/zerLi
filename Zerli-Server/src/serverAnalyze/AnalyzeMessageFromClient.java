package serverAnalyze;

import java.sql.SQLException;
import java.util.ArrayList;

import communication.Message;
import communication.MessageAnswer;
import communication.MessageType;
import controllers.ConnectedClientsController;
import logic.Account;
import logic.Complain;
import logic.Item;
import logic.Order;
import logic.SingleOrder;
import logic.Survey;
import logic.User;
import ocsf.server.ConnectionToClient;
import query.AccountDetailsQuery;
import query.GetOrderQuery;
import query.Query;
import query.StoresQuery;
import query.SurveyQuery;
import query.complaintQuery;

public class AnalyzeMessageFromClient {

	public static Message parsing(Message receivedMessage, ConnectionToClient client) {
		User user;
		String storeID;
		ArrayList<SingleOrder> Orders;
		ArrayList<User> UsersArr;
		ArrayList<Item> Items;
		ArrayList<String> data;
		ArrayList<Account> creditDetails;
		int Num;
		switch (receivedMessage.getMessageType()) {

		case CONFIRM_IP:
			ConnectedClientsController.addClient(client, receivedMessage);
			return new Message(MessageType.CONFIRM_IP, receivedMessage.getMessageAnswer(), null);

		case EXIT:
			ConnectedClientsController.editClient(client, receivedMessage);
			return new Message(MessageType.EXIT, receivedMessage.getMessageAnswer(), null);

		case LOGIN:
			user = Query.Login((User) receivedMessage.getMessageData());
			if (user.isLoggedIn()) {
				receivedMessage.setMessageData(user);
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			} else {
				receivedMessage.setMessageData(null);
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
			}
			return new Message(MessageType.LOGIN, receivedMessage.getMessageAnswer(), receivedMessage.getMessageData());

		case LOGOUT:
			if (Query.Disconnect((User) receivedMessage.getMessageData()))
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			else
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
			return new Message(MessageType.LOGOUT, receivedMessage.getMessageAnswer(), null);

		case UPDATE:
			if (GetOrderQuery.Update((String) receivedMessage.getMessageData()))
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			else
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
			return new Message(MessageType.UPDATE, receivedMessage.getMessageAnswer(), null);

		case GET_ORDERS:
			Orders = GetOrderQuery.GetOrders((String)receivedMessage.getMessageData());
			try {
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(Orders);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}

			return new Message(MessageType.GET_ORDERS, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case GET_SELECTED_ORDER:
			String order = GetOrderQuery.getSelectedOrder((int) receivedMessage.getMessageData());
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
			UsersArr = Query.GetUsersDB();
			receivedMessage.setMessageData(UsersArr);
			receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			return new Message(MessageType.GET_USERS, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case GET_PREMADE_ITEMS:
			try {
				Items = Query.GetPremadeItems();
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(Items);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			;
			return new Message(MessageType.GET_PREMADE_ITEMS, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case GET_SELFASSEMBLY_ITEMS:
			try {
				Items = Query.GetSelfAssemblyItems();
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(Items);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			;
			return new Message(MessageType.GET_PREMADE_ITEMS, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case GET_ACCOUNT_DETAILS:
			try {
				creditDetails = AccountDetailsQuery.GetAccountDetails((String) receivedMessage.getMessageData());
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(creditDetails);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			;

			return new Message(MessageType.GET_ACCOUNT_DETAILS, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case GET_STORE_NAME:
			try {
				data = StoresQuery.GetStoreName((String) receivedMessage.getMessageData());
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(data);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			;
			return new Message(MessageType.GET_STORE_NAME, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());
		case INSERT_NEW_ORDER:
			try {
			GetOrderQuery.InsertNewOrder((Order) receivedMessage.getMessageData());
			receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
		} catch (Exception e) {
			receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
			receivedMessage.setMessageData(null);
		}
		;
		case GET_STORE_ID:
			try {
				storeID= StoresQuery.GetStoreId((String) receivedMessage.getMessageData());
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(storeID);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			};
			return new Message(MessageType.GET_STORE_ID, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());
	case GET_ORDER_BY_ID:
		Orders = GetOrderQuery.GetOrderByIdAndUserId((String)receivedMessage.getMessageData());
		try {
			receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			receivedMessage.setMessageData(Orders);
		} catch (Exception e) {
			receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
			receivedMessage.setMessageData(null);
		}

		return new Message(MessageType.GET_ORDER_BY_ID, receivedMessage.getMessageAnswer(),
				receivedMessage.getMessageData());
	case GET_USERID_BY_ORDERID:
		storeID = complaintQuery.GetUserIDbyOrderNumberQuery((String)receivedMessage.getMessageData());
		try {
			receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			receivedMessage.setMessageData(storeID);
		} catch (Exception e) {
			receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
			receivedMessage.setMessageData(null);
		}

		return new Message(MessageType.GET_USERID_BY_ORDERID, receivedMessage.getMessageAnswer(),
				receivedMessage.getMessageData());
	case INSERT_NEW_COMPLAIN:
		try {
			complaintQuery.InsertNewComplain((Complain) receivedMessage.getMessageData());
		receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
	} catch (Exception e) {
		receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
		receivedMessage.setMessageData(null);
	}
	;
	case GET_SURVEY_NUMBER:
		
		String SurveyNum = SurveyQuery.getNumberOfNextSurvey();
		try {
			receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			receivedMessage.setMessageData(SurveyNum);
		} catch (Exception e) {
			receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
			receivedMessage.setMessageData(null);
		}

		return new Message(MessageType.GET_SURVEY_NUMBER, receivedMessage.getMessageAnswer(),
				receivedMessage.getMessageData());
		case INSERT_NEW_SURVEY:
			try {
				SurveyQuery.InsertNewQuery((Survey) receivedMessage.getMessageData());
			receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
		} catch (Exception e) {
			receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
			receivedMessage.setMessageData(null);
		}
		;
		//INSERT_NEW_SURVEY
		default:
			return new Message(MessageType.ERROR, null);
		}
	}

}
