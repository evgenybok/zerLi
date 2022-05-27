package logic;

import java.io.Serializable;
import java.util.ArrayList;

public class Survey implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int surveynum;
	private String surveyCreatorId;
	private ArrayList<String> questions;
	
	public Survey(int surveynum,String surveyCreatorId,ArrayList<String> questions)
	{
		this.surveynum=surveynum;
		this.surveyCreatorId=surveyCreatorId;
		this.questions=questions;
	}
	public int getSurveynum() {
		return surveynum;
	}

	public void setSurveynum(int surveynum) {
		this.surveynum = surveynum;
	}

	public String getSurveyCreatorId() {
		return surveyCreatorId;
	}

	public void setSurveyCreatorId(String surveyCreatorId) {
		this.surveyCreatorId = surveyCreatorId;
	}

	public ArrayList<String> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<String> questions) {
		this.questions = questions;
	}
	@Override
	public String toString() {
		return "Survey [surveynum=" + surveynum + ", surveyCreatorId=" + surveyCreatorId + ", questions=" + questions
				+ "]";
	}
}
