package logic;

public class Report {
	public enum Quarterly {FIRST,SECOND,THIRD,FOURTH};		//Quarterly reports divides by months.
	//FIRST: 1-3 , SECOND: 4-6 , THIRD: 7-9 , FOURTH: 10-12.
	
	private Quarterly Quarterly;
	private String year;
	private String month;
	private int storeID;
	public Report(Quarterly quarterly, String year,String month, int storeID) {
		super();
		Quarterly = quarterly;
		this.year = year;
		this.month=month;
		this.storeID = storeID;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Quarterly getQuarterly() {
		return Quarterly;
	}
	public void setQuarterly(Quarterly quarterly) {
		Quarterly = quarterly;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getStoreID() {
		return storeID;
	}
	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}
	@Override
	public String toString() {
		return "Report [Quarterly=" + Quarterly + ", year=" + year + ", month=" + month + ", storeID=" + storeID + "]";
	}

}
