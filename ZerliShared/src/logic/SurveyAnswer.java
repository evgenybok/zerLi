package logic;

import java.io.Serializable;

public class SurveyAnswer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7125451465107280207L;
	private int ID;
	private int SurveyNumber;
	private int Q1;
	private int Q2;
	private int Q3;
	private int Q4;
	private int Q5;
	private int Q6;

	public SurveyAnswer(int iD, int surveyNumber, int q1, int q2, int q3, int q4, int q5, int q6) {
		super();
		ID = iD;
		SurveyNumber = surveyNumber;
		Q1 = q1;
		Q2 = q2;
		Q3 = q3;
		Q4 = q4;
		Q5 = q5;
		Q6 = q6;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getSurveyNumber() {
		return SurveyNumber;
	}

	public void setSurveyNumber(int surveyNumber) {
		SurveyNumber = surveyNumber;
	}

	public int getQ1() {
		return Q1;
	}

	public void setQ1(int q1) {
		Q1 = q1;
	}

	public int getQ2() {
		return Q2;
	}

	public void setQ2(int q2) {
		Q2 = q2;
	}

	public int getQ3() {
		return Q3;
	}

	public void setQ3(int q3) {
		Q3 = q3;
	}

	public int getQ4() {
		return Q4;
	}

	public void setQ4(int q4) {
		Q4 = q4;
	}

	public int getQ5() {
		return Q5;
	}

	public void setQ5(int q5) {
		Q5 = q5;
	}

	public int getQ6() {
		return Q6;
	}

	public void setQ6(int q6) {
		Q6 = q6;
	}

	@Override
	public String toString() {
		return "SurveyAnswer [ID=" + ID + ", SurveyNumber=" + SurveyNumber + ", Q1=" + Q1 + ", Q2=" + Q2 + ", Q3=" + Q3
				+ ", Q4=" + Q4 + ", Q5=" + Q5 + ", Q6=" + Q6 + "]";
	}

}
