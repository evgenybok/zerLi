package logic;

public class Store {
	public Store(int storeID, String storeAddress) {
		super();
		this.storeID = storeID;
		this.storeAddress = storeAddress;
	}
	private int storeID;
	private String storeAddress;
	//private ArrayList<Double> monthlyIncome; //List of daily incomes for current store.
	public int getStoreID() {
		return storeID;
	}
	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}
	public String getStoreAddress() {
		return storeAddress;
	}
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	@Override
	public String toString() {
		return "Store [storeID=" + storeID + ", storeAddress=" + storeAddress + "]";
	}
	
}
