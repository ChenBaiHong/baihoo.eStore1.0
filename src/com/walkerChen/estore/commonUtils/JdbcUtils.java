package com.walkerChen.estore.commonUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.sql.DataSource;
import java.sql.Connection;

public class JdbcUtils {
	private static DataSource dataSource;
	private static ThreadLocal <Connection>threadLocal = new ThreadLocal<Connection>();
	static{
		dataSource = new ComboPooledDataSource();
	}
	public DataSource getDatasource(){
		return dataSource;
	}
	public static Connection getConnection(){
		try{
			Connection connection = threadLocal.get();
			if(connection==null){
				connection = dataSource.getConnection();
				connection.setAutoCommit(false);//每次（crud）增删改查都会对应一次事务的开启，为能够更好的为事务过滤器服务!相当于web层是对数据访问层的处理才会开启事务，从而避免事务链接资源无故消耗（）
				threadLocal.set(connection);
			}
			return connection;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	public  static void startTranscation(){
		try{
			Connection connection= getConnection();
			connection.setAutoCommit(false);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	public static void commitTranscation(){
		try{
			Connection connection = getConnection();
			connection.commit();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	public static void closebleTranscation(){
		try{
			Connection connection =getConnection();
			connection.close();
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			threadLocal.remove();
		}
	}
	@SuppressWarnings("all")
	public static <T> T retrievalValidateLogonObject(Class<T> clazz ,String[]fieldNames,Object[] params){
		try{
			StringBuilder stringBuilder= new StringBuilder();
			stringBuilder.append("SELECT * FROM "+clazz.getSimpleName()+" WHERE ");
			for(int i = 0 ; fieldNames!=null && params!=null && i<fieldNames.length;i++){
				stringBuilder.append(fieldNames[i]+"="+params[i]+" AND ");
			}
			String sql = stringBuilder.toString();
			sql = sql.trim();
			sql = sql.substring(0,sql.length()-3);
			QueryRunner runner= new QueryRunner();
			T bean = (T)runner.query(JdbcUtils.getConnection(), sql, new BeanHandler(clazz));
			return bean;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
