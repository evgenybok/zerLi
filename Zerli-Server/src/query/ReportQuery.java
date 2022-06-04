package query;

import logic.Store;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReportQuery {


    //Query that Creates an Income Report From Table Orders and updates the incomereports table.
    public static void CreateIncomeReports(String StoreID,String Month,String Year){
        String query1="INSERT INTO zerli.incomereport (Week1,Week2,Week3,Week4,StoreID,Month,Year) VALUES('"+0+"','"+0+"','"+0+"','"+0+"','"+StoreID+"','"+Month+"','"+Year+"');";

        try {
            PreparedStatement st = ConnectToDB.conn.prepareStatement(query1);
            st.executeUpdate();
        }
        catch(SQLException e){
          return;
        };


        String query="SELECT FLOOR((DayOfMonth(OrderDate)-1)/7)+1\n" +
                " as week_of_year, SUM(Price) as Price \n" +
                "FROM zerli.orders\n" +
                "where StoreID= '"+StoreID+"' AND month(OrderDate)='"+Month+"' AND year(OrderDate)='"+Year+"' \n" +
                "group by FLOOR((DayOfMonth(OrderDate)-1)/7)+1 \n" +
                "Order BY week_of_year;";

        try {
            PreparedStatement st1 = ConnectToDB.conn.prepareStatement(query);
            ResultSet rs1 = st1.executeQuery();
            while (rs1.next()) {
                String week = rs1.getString("week_of_year");
                String price = rs1.getString("Price");
                String newWeek="Week"+week;

                 String query2= "Update incomereport SET " +newWeek+" ='"+price+"' WHERE StoreID='"+StoreID+"' AND Month='"+Month+"' AND Year='"+Year+"';";
                 System.out.println(query2);
                try {
                    PreparedStatement st2 = ConnectToDB.conn.prepareStatement(query2);
                    st2.executeUpdate();
                } catch (SQLException e) {
                    return;
                }

            }
            }

        catch(SQLException e){
            return;

        };

        }


        public static ArrayList<String> GetIncomeGraphStatistics(String StoreID, String Month, String Year) {

        String query="SELECT * FROM zerli.incomereport WHERE StoreID= '"+StoreID+"' AND Month='"+Month+"' AND Year='"+Year+"';";
            ArrayList<String>GraphStats=new ArrayList<>();

            try {
                PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
                ResultSet rs = st.executeQuery();
                while (rs .next()) {
                    String Week1 = rs.getString("Week1");
                    String Week2 = rs.getString("Week2");
                    String Week3 = rs.getString("Week3");
                    String Week4 = rs.getString("Week4");
                    String currentMonth = rs.getString("Month");

                    GraphStats.add(currentMonth);
                    GraphStats.add(Week1);
                    GraphStats.add(Week2);
                    GraphStats.add(Week3);
                    GraphStats.add(Week4);
                }

            }
            catch(SQLException e){
                return null;
            }

            return GraphStats;
        }


    public static void CreateComplaintQuarterReports(String StoreID,String Quarter,String Year) throws SQLException {
        String query,query1,query2;
         query="INSERT INTO zerli.complaintreport (StoreID,QuarterNumber,Year,GotRefund,DidntGotRefund) VALUES('"+StoreID+"','"+Quarter+"','"+Year+"','"+0+"','"+0+"');";

        try {
            PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
            st.executeUpdate();
        }
        catch(SQLException e){
            return;
        };

        switch (Quarter) {
            case "01":
                query = "SELECT COUNT(*) as count_compliant FROM zerli.complaint WHERE  Refund!='"+null+"'AND StoreID='" + StoreID + "' AND year(Date)='" + Year + "' AND (month(Date)='01' OR month(Date)='02' OR month(Date)='03');";

                try {
                    PreparedStatement st1 = ConnectToDB.conn.prepareStatement(query);
                    ResultSet rs1 = st1.executeQuery();
                    String count_Compliant = null;
                    while (rs1.next()) {
                        count_Compliant = rs1.getString("count_compliant");
                    }

                    query2 = "Update complaintreport SET GotRefund ='" + count_Compliant + "' WHERE StoreID='" + StoreID + "' AND Quarter='01' AND Year='" + Year + "';";
                    try {
                        PreparedStatement st2 = ConnectToDB.conn.prepareStatement(query2);
                        st2.executeUpdate();
                    }
                    catch(SQLException e){
                        return;
                    }

                } catch (SQLException e) {
                    return;
                }

                query = "SELECT COUNT(*) as count_compliant FROM zerli.complaint WHERE  Refund ='"+null+"'AND StoreID='" + StoreID + "' AND year(Date)='" + Year + "' AND (month(Date)='01' OR month(Date)='02' OR month(Date)='03');";

                try {
                    PreparedStatement st1 = ConnectToDB.conn.prepareStatement(query);
                    ResultSet rs1 = st1.executeQuery();
                    String count_Compliant = null;
                    while (rs1.next()) {
                        count_Compliant = rs1.getString("count_compliant");
                    }

                    query2 = "Update complaintreport SET DidntGotRefund ='" + count_Compliant + "' WHERE StoreID='" + StoreID + "' AND Quarter='01' AND Year='" + Year + "';";
                    try {
                        PreparedStatement st2 = ConnectToDB.conn.prepareStatement(query2);
                        st2.executeUpdate();
                    }
                    catch(SQLException e){
                        return;
                    }

                } catch (SQLException e) {
                    return;
                }



                break;
            case "02":

                query = "SELECT COUNT(*) as count_compliant FROM zerli.complaint WHERE StoreID='" + StoreID + "' AND year(Date)='" + Year + "' AND (month(Date)='04' OR month(Date)='05' OR month(Date)='06');";
                try {
                    PreparedStatement st1 = ConnectToDB.conn.prepareStatement(query);
                    ResultSet rs1 = st1.executeQuery();
                    String count_Compliant = null;
                    while (rs1.next()) {
                        count_Compliant = rs1.getString("count_compliant");
                    }

                    query2 = "Update complaintreport SET GotRefund ='" + count_Compliant + "' WHERE StoreID='" + StoreID + "' AND Quarter='02' AND Year='" + Year + "';";
                    try {
                        PreparedStatement st2 = ConnectToDB.conn.prepareStatement(query2);
                        st2.executeUpdate();
                    }
                    catch(SQLException e){
                        return;
                    }

                } catch (SQLException e) {
                    return;
                }
                query = "SELECT COUNT(*) as count_compliant FROM zerli.complaint WHERE  Refund ='"+null+"'AND StoreID='" + StoreID + "' AND year(Date)='" + Year + "' AND (month(Date)='04' OR month(Date)='05' OR month(Date)='06');";

                try {
                    PreparedStatement st1 = ConnectToDB.conn.prepareStatement(query);
                    ResultSet rs1 = st1.executeQuery();
                    String count_Compliant = null;
                    while (rs1.next()) {
                        count_Compliant = rs1.getString("count_compliant");
                    }

                    query2 = "Update complaintreport SET DidntGotRefund ='" + count_Compliant + "' WHERE StoreID='" + StoreID + "' AND Quarter='02' AND Year='" + Year + "';";
                    try {
                        PreparedStatement st2 = ConnectToDB.conn.prepareStatement(query2);
                        st2.executeUpdate();
                    }
                    catch(SQLException e){
                        return;
                    }

                } catch (SQLException e) {
                    return;
                }





                break;
            case "03":
                query = "SELECT COUNT(*) as count_compliant FROM zerli.complaint WHERE StoreID='" + StoreID + "' AND year(Date)='" + Year + "' AND (month(Date)='07' OR month(Date)='08' OR month(Date)='09');";

                try {
                    PreparedStatement st1 = ConnectToDB.conn.prepareStatement(query);
                    ResultSet rs1 = st1.executeQuery();
                    String count_Compliant = null;
                    while (rs1.next()) {
                        count_Compliant = rs1.getString("count_compliant");
                    }

                    query2 = "Update complaintreport SET GotRefund ='" + count_Compliant + "' WHERE StoreID='" + StoreID + "' AND Quarter='03' AND Year='" + Year + "';";
                    try {
                        PreparedStatement st2 = ConnectToDB.conn.prepareStatement(query2);
                        st2.executeUpdate();
                    }
                    catch(SQLException e){
                        return;
                    }

                } catch (SQLException e) {
                    return;
                }
                query = "SELECT COUNT(*) as count_compliant FROM zerli.complaint WHERE  Refund ='"+null+"'AND StoreID='" + StoreID + "' AND year(Date)='" + Year + "' AND (month(Date)='07' OR month(Date)='08' OR month(Date)='09');";

                try {
                    PreparedStatement st1 = ConnectToDB.conn.prepareStatement(query);
                    ResultSet rs1 = st1.executeQuery();
                    String count_Compliant = null;
                    while (rs1.next()) {
                        count_Compliant = rs1.getString("count_compliant");
                    }

                    query2 = "Update complaintreport SET DidntGotRefund ='" + count_Compliant + "' WHERE StoreID='" + StoreID + "' AND Quarter='03' AND Year='" + Year + "';";
                    try {
                        PreparedStatement st2 = ConnectToDB.conn.prepareStatement(query2);
                        st2.executeUpdate();
                    }
                    catch(SQLException e){
                        return;
                    }

                } catch (SQLException e) {
                    return;
                }






                break;
            case "04":
                query = "SELECT COUNT(*) as count_compliant FROM zerli.complaint WHERE StoreID='" + StoreID + "' AND year(Date)='" + Year + "' AND (month(Date)='10' OR month(Date)='11' OR month(Date)='12');";

                try {
                    PreparedStatement st1 = ConnectToDB.conn.prepareStatement(query);
                    ResultSet rs1 = st1.executeQuery();
                    String count_Compliant = null;
                    while (rs1.next()) {
                        count_Compliant = rs1.getString("count_compliant");
                    }

                    query2 = "Update complaintreport SET GotRefund ='" + count_Compliant + "' WHERE StoreID='" + StoreID + "' AND Quarter='04' AND Year='" + Year + "';";
                    try {
                        PreparedStatement st2 = ConnectToDB.conn.prepareStatement(query2);
                        st2.executeUpdate();
                    }
                    catch(SQLException e){
                        return;
                    }

                } catch (SQLException e) {
                    return;
                }
                query = "SELECT COUNT(*) as count_compliant FROM zerli.complaint WHERE  Refund ='"+null+"'AND StoreID='" + StoreID + "' AND year(Date)='" + Year + "' AND (month(Date)='10' OR month(Date)='11' OR month(Date)='12');";

                try {
                    PreparedStatement st1 = ConnectToDB.conn.prepareStatement(query);
                    ResultSet rs1 = st1.executeQuery();
                    String count_Compliant = null;
                    while (rs1.next()) {
                        count_Compliant = rs1.getString("count_compliant");
                    }

                    query2 = "Update complaintreport SET DidntGotRefund ='" + count_Compliant + "' WHERE StoreID='" + StoreID + "' AND Quarter='04' AND Year='" + Year + "';";
                    try {
                        PreparedStatement st2 = ConnectToDB.conn.prepareStatement(query2);
                        st2.executeUpdate();
                    }
                    catch(SQLException e){
                        return;
                    }

                } catch (SQLException e) {
                    return;
                }

                break;
        }

}
}
