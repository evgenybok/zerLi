package logic;

public class OrderReport extends Report {
	private int ordersAmount;

	// Types of items
	private int brideBouquetAmount;
	private int potAmount;
	private int accessoryAmount;

	private int bouquetAmount;
	private int selfAssemblyAmount;

	public OrderReport(Quarterly quarterly, String year, String month, int storeID, int ordersAmount,
			int brideBouquetAmount, int potAmount, int accessoryAmount, int bouquetAmount, int selfAssemblyAmount) {
		super(quarterly, year, month, storeID);
		this.ordersAmount = ordersAmount;
		this.brideBouquetAmount = brideBouquetAmount;
		this.potAmount = potAmount;
		this.accessoryAmount = accessoryAmount;
		this.bouquetAmount = bouquetAmount;
		this.selfAssemblyAmount = selfAssemblyAmount;
	}

	public int getOrdersAmount() {
		return ordersAmount;
	}

	public void setOrdersAmount(int ordersAmount) {
		this.ordersAmount = ordersAmount;
	}

	public int getBrideBouquetAmount() {
		return brideBouquetAmount;
	}

	public void setBrideBouquetAmount(int brideBouquetAmount) {
		this.brideBouquetAmount = brideBouquetAmount;
	}

	public int getPotAmount() {
		return potAmount;
	}

	public void setPotAmount(int potAmount) {
		this.potAmount = potAmount;
	}

	public int getAccessoryAmount() {
		return accessoryAmount;
	}

	public void setAccessoryAmount(int accessoryAmount) {
		this.accessoryAmount = accessoryAmount;
	}

	public int getBouquetAmount() {
		return bouquetAmount;
	}

	public void setBouquetAmount(int bouquetAmount) {
		this.bouquetAmount = bouquetAmount;
	}

	public int getSelfAssemblyAmount() {
		return selfAssemblyAmount;
	}

	public void setSelfAssemblyAmount(int selfAssemblyAmount) {
		this.selfAssemblyAmount = selfAssemblyAmount;
	}

	@Override
	public String toString() {
		return "OrderReport [ordersAmount=" + ordersAmount + ", brideBouquetAmount=" + brideBouquetAmount
				+ ", potAmount=" + potAmount + ", accessoryAmount=" + accessoryAmount + ", bouquetAmount="
				+ bouquetAmount + ", selfAssemblyAmount=" + selfAssemblyAmount + "]";
	}

}
