package com.walkerChen.estore.bean.page;


import com.walkerChen.estore.bean.substance.Product;

public class ShoppingItem {
	private int buyQuantity;
	private double price;
	private Product product;
	public int getBuyQuantity() {
		return buyQuantity;
	}
	public void setBuyQuantity(int buyQuantity) {
		price=product.getPrice()*buyQuantity;
		this.buyQuantity = buyQuantity;
	}
	public double getPrice() {
		return price;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "ShoppingItem [buyQuantity=" + buyQuantity + ", price=" + price
				+ ", product=" + product + "]";
	}
	
}
