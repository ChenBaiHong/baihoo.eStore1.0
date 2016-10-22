package com.walkerChen.estore.bean.substance;


public class Product {
	private String id; //产品编号
	private String name;//产品名字
	private double price;//产品价格
	private String iconurl;//产品图片保存路径
	private int quantity;//产品数量
	private int totalSaleNum;//产品销售数量
	private String description;//产品描述
	private Category category;//产品品种
	
	public Product() {
		super();
	}
	public Product(String id, String name, double price, String iconurl,
			int quantity, int totalSaleNum, String description,
			Category category) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.iconurl = iconurl;
		this.quantity = quantity;
		this.totalSaleNum = totalSaleNum;
		this.description = description;
		this.category = category;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getIconurl() {
		return iconurl;
	}
	public void setIconurl(String iconurl) {
		this.iconurl = iconurl;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getTotalSaleNum() {
		return totalSaleNum;
	}
	public void setTotalSaleNum(int totalSaleNum) {
		this.totalSaleNum = totalSaleNum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price
				+ ", iconurl=" + iconurl + ", quantity=" + quantity + ", totalSaleNum="
				+ totalSaleNum + ", description=" + description + ", category="
				+ category + "]";
	}
}
