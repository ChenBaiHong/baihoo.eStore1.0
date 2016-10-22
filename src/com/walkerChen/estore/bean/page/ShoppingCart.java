package com.walkerChen.estore.bean.page;

import com.walkerChen.estore.bean.substance.Product;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class ShoppingCart {
	private Map<String , ShoppingItem> map = new HashMap<String , ShoppingItem>();
	private double totalMoney;
	
	public void appendProduct(Product product){
		ShoppingItem shopItem = map.get(product.getId());
		
		if(shopItem==null){
			shopItem = new ShoppingItem();
			shopItem.setProduct(product);
			shopItem.setBuyQuantity(1);
			map.put(product.getId(), shopItem);
		}else{
			shopItem.setBuyQuantity(shopItem.getBuyQuantity()+1);
		}
	}
	public Map<String, ShoppingItem> getMap() {
		return map;
	}
	public void setMap(Map<String, ShoppingItem> map) {
		
		this.map = map;
	}
	//由内部的自动算出产品的总价格
	public double getTotalMoney() {
		double price = 0;
		Set<Entry<String , ShoppingItem>> entrySet = map.entrySet();
		Iterator<Entry<String, ShoppingItem>>iterator = entrySet.iterator();
		while(iterator.hasNext()){
			Entry<String, ShoppingItem> entry= iterator.next();
			ShoppingItem shopItem= entry.getValue();
			price +=shopItem.getPrice();
		}
		totalMoney=price;
		return totalMoney;
	}
}
