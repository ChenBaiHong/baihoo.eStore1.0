package com.walkerChen.estore.dao;


import com.walkerChen.estore.bean.page.QueryPaging;
import com.walkerChen.estore.bean.page.QueryResult;
import com.walkerChen.estore.bean.substance.Product;

public interface ProductDao {

	public abstract void addProduct(Product product);
	
	public abstract void remainderProductQuantity(String productId, int totalSale);
	
	public abstract Product findProduct(String id);

	/**
	 * @param startIndex
	 * @param pageSize
	 * @param whereAssociate  
	 * 								whereAssociate equals == (where category_id=?)
	 * @param conditionVariable 
	 * 								?== (conditionVariable) 
	 * @return
	 */
	public abstract QueryResult getProductQueryResult(int startIndex,
													  int pageSize, QueryPaging queryInfo);

	public abstract void deleteProduct(String id);
	
}