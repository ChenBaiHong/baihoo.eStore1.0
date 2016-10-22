package com.walkerChen.estore.dao.impl;

import com.walkerChen.estore.bean.substance.Category;
import com.walkerChen.estore.dao.CategoryDao;
import com.walkerChen.estore.commonUtils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;


public class CategoryDaoImpl implements CategoryDao {

	@Override
	public void addCategory(Category category) {
		try {
			Connection connection = JdbcUtils.getConnection();
			QueryRunner queryRunner = new QueryRunner();
			String sql = "INSERT INTO CATEGORY (id , name , description)VALUES(?,?,?)";
			Object [] params = {category.getId() , category.getName(), category.getDescription()};
			queryRunner.update(connection, sql, params);
		} catch (Exception e) {
			throw new SecurityException(e);
		}
	}


	@Override
	@SuppressWarnings("deprecation")
	public Category findCategory(String id) {
		try {
			Connection connection = JdbcUtils.getConnection();
			QueryRunner queryRunner = new QueryRunner();
			String sql = "SELECT * FROM CATEGORY WHERE id=?";
			Category category = (Category) queryRunner.query(connection, sql, id, new BeanHandler(Category.class));
			return category;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Category> findAllCategory() {
		try {
			Connection connection = JdbcUtils.getConnection();
			QueryRunner queryRunner = new QueryRunner();
			String sql = "SELECT * FROM CATEGORY";
			List<Category> categorys = (List<Category>) queryRunner.query(connection, sql,  new BeanListHandler(Category.class));
			return categorys;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteCategory(String id) {
		try {
			Connection connection = JdbcUtils.getConnection();
			QueryRunner queryRunner = new QueryRunner();
			String sql = "DELETE  FROM CATEGORY WHERE ID=?";
			queryRunner.update(connection, sql, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}