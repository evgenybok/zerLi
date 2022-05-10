package query;

import logic.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetOrderQuery {



    public static Object GetOrders() {
        ArrayList<Order> list=new ArrayList<>();
        String query = ("SELECT * FROM zerli.orders;");
        //StringBuilder res = new StringBuilder();
        try {
            PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int OrderNumber = rs.getInt("orderNumber");
                double Price = rs.getDouble("price");
                String GreetingCard = rs.getString("greetingCard");
                String color = rs.getString("Color");
                String D_Order = rs.getString("dOrder");
                String Shop = rs.getString("shop");
                String Date = rs.getString("date");
                String OrderDate = rs.getString("orderDate");
                String Status=rs.getString("Status");
                String SupplyType=rs.getString("SupplyType");
                list.add(new Order(OrderNumber,Price,GreetingCard,color,D_Order,Shop,Date,OrderDate,Status,SupplyType));
                /*res.append(OrderNumber);
                res.append("#");
                res.append(Price);
                res.append("#");
                res.append(GreetingCard);
                res.append("#");
                res.append(color);
                res.append("#");
                res.append(D_Order);
                res.append("#");
                res.append(Shop);
                res.append("#");
                res.append(Date);
                res.append("#");
                res.append(OrderDate);
                res.append("@");*/
            }
            // res.append("&");
            if(list.size()!=0)
            {
                return list;
            }
            /*if (res != null) {
                return res.toString();
            }*/

        } catch (SQLException e) {

        }
       // return ("ERROR");
        return null;
    }

    public static boolean Update(String updatedData) {
        String[] str = updatedData.split("#", 3);
        int ordNum = Integer.parseInt(str[0]);
        String color = str[1];
        String date = str[2];
        String query = ("SELECT * FROM zerli.orders;");

        try {
            PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int num = rs.getInt("orderNumber");
                if (num == ordNum) {
                    String color1 = rs.getString("Color");
                    String date1 = rs.getString("date");
                    if (color.equals("")) {
                        color = color1;
                    }
                    if (date.equals("")) {
                        date = date1;
                    }

                    String query1 = ("UPDATE zerli.orders SET Color=" + "\"" + color + "\"" + " , " + "Date=" + "\""
                            + date + "\"" + " WHERE orderNumber = " + ordNum + ";");
                    st.executeUpdate(query1);
                    return true;
                }

            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    public static String getSelectedOrder(int orderNumber) {
        String query = ("SELECT * FROM zerli.iteminorder;");
        StringBuilder res = new StringBuilder();
        try {
            PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            res.append(orderNumber);
            res.append("#");
            while (rs.next()) {
                int OrderID = rs.getInt("orderID");
                int itemID = rs.getInt("itemID");
                int amount = rs.getInt("amount");
                if (orderNumber == OrderID) {
                    res.append(itemID);
                    res.append("#");
                    res.append(amount);
                    res.append("#");
                }
            }
            // ordernum, itemid, amount, itemid, amout
            res.append("@");
            if (res.toString().equals("@"))
                return null; // no details
            return res.toString();
        } catch (SQLException e) {
            return "Error";
        }
    }

}