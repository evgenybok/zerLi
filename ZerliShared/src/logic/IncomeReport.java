package logic;

public class IncomeReport extends Report {

	private double income;

	public IncomeReport(Quarterly quarterly, String year, String month, int storeID, double income) {
		super(quarterly, year, month, storeID);
		this.income = income;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	@Override
	public String toString() {
		return "IncomeReport [income=" + income + "]";
	}

}
