package com.walkerChen.estore.dao;

import com.walkerChen.estore.bean.substance.Orders;

import java.util.List;



public interface OrdersDao {

	public abstract void addOrders(Orders orders);

	public abstract Orders findOrders(String id);

	/*
	 * state:true:已发货 state:false;未发货
	 */
	public abstract List<Orders> findStateOrders(boolean state);

	public abstract void updateOrderState(String id, boolean state);

}