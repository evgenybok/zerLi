package serverAnalyze;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import communication.Message;
import communication.MessageAnswer;
import communication.MessageType;
import controllers.ConnectedClientsController;
import logic.Account;
import logic.Complain;
import logic.Item;
import logic.Order;
import logic.SingleComplaint;
import logic.SingleDelivery;
import logic.SingleManageOrder;
import logic.SingleOrder;
import logic.SingleSelfDelivery;
import logic.SingleUser;
import logic.Survey;
import logic.User;
import ocsf.server.ConnectionToClient;
import query.AccountDetailsQuery;
import query.AddNewUserQuery;
import query.CatalogQuery;
import query.DeliveryQuery;
import query.GetOrderQuery;
import query.Query;
import query.ReportQuery;
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
		ArrayList<SingleComplaint> singlecomplaint;
		ArrayList<SingleDelivery> singleDelivery;
		ArrayList<SingleSelfDelivery> singleSelfDelivery;
		ArrayList<SingleUser> singleUser;
		ArrayList<SingleManageOrder> singleManageOrder;
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
			Orders = GetOrderQuery.GetOrders((String) receivedMessage.getMessageData());
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

		case UPDATE_CATALOG:
			try {
				CatalogQuery.UpdateCatalog((Item) receivedMessage.getMessageData());
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			;
			return new Message(MessageType.UPDATE_CATALOG, receivedMessage.getMessageAnswer(), null);

		case ADD_NEW_ITEM:
			try {
				CatalogQuery.AddNewItem((Item) receivedMessage.getMessageData());
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			;
			return new Message(MessageType.ADD_NEW_ITEM, receivedMessage.getMessageAnswer(), null);

		case DELETE_ITEM:
			try {
				CatalogQuery.DeleteItem((Item) receivedMessage.getMessageData());
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			;
			return new Message(MessageType.DELETE_ITEM, receivedMessage.getMessageAnswer(), null);

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
			return new Message(MessageType.INSERT_NEW_ORDER, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case UPDATE_USED_REFUND:
			try {
				GetOrderQuery.UpdateRefundUsed((String) receivedMessage.getMessageData());
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			;
			return new Message(MessageType.INSERT_NEW_ORDER, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case GET_STORE_ID:
			try {
				storeID = StoresQuery.GetStoreId((String) receivedMessage.getMessageData());
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(storeID);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			;
			return new Message(MessageType.GET_STORE_ID, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case GET_STORE_ID_BY_WORKER_ID:
			try {
				storeID = StoresQuery.GetStoreIdByWorkerID((String) receivedMessage.getMessageData());
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(storeID);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			;
			return new Message(MessageType.GET_STORE_ID_BY_WORKER_ID, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case GET_ORDER_BY_ID:
			Orders = GetOrderQuery.GetOrderByIdAndUserId((String) receivedMessage.getMessageData());
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
			storeID = complaintQuery.GetUserIDbyOrderNumberQuery((String) receivedMessage.getMessageData());
			try {
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(storeID);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}

		case CANCEL_ORDER:
			try {
				GetOrderQuery.CancelOrder((SingleOrder) receivedMessage.getMessageData());
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			;
			return new Message(MessageType.DELETE_ITEM, receivedMessage.getMessageAnswer(), null);

		case INSERT_NEW_COMPLAIN:
			try {
				complaintQuery.InsertNewComplain((Complain) receivedMessage.getMessageData());
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			;
			return new Message(MessageType.INSERT_NEW_COMPLAIN, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

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

		case CHECK_EXIST_QOMPLAIN:

			String flag = complaintQuery.CheckComplaintExist((String) receivedMessage.getMessageData());
			try {
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(flag);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}

			return new Message(MessageType.GET_SURVEY_NUMBER, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());
		case CHECK_ORDER_BY_USERID:

			String flag2 = complaintQuery.CheckIfThereExistOrderForUserId((String) receivedMessage.getMessageData());
			try {
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(flag2);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}

			return new Message(MessageType.CHECK_ORDER_BY_USERID, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case INCOME_REPORT:
			ArrayList<String> arr = new ArrayList<>();
			arr = (ArrayList<String>) receivedMessage.getMessageData();

			ReportQuery.CreateIncomeReports(arr.get(0), arr.get(1), arr.get(2));
			try {
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(arr);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			return new Message(MessageType.INCOME_REPORT, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case GRAPH_STATISTICS:
			ArrayList<String> GraphArr = new ArrayList<>();
			ArrayList<String> GraphArr2 = new ArrayList<>();
			GraphArr = (ArrayList<String>) receivedMessage.getMessageData();
			GraphArr2 = ReportQuery.GetIncomeGraphStatistics(GraphArr.get(0), GraphArr.get(1), GraphArr.get(2));
			try {
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(GraphArr2);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			return new Message(MessageType.INCOME_REPORT, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case GET_STORE_NAME_BY_ID:
			try {
				data = StoresQuery.GetStoreNameByID((String) receivedMessage.getMessageData());
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(data);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			;
			return new Message(MessageType.GET_STORE_NAME, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case INSERT_NEW_SURVEY:
			try {
				String s = SurveyQuery.InsertNewQuery((Survey) receivedMessage.getMessageData());
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(s);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			;
			return new Message(MessageType.INSERT_NEW_SURVEY, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case CHECK_IF_USERNAME_EXIST:
			String str = AddNewUserQuery.checkIfUserExisting((String) receivedMessage.getMessageData());
			try {
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(str);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			return new Message(MessageType.CHECK_IF_USERNAME_EXIST, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case INSERT_NEW_USER:// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			try {
				AddNewUserQuery.InsertNewUser((User) receivedMessage.getMessageData());
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			;
			return new Message(MessageType.INSERT_NEW_USER, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case INSERT_NEW_ACCOUNT:
			try {
				AddNewUserQuery.InsertNewAccount((Account) receivedMessage.getMessageData());
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			;
			return new Message(MessageType.INSERT_NEW_ACCOUNT, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case GET_COMPLAINTS:
			singlecomplaint = complaintQuery.GetComplaints();
			try {
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(singlecomplaint);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			return new Message(MessageType.GET_COMPLAINTS, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case GET_COMPLAINT_BY_ID:
			singlecomplaint = complaintQuery.GetSingleComplaintByUserId((String) receivedMessage.getMessageData());
			try {
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(singlecomplaint);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			return new Message(MessageType.GET_COMPLAINT_BY_ID, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case UPDATE_REFUND:
			try {
				complaintQuery.UpdateAccountTotalRefund((String) receivedMessage.getMessageData());
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			;
			return new Message(MessageType.UPDATE_REFUND, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case UPDATE_STATUS_COMPLAINT:
			try {
				complaintQuery.UpdateToHandled((String) receivedMessage.getMessageData());
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			;
			return new Message(MessageType.UPDATE_STATUS_COMPLAINT, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case GET_SINGLE_DELIVERY:
			singleDelivery = DeliveryQuery.getOrderForDelivey();
			try {
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(singleDelivery);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			return new Message(MessageType.GET_SINGLE_DELIVERY, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case GET_SINGLE_DELIVERY_BY_STORE_ID:
			singleDelivery = DeliveryQuery.getSingleDeliveryByStoreId((String) receivedMessage.getMessageData());
			try {
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(singleDelivery);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			return new Message(MessageType.GET_SINGLE_DELIVERY_BY_STORE_ID, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());
		case GET_SINGLE_DELIVERY_BY_ORDER_ID:
			singleDelivery = DeliveryQuery.getSingleDeliveryByOrderId((String) receivedMessage.getMessageData());
			try {
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(singleDelivery);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			return new Message(MessageType.GET_SINGLE_DELIVERY_BY_ORDER_ID, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());
		case UPDATE_DELIVERY_STATUS:
			String res = DeliveryQuery.UpdateDeliveryStatus((String) receivedMessage.getMessageData());
			try {
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(res);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			return new Message(MessageType.UPDATE_DELIVERY_STATUS, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());
		case INSERT_TO_DELIVERY_TABLE:
			String res2 = DeliveryQuery.InsertSingleSelfDelivery((SingleSelfDelivery) receivedMessage.getMessageData());
			try {
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(res2);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			return new Message(MessageType.INSERT_TO_DELIVERY_TABLE, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());
		case UPDATE_REFUND_BY_ORDERID:
			try {
				DeliveryQuery.UpdateRefundByOrderId((String) receivedMessage.getMessageData());
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			;
		case VIEW_SELF_DELIVERY_DETAILS:
			singleSelfDelivery = DeliveryQuery.getSingleSelfOrder((String) receivedMessage.getMessageData());
			try {
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(singleSelfDelivery);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			return new Message(MessageType.VIEW_SELF_DELIVERY_DETAILS, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());
		case VIEW_SELF_DELIVERY_DETAILS_BY_ORDERID:
			singleSelfDelivery = DeliveryQuery.getSingleSelfOrderByOrderId((String) receivedMessage.getMessageData());
			try {
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(singleSelfDelivery);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			return new Message(MessageType.VIEW_SELF_DELIVERY_DETAILS_BY_ORDERID, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case GET_STORE_ORDERS:
			Orders = GetOrderQuery.getStoreOrders((String) receivedMessage.getMessageData());
			try {
				receivedMessage.setMessageData(Orders);
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			return new Message(MessageType.GET_STORE_ORDERS, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case GET_SURVEY_ANSWERS:
			try {
				receivedMessage.setMessageData(SurveyQuery.GetSurveyAnswers());
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			return new Message(MessageType.GET_SURVEY_ANSWERS, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case SAVE_ANALYSIS:
			String[] report = (String[]) receivedMessage.getMessageData();
			try {
				PDDocument conclusion = new PDDocument();
				PDPage page = new PDPage();
				conclusion.addPage(page);
				PDPageContentStream contentStream = new PDPageContentStream(conclusion, page);
				contentStream.setFont(PDType1Font.COURIER, 12);
				contentStream.beginText();
				contentStream.newLineAtOffset(25, 700);
				String[] spliting = report[0].split("\n");
				for (int i = 0; i < spliting.length; i++) {
					contentStream.showText(spliting[i]);
					contentStream.newLineAtOffset(0, -25);
					contentStream.newLine();

					System.out.println(spliting[i]);
				}
				contentStream.endText();
				contentStream.close();

				conclusion.save(report[1]);

				conclusion.close();
			} catch (IOException e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}

			return new Message(MessageType.SAVE_ANALYSIS, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());

		case GET_USERS_BY_ID:
			singleUser = AddNewUserQuery.GetUserDetials();
			try {
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(singleUser);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			return new Message(MessageType.GET_USERS_BY_ID, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());
		case GET_MANAGER_ORDERS:
			singleManageOrder = GetOrderQuery.GetManagerOrders();
			try {
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(singleManageOrder);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			return new Message(MessageType.GET_MANAGER_ORDERS, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());
		case GET_ORDER_BY_ORDER_ID:
			singleManageOrder = GetOrderQuery.GetOrderById((String) receivedMessage.getMessageData());
			try {
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(singleManageOrder);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			return new Message(MessageType.GET_ORDER_BY_ORDER_ID, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());
		case GET_USER_BY_USER_ID:
			singleUser = AddNewUserQuery.GetUserByUserId((String) receivedMessage.getMessageData());
			try {
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
				receivedMessage.setMessageData(singleUser);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			return new Message(MessageType.GET_USER_BY_USER_ID, receivedMessage.getMessageAnswer(),
					receivedMessage.getMessageData());
		case UPDATE_STATUS_BY_MANAGER:
			try {
				AccountDetailsQuery.UpdateStatusByManager((String) receivedMessage.getMessageData());
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			;
		case UPDATE_ORDER_STATUS_BY_MANAGER:
			try {
				GetOrderQuery.UpdateOrderStatusByManager((String) receivedMessage.getMessageData());
				receivedMessage.setMessageAnswer(MessageAnswer.SUCCEED);
			} catch (Exception e) {
				receivedMessage.setMessageAnswer(MessageAnswer.NOT_SUCCEED);
				receivedMessage.setMessageData(null);
			}
			;

		default:// ;

			return new Message(MessageType.ERROR, null);
		}
	}

}
