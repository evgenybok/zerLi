package query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import logic.Survey;
import logic.SurveyAnswer;

/**
 * @author Evgeny
 * this class contains 'Survey' related queries based on the DB.
 *
 */
public class SurveyQuery {
	/**
	 * @return number of the next survey number.
	 */
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
	/**
	 * @param survey - logic Survey, contains survey details.
	 * Inserts to the DB new given survey.
	 * @return string true if insertion successful, string false otherwise.
	 */
	public static String InsertNewQuery(Survey survey)
	{
		String query= "INSERT INTO zerli.survey VALUES("+survey.getSurveynum()+",'" +survey.getSurveyCreatorId()+"','" +survey.getQuestions().get(0)+"','" 
				+survey.getQuestions().get(1)+"','" + survey.getQuestions().get(2)+"','" +survey.getQuestions().get(3)+"','" +survey.getQuestions().get(4)+"','" 
				+survey.getQuestions().get(5)+"')";
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			return "false";
		}
		return "true";
	}
	
	
	/**
	 * Method returns all the answers of all surveys.
	 * @return Array list of logic Survey Answer
	 */
	public static ArrayList<SurveyAnswer> GetSurveyAnswers()
	{
		ArrayList<SurveyAnswer> answers = new ArrayList<>();
		String query= "SELECT * FROM zerli.survey_answers;";
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				int surveyNumber = rs.getInt("SurveyNumber");
				int q1 = rs.getInt("Q1");
				int q2 = rs.getInt("Q2");
				int q3 = rs.getInt("Q3");
				int q4 = rs.getInt("Q4");
				int q5 = rs.getInt("Q5");
				int q6 = rs.getInt("Q6");
				answers.add(new SurveyAnswer(id,surveyNumber,q1,q2,q3,q4,q5,q6));
			}
		} catch (SQLException e) {
		}
		return answers;
	}
	/**
	 * Retrieves the survey questions from the DB
	 * @param surveyNum survey number
	 * @return
	 */
	public static ArrayList<String> GetSurveyQues(String surveyNum) {
		ArrayList<String> ques = new ArrayList<>();
		String query = "SELECT * FROM zerli.survey WHERE SurveyNumber=" + surveyNum + ";";
		try {
			PreparedStatement st = ConnectToDB.conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String q1 = rs.getString("Q1");
				String q2 = rs.getString("Q2");
				String q3 = rs.getString("Q3");
				String q4 = rs.getString("Q4");
				String q5 = rs.getString("Q5");
				String q6 = rs.getString("Q6");
				ques.add(q1);
				ques.add(q2);
				ques.add(q3);
				ques.add(q4);
				ques.add(q5);
				ques.add(q6);
			}
		} catch (SQLException e) {

		}
		return ques;
	}
	/**
	 * Updates DB and adds the customer's answers to the survey
	 * @param ans user answers for the survey
	 */
	public static void UpdateSurveyAns(String ans)
	{
		String[] answers = ans.split("@");
		int count = 0;
		try {
			String sql = "SELECT MAX(ID) AS ID FROM survey_answers";
			PreparedStatement ps = ConnectToDB.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt("ID"); // access the max value
			}
		} catch (SQLException e) {
			return ;
		}
		count++;
		String countUpt = String.valueOf(count);
		int surveyNum = Integer.parseInt(answers[0]);
		int q1 =Integer.parseInt(answers[1]); 
		int q2 =Integer.parseInt(answers[2]); 
		int q3 =Integer.parseInt(answers[3]); 
		int q4 =Integer.parseInt(answers[4]); 
		int q5 =Integer.parseInt(answers[5]); 
		int q6 =Integer.parseInt(answers[6]); 
		String query ="INSERT INTO zerli.survey_answers VALUES("+countUpt+","+surveyNum+","+q1+","+q2+","+q3+","+q4+","+q5+","+q6+")";
		try {
			PreparedStatement st1 = ConnectToDB.conn.prepareStatement(query);
			st1.executeUpdate();
		} catch (SQLException e) {
			return;
		}
		return;
	}
	
	
}
