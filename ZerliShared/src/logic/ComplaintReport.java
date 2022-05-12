package logic;

import java.util.Arrays;

public class ComplaintReport extends Report {

	// array size of 2, first is the number of complaints handled, second is
	// complaints pending.
	private int[] firstMonth;
	private int[] secondMonth;
	private int[] thirdMonth;

	public ComplaintReport(Quarterly quarterly, String year, String month, int storeID, int[] firstMonth,
			int[] secondMonth, int[] thirdMonth) {
		super(quarterly, year, month, storeID);
		this.firstMonth = firstMonth;
		this.secondMonth = secondMonth;
		this.thirdMonth = thirdMonth;
	}

	public int[] getFirstMonth() {
		return firstMonth;
	}

	public void setFirstMonth(int[] firstMonth) {
		this.firstMonth = firstMonth;
	}

	public int[] getSecondMonth() {
		return secondMonth;
	}

	public void setSecondMonth(int[] secondMonth) {
		this.secondMonth = secondMonth;
	}

	public int[] getThirdMonth() {
		return thirdMonth;
	}

	public void setThirdMonth(int[] thirdMonth) {
		this.thirdMonth = thirdMonth;
	}

	@Override
	public String toString() {
		return "ComplaintReport [firstMonth=" + Arrays.toString(firstMonth) + ", secondMonth="
				+ Arrays.toString(secondMonth) + ", thirdMonth=" + Arrays.toString(thirdMonth) + "]";
	}

}
