
package com.walkerChen.estore.dao.impl;


import com.walkerChen.estore.bean.page.QueryPaging;
import com.walkerChen.estore.bean.page.QueryResult;
import com.walkerChen.estore.bean.substance.Category;
import com.walkerChen.estore.bean.substance.Product;
import com.walkerChen.estore.dao.ProductDao;
import com.walkerChen.estore.commonUtils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.util.List;




/**
 * create table Product(
	id varChar(40) primary key,
	name varChar(100) not null,
	price decimal(8 , 1) not null ,
	iconurl varChar(225) not null,
	quantity int not null,
	totalSaleNum int null,
	description varChar(225),
	category_id varChar(40),
	foreign key(category_id) references category(id)
);
 * @author hareClase
 *
 */
@SuppressWarnings("all")
public class ProductDaoImpl implements ProductDao {
	
	@Override
	public void addProduct(Product product) {
		try {
			QueryRunner runner = new QueryRunner();
			String sql = "INSERT INTO PRODUCT(ID,NAME ,PRICE,ICONURL,QUANTITY,TOTALSALENUM,DESCRIPTION,CATEGORY_ID)VALUES(?,?,?,?,?,?,?,?)";
			Object[] params={product.getId() , product.getName(), product.getPrice(), product.getIconurl() , product.getQuantity() , product.getTotalSaleNum() , product.getDescription(),product.getCategory().getId()};
			runner.update(JdbcUtils.getConnection(), sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//totalSale 销售数量就是用户购买的数量
	//产品的销售数量根据用户的购买的数据量计算，而该购物项的价格是根据产品的价格乘以购买数量
	public void remainderProductQuantity(String  productId , int totalSale ){
		
		System.out.println("tatalSale:"+totalSale);
		try{
			Product product = findProduct(productId);
			if(product.getQuantity()==0){
				throw new SecurityException("该产品已销售告罄！");
			}if(product.getQuantity()-totalSale<0){
				throw new SecurityException("该产品销售太火热！数量不足还请见谅");
			}
			System.out.println("Product:"+product+"\n\n");
			QueryRunner runner = new QueryRunner();
			String sql = "UPDATE PRODUCT SET TOTALSALENUM=? ,QUANTITY=? WHERE ID=? ";
			Object[] params ={product.getTotalSaleNum()+totalSale ,product.getQuantity()-totalSale, productId}; 
			runner.update(JdbcUtils.getConnection(), sql, params);
			JdbcUtils.commitTranscation();
		}catch(Exception e){
			JdbcUtils.closebleTranscation();
		}
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public Product findProduct(String id) {
		try {
			QueryRunner runner = new QueryRunner();
			String sql = "SELECT * FROM PRODUCT WHERE ID=?";
			Product product = (Product) runner.query(JdbcUtils.getConnection(), sql, id, new BeanHandler(Product.class));
			sql = "SELECT c.* FROM CATEGORY c , PRODUCT p WHERE c.ID=p.CATEGORY_ID AND p.ID=?";
			Category category = (Category) runner.query(JdbcUtils.getConnection(), sql, id, new BeanHandler(Category.class));
			product.setCategory(category);
			return product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//String where =  "where category_id=?"
	/*
	 * 用户带where条件过来，则该方法返回分类下面的分页数 如果没带where条件，则返回所有书的分页数据
	 *
	 * where条件的格式：String where = "where category_id=?"
	 *
	 * param=?
	 */
	private List<Product> findlScopeByProduct(int startIndex, int pageSize,
			String where, Object param) {
		try {
			QueryRunner runner = new QueryRunner();
			String sql ;
			Object[] params;
			/**
			 * 一个新的问题为甚么 condition expression if(where==null && where.trim().equals(""))会获取不到值 ， 唯一能解决该问题就是去反向判断
			 */
			if(where != null && !where.trim().equals("")){
	
				sql="SELECT * FROM PRODUCT "+where+" ORDER BY TOTALSALENUM LIMIT ?,? ";
				params=new Object[]{param,startIndex , pageSize};
				List<Product> products = (List<Product>) runner.query(JdbcUtils.getConnection(), sql,params, new BeanListHandler(Product.class));
				System.out.println(products +"where Condition establishment");
				for(Product product : products){
					sql = "SELECT c.* FROM CATEGORY c , PRODUCT p WHERE c.ID=p.CATEGORY_ID AND p.ID=?";
					Category category = (Category) runner.query(JdbcUtils.getConnection(), sql, product.getId(), new BeanHandler(Category.class));
					product.setCategory(category);
				}
				return products;
			}else{
				sql="SELECT * FROM PRODUCT  ORDER BY TOTALSALENUM LIMIT ?,?";
				params=new Object[]{startIndex , pageSize};
				List<Product> products = (List<Product>) runner.query(JdbcUtils.getConnection(), sql,params, new BeanListHandler(Product.class));
				System.out.println(products+"where Condition  not establishment");
				for(Product product : products){
					sql = "SELECT c.* FROM CATEGORY c , PRODUCT p WHERE c.ID=p.CATEGORY_ID AND p.ID=?";
					Category category = (Category) runner.query(JdbcUtils.getConnection(), sql, product.getId(), new BeanHandler(Category.class));
					product.setCategory(category);
				}
				return products;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * 下面和上面的概念思想相同，同自继承一脉
	 */
	private  int findProductTotalRecord(String where, Object param) {
		try {
			Connection connection = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql;
			if(where != null ){
				  sql = "SELECT COUNT(*) FROM  PRODUCT "+where;
				 long totalRecord =  (long) runner.query(connection, sql,  param ,new ScalarHandler());//...new ScalarHandler() ，查询出的是长整型整数
				 return new Long(totalRecord).intValue();
			}else{
				sql = "SELECT COUNT(*) FROM PRODUCT";
				 long totalRecord =  (long) runner.query(connection, sql,  new ScalarHandler());//...new ScalarHandler() ，查询出的是长整型整数
				 return new Long(totalRecord).intValue();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

/**
 * 
 * @param startIndex
 * @param pageSize
 * @param whereAssociate  
 * 								whereAssociate equals == (where category_id=?)
 * @param conditionVariable 
 * 								?== (conditionVariable) 
 * @return
 */
	@Override
	public QueryResult getProductQueryResult(int startIndex, int pageSize, QueryPaging queryInfo){
		List<Product> productList = findlScopeByProduct( startIndex, pageSize,queryInfo.getWhereAssociate(), queryInfo.getConditionVariable());
		//某个条件的记录结果
		int recodeResult = findProductTotalRecord(queryInfo.getWhereAssociate(), queryInfo.getConditionVariable()); 
		
		QueryResult queryResult = new QueryResult();
		queryResult.setList(productList);
		queryResult.setRecodeResult(recodeResult);
		return queryResult;
	}

	@Override
	public void deleteProduct(String id){
		try{
			Connection connection = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql="DELETE FROM PRODUCT WHERE ID=?";
			runner.update(connection, sql, id);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}