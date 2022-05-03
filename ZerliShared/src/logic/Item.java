package logic;

public class Item {
	private int ID;
	private String imgSrc;
	private String name;
	private double price;
	private String color;
	private String type;

	public Item(int ID, String imgSrc, String name, double price, String color, String type) {
		this.ID = ID;
		this.imgSrc = imgSrc;
		this.name = name;
		this.price = price;
		this.color = color;
		this.type = type;
	}


	@Override
	public String toString() {
		return "Item [ID=" + ID + ", imgSrc=" + imgSrc + ", name=" + name + ", price=" + price + ", color=" + color
				+ ", type=" + type + "]";
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

}
