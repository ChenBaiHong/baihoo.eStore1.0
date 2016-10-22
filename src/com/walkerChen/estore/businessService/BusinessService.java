package com.walkerChen.estore.businessService;

import java.util.List;
import java.util.Set;

import com.walkerChen.estore.bean.backstage.Admin;
import com.walkerChen.estore.bean.backstage.Privilege;
import com.walkerChen.estore.bean.backstage.Role;
import com.walkerChen.estore.bean.page.QueryPaging;
import com.walkerChen.estore.bean.page.QueryResult;
import com.walkerChen.estore.bean.page.ShoppingCart;
import com.walkerChen.estore.bean.substance.Category;
import com.walkerChen.estore.bean.substance.Orders;
import com.walkerChen.estore.bean.substance.Product;
import com.walkerChen.estore.bean.substance.User;
import com.walkerChen.estore.dao.pagingBeanOfAdmin.AdminPageBean;
import com.walkerChen.estore.dao.pagingBeanOfAdmin.PageInfoOfAdmin;
import com.walkerChen.estore.dao.pagingBeanOfRole.PageInfo;
import com.walkerChen.estore.dao.pagingBeanOfRole.RolePageBean;



public interface BusinessService {
	/*****************************************************************************
	 *管理员查询管理
	 * @param admin
	 *****************************************************************************/

	public abstract void addAdmin(Admin admin);

	public abstract Admin findAdmin(String id);

	public abstract Admin findAdmin(String username, String password);

	public abstract List<Admin> findAllAdmin();

	public void updateAdmin(Admin admin);
	
	public abstract Set<Privilege> findAdminAllPrivilegeByRole(String adminId);
	
	List<String> findAllAdminName();
	
	public AdminPageBean queryAdmiPageBean(PageInfoOfAdmin pageInfo);
	
	void deleteAdmin(String adminId);
	
	public List<Admin> searchLikeAdmin(String searchCondition);
	
	public void alterAdminName(String id, String newName);
	/*****************************************************************************
	 * 分类查询管理
	 * @param category
	 ******************************************************************************/

	public abstract void addCategory(Category category);

	public abstract Category findCategory(String id);

	public abstract List<Category> findAllCategory();

	public abstract void deleteCategory(String id);


	/*****************************************************************************
	 * 订单查询管理
	 * @param orders
	 *****************************************************************************/
	public abstract void saveOrders(User user, ShoppingCart shoppingCart);

	public abstract Orders findOrders(String id);

	/*
	 * state:true:已发货 state:false;未发货
	 */
	public abstract List<Orders> findStateOrders(boolean state);

	public abstract void updateOrderState(String id, boolean state);
	/*****************************************************************************
	 * 权限查询管理
	 * @param privilege
	 *****************************************************************************/

	public abstract void addPrivilege(Privilege privilege);
	public abstract void updatePrivilege(Privilege privilege);
	public abstract Privilege findPrivilege(String id);
	public abstract Privilege searchPrivilege(String name);
	public abstract List<Privilege> findAllPrivilege();
	public abstract void deletePrivilege(String id);
	public abstract List<Privilege> searchLikePrivilege(String searchCondition);
	/*****************************************************************************
	 * 产品查询管理
	 * @param product
	 *****************************************************************************/

	public abstract void addProduct(Product product);

	public abstract Product findProduct(String id);

	public abstract QueryResult getProductQueryResult(QueryPaging queryInfo);

	public abstract void deleteProduct(String id);

	/*****************************************************************************
	 * 角色查询管理
	 * @param role
	 *****************************************************************************/

	public abstract void addRole(Role role);

	public abstract Role findRole(String id);

	public abstract Boolean isExistRole(String name);
	
	public abstract List<Role> findAllRole();

	public abstract void deleteRole(String id);
	
	void updateRoleName(String name, String roleId);
	
	public void updateOfRolePrivilege(String roleId, String description, List<Privilege> privilegeList);
	
	public RolePageBean queryRolePageBean(PageInfo pageInfo);
	
	List<Role> pagingSearchRole(int startIndex, int pagingSize);
	/*****************************************************************************
	 * 用户查询管理
	 * @param user
	 *****************************************************************************/
	
	public abstract void addUser(User user);

	public abstract User findUser(String id);

	public abstract User findUser(String username, String password);

	public abstract List<User> findAllUser(boolean state);

	public abstract List<User> searchLikeUser(Object conditionParam);

}
