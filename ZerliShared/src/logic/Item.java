package logic;


import java.io.Serializable;

public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	private int ID;
	private String imgSrc;
	private String name;
	private double price;
	private String color;
	private String type;
	private boolean onSale;
	private double salePrice;

	public Item(int ID, String imgSrc, String name, double price, String color, String type,boolean onSale,double salePrice) {
		this.ID = ID;
		this.imgSrc = imgSrc;
		this.name = name;
		this.price = price;
		this.color = color;
		this.type = type;
		this.onSale = onSale;
		this.salePrice = salePrice;		
	}


	@Override
	public String toString() {
		return "Item [ID=" + ID + ", imgSrc=" + imgSrc + ", name=" + name + ", price=" + price + ", color=" + color
				+ ", type=" + type + ", onSale=" + onSale + ", salePrice=" + salePrice + "]";
	}


	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public boolean isOnSale() {
		return onSale;
	}


	public void setOnSale(boolean onSale) {
		this.onSale = onSale;
	}


	public double getSalePrice() {
		return salePrice;
	}


	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
}
