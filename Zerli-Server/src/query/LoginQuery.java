package query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ocsf.server.ConnectionToClient;

/**
 * @author Evgeny
 * this class contains 'Login' related queries based on the DB.
 *
 */
public class LoginQuery {

    /**
     * @param loginInfo - username,password of user
     * @param client - OCSF client information
     * updates login status in DB of given user when logging in.
     * @return
     */
    public static String Login(String loginInfo, ConnectionToClient client) {
        String[] login = loginInfo.split("@"); // username@password
        String query ="UPDATE users SET LoggedIn=true WHERE Username='"+login[0]+"' AND Password='"+login[1]+"'";
        try {
            PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
            int rs = st.executeUpdate();
            if(rs==-1)
                return null;
            String query2 ="SELECT Role,id FROM zerli.users WHERE Username='"+login[0]+"'";
            PreparedStatement st2= ConnectToDB.conn.prepareStatement(query2);
            ResultSet rs2=st2.executeQuery();
            while (rs2.next()) {
                String role = rs2.getString("Role");
                String id1 = rs2.getString("id");
                if(role.equals("customer")) {
                    String query3 ="SELECT Status FROM zerli.account_details WHERE User_ID='"+id1+"'";
                    PreparedStatement st3= ConnectToDB.conn.prepareStatement(query3);
                    ResultSet rs3=st3.executeQuery();
                    while (rs3.next()) {
                        String status= rs3.getString("Status");
                        System.out.println(role+" "+status);
                        return role+"@"+status;
                    }

                }
                return role;
            }

        } catch (SQLException e) {
            return null;
        }
        return null;
    }

}