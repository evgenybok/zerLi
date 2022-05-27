package query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.Survey;

public class SurveyQuery {
	public static String getNumberOfNextSurvey()
	{
		int lastSurveyNumber=0;
		try
		{
			String sql = "SELECT MAX(SurveyNumber) AS SurveyNumber FROM survey";
			PreparedStatement ps = ConnectToDB.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				lastSurveyNumber = rs.getInt("SurveyNumber");  // access the max value
			}
		}catch (SQLException e) {
			return null;
		}
		lastSurveyNumber++;
		String temp= String.valueOf(lastSurveyNumber); 
		return temp;
	}
	public static void InsertNewQuery(Survey survey)
	{
		String query= "INSERT INTO zerli.survey VALUES("+survey.getSurveynum()+",'" +survey.getSurveyCreatorId()+"','" +survey.getQuestions().get(0)+"','" 
				+survey.getQuestions().get(1)+"','" + survey.getQuestions().get(2)+"','" +survey.getQuestions().get(3)+"','" +survey.getQuestions().get(4)+"','" 
				+survey.getQuestions().get(5)+"')";
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			return;
		}	
	}
}
