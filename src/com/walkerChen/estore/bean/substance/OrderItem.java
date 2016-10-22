package com.walkerChen.estore.bean.substance;
/**
 * 订单项  与之相关联的就是 与用户打交道的购物项
 *
 *  订单项只是一个记录型的一个类， 相当于等待购物项给订单项赋值
 *  			记录买的数量
 *  			记录书的价格
 *  			记录买的那个产品
 *
 * @author  ChenBaiHong
 */
public class OrderItem {
	private String id;
	private int buyQuantity;
	private double price;
	private Product product;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getBuyQuantity() {
		return buyQuantity;
	}
	public void setBuyQuantity(int buyQuantity) {
		this.buyQuantity = buyQuantity;
	}
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public OrderItem(String id, int buyQuantity, double price,
			Product product) {
		super();
		this.id = id;
		this.buyQuantity = buyQuantity;
		this.price = price;
		this.product = product;
	}
	public OrderItem() {
		super();
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", buyQuantity=" + buyQuantity
				+ ", price=" + price + ", product=" + product + "]";
	}
	
}
