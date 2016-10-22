package com.walkerChen.estore.dao;

import com.walkerChen.estore.bean.substance.Category;

import java.util.List;


public interface CategoryDao {

	public abstract void addCategory(Category category);

	public abstract Category findCategory(String id);

	public abstract List<Category> findAllCategory();

	public abstract void deleteCategory(String id);

}