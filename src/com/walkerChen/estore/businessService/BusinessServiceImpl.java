package com.walkerChen.estore.businessService;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import com.walkerChen.estore.bean.backstage.Admin;
import com.walkerChen.estore.bean.backstage.Privilege;
import com.walkerChen.estore.bean.backstage.PrivilegeInject;
import com.walkerChen.estore.bean.backstage.Role;
import com.walkerChen.estore.bean.page.QueryPaging;
import com.walkerChen.estore.bean.page.QueryResult;
import com.walkerChen.estore.bean.page.ShoppingCart;
import com.walkerChen.estore.bean.page.ShoppingItem;
import com.walkerChen.estore.bean.substance.Category;
import com.walkerChen.estore.bean.substance.OrderItem;
import com.walkerChen.estore.bean.substance.Orders;
import com.walkerChen.estore.bean.substance.Product;
import com.walkerChen.estore.bean.substance.User;
import com.walkerChen.estore.businessFactory.DaoFactory;
import com.walkerChen.estore.dao.AdminDao;
import com.walkerChen.estore.dao.CategoryDao;
import com.walkerChen.estore.dao.OrdersDao;
import com.walkerChen.estore.dao.PrivilegeDao;
import com.walkerChen.estore.dao.ProductDao;
import com.walkerChen.estore.dao.RoleDao;
import com.walkerChen.estore.dao.UserDao;
import com.walkerChen.estore.dao.pagingBeanOfAdmin.AdminPageBean;
import com.walkerChen.estore.dao.pagingBeanOfAdmin.PageInfoOfAdmin;
import com.walkerChen.estore.dao.pagingBeanOfAdmin.QueryResultOfAdmin;
import com.walkerChen.estore.dao.pagingBeanOfRole.PageInfo;
import com.walkerChen.estore.dao.pagingBeanOfRole.RolePageBean;
import com.walkerChen.estore.dao.pagingBeanOfRole.RoleResult;
public class BusinessServiceImpl implements BusinessService{
			AdminDao adminDao = DaoFactory.newInstance().createDataAccessibleFactoryByInterface(AdminDao.class);
			CategoryDao categoryDao= DaoFactory.newInstance().createDataAccessibleFactoryByInterface(CategoryDao.class);
			OrdersDao ordersDao= DaoFactory.newInstance().createDataAccessibleFactoryByInterface(OrdersDao.class);
			PrivilegeDao privilegeDao= DaoFactory.newInstance().createDataAccessibleFactoryByInterface(PrivilegeDao.class);
			ProductDao productDao= DaoFactory.newInstance().createDataAccessibleFactoryByInterface(ProductDao.class);
			RoleDao	roleDao= DaoFactory.newInstance().createDataAccessibleFactoryByInterface(RoleDao.class);
			UserDao userDao= DaoFactory.newInstance().createDataAccessibleFactoryByInterface(UserDao.class);

	/*****************************************************************************
	 *管理员查询管理
	 * @param admin
	 *****************************************************************************/
	@Override
	@PrivilegeInject(chineseValue="添加管理员",englishKey = "addAdmin")
	public void addAdmin(Admin admin) {
		adminDao.addAdmin(admin);		
	}

	@Override
	public Admin findAdmin(String id) {
		return adminDao.findAdmin(id);
	}

	@Override
	public Admin findAdmin(String username, String password) {
		return adminDao.findAdmin(username, password);
	}

	@Override
	@PrivilegeInject(chineseValue = "查询所有管理员",englishKey = "findAllAdmin")
	public List<Admin> findAllAdmin() {
		return adminDao.findAllAdmin();
	}

	@Override
	@PrivilegeInject(chineseValue = "更新管理员",englishKey = "updateAdmin")
	public void updateAdmin(Admin admin){
		adminDao.updateAdmin(admin);
	}
	@Override
	public Set<Privilege> findAdminAllPrivilegeByRole(String adminId){
		Set<Privilege> privilegeSet = new HashSet<Privilege>();
		Admin admin = adminDao.findAdmin(adminId);
		Set<Role> roleSet = admin.getRoleSet();
		Iterator <Role>iterator = roleSet.iterator();
		while(iterator.hasNext()){
			Role role = iterator.next();
			privilegeSet.addAll(role.getPrivilegeSet());
		}
		return privilegeSet;
	}
	@Override
	public List<String> findAllAdminName(){
		return adminDao.findAllAdminName();
	}
	@Override
	public AdminPageBean queryAdmiPageBean(PageInfoOfAdmin pageInfo){
		QueryResultOfAdmin resultAdmin = adminDao.queryAdminResult(pageInfo);
		AdminPageBean adminPageBean = new AdminPageBean();
		
		adminPageBean.setAdminList(resultAdmin.getAdminList());
		adminPageBean.setCurrentPage(pageInfo.getCurrentPage());
		adminPageBean.setPageSize(pageInfo.getPageSize());
		adminPageBean.setQueryStartIndex(pageInfo.getQueryStartIndex());
		adminPageBean.setTotalRecordOfAdmin(resultAdmin.getTotalRecordOfAdmin());
		
		return adminPageBean;
	}
	@PrivilegeInject(chineseValue = "删除管理员",englishKey = "deleteAdmin")
	public  void deleteAdmin(String adminId){
		adminDao.deleteAdmin(adminId);
	}
	@Override
	public List<Admin> searchLikeAdmin(String searchCondition){
		return adminDao.searchLikeAdmin(searchCondition);
	}
	@Override
	public void alterAdminName(String id, String newName){
		adminDao.alterAdminName(id, newName);
	}
	/*****************************************************************************
	 * 分类查询管理
	 * @param category
	 ******************************************************************************/
	@Override
	@PrivilegeInject(chineseValue = "添加分类",englishKey = "addCategory")
	public void addCategory(Category category) {
		categoryDao.addCategory(category);		
	}

	@Override
	public Category findCategory(String id) {
		return categoryDao.findCategory(id);
	}

	@Override
	@PrivilegeInject(chineseValue = "查询所有分类",englishKey = "findAllCategory")
	public List<Category> findAllCategory() {
		return categoryDao.findAllCategory();
	}

	@Override
	@PrivilegeInject(chineseValue = "删除分类",englishKey = "deleteCategory")
	public void deleteCategory(String id) {
		categoryDao.deleteCategory(id);
		
	}
	/*****************************************************************************
	 * 订单查询管理
	 *
	 * 一定要记住什么是订单就是用户和购物项组成的购物车，合起来的就一个订单
	 * @param orders
	 *****************************************************************************/
	@Override
	public void saveOrders(User user, ShoppingCart shoppingCart) {
		Orders orders = new Orders();
		orders.setId(UUID.randomUUID().toString());
		orders.setMoney(shoppingCart.getTotalMoney());
		orders.setOrderTime(new Date());
		orders.setState(false);
		orders.setUser(user);
		orders.setReceiverinfo(null);
		Set<OrderItem> orderItemSet = new HashSet<OrderItem>();
		if(shoppingCart!=null){
			Map<String , ShoppingItem> shopItemMap = shoppingCart.getMap();
			Set<Entry<String , ShoppingItem>> entrySet=shopItemMap.entrySet();
			Iterator<Entry<String, ShoppingItem>> iterator = entrySet.iterator();
			while(iterator.hasNext()){
				Entry<String, ShoppingItem> entry = iterator.next();
				ShoppingItem shopItem = entry.getValue();
				OrderItem orderItem = new OrderItem();
				orderItem.setId(UUID.randomUUID().toString());
				orderItem.setProduct(shopItem.getProduct());
				orderItem.setBuyQuantity(shopItem.getBuyQuantity());
				orderItem.setPrice(shopItem.getPrice());
				orderItemSet.add(orderItem);
				//更新这产品在数据中的保存数量和销售数量，销售数量就是购物项里的购买的数量
				productDao.remainderProductQuantity(entry.getKey(),entry.getValue().getBuyQuantity());
			}
		}
		orders.setOrderItems(orderItemSet);
		ordersDao.addOrders(orders);
		shoppingCart.getMap().clear();
/*		System.out.println(shoppingCart.getMap().size());*/
	}

	@Override
	public Orders findOrders(String id) {
		return ordersDao.findOrders(id);
	}

	@Override
	public List<Orders> findStateOrders(boolean state) {
		return ordersDao.findStateOrders(state);
	}

	@Override
	public void updateOrderState(String id, boolean state) {
		ordersDao.updateOrderState(id, state);		
	}
	/*****************************************************************************
	 * 权限查询管理
	 * @param privilege
	 *****************************************************************************/
	@Override
	@PrivilegeInject(chineseValue = "分配权限",englishKey = "addPrivilege")
	public void addPrivilege(Privilege privilege) {
		privilegeDao.addPrivilege(privilege);		
	}
	@Override
	public void updatePrivilege(Privilege privilege){
		privilegeDao.updatePrivilege(privilege);
	}
	@Override
	public Privilege findPrivilege(String id) {
		return privilegeDao.findPrivilege(id);
	}
	@Override
	public  Privilege searchPrivilege(String name){
		return privilegeDao.searchPrivilege(name);
	}
	@Override
	@PrivilegeInject(chineseValue = "查看所有权限名称",englishKey = "findAllPrivilege")
	public List<Privilege> findAllPrivilege() {
		return privilegeDao.findAllPrivilege();
	}

	@Override
	@PrivilegeInject(chineseValue = "删除权限",englishKey = "deletePrivilege")
	public void deletePrivilege(String id) {
		privilegeDao.deletePrivilege(id);		
	}
	public  List<Privilege> searchLikePrivilege(String searchCondition){
		return privilegeDao.searchLikePrivilege(searchCondition);
	}
	/*****************************************************************************
	 * 产品查询管理
	 * @param product
	 *****************************************************************************/
	@Override
	@PrivilegeInject(chineseValue = "添加产品",englishKey = "addProduct")
	public void addProduct(Product product) {
		productDao.addProduct(product);		
	}

	@Override
	public Product findProduct(String id) {
		return productDao.findProduct(id);
	}

	@Override
	public QueryResult getProductQueryResult(QueryPaging queryInfo) {
		int startIndex = queryInfo.getStartIndex();
		int  pageSize = queryInfo.getPageSize();
		return productDao.getProductQueryResult(startIndex, pageSize, queryInfo);
	}

	@Override
	@PrivilegeInject(chineseValue = "删除产品",englishKey = "deleteProduct")
	public void deleteProduct(String id) {
		productDao.deleteProduct(id);		
	}
	/*****************************************************************************
	 * 角色查询管理
	 * @param role
	 *****************************************************************************/
	@Override
	@PrivilegeInject(chineseValue = "添加角色",englishKey = "addRole")
	public void addRole(Role role) {
		roleDao.addRole(role);		
	}

	@Override
	public Role findRole(String id) {
		return roleDao.findRole(id);
	}
	@Override
	public Boolean isExistRole(String name){
		return roleDao.isExistRole(name);
	}
	@Override
	public RolePageBean queryRolePageBean(PageInfo pageInfo){
		RoleResult roleResult = roleDao.queryRoleResult(pageInfo);
		RolePageBean rolePageBean = new RolePageBean();
		rolePageBean.setCurrentPage(pageInfo.getCurrentPage());
		rolePageBean.setPageSize(pageInfo.getPageSize());
		rolePageBean.setStartIndex(pageInfo.getStartIndex());
		rolePageBean.setTotalRecord(roleResult.getTotalRecord());
		rolePageBean.setRoleList(roleResult.getRoleList());
		return rolePageBean;
	}
	@Override
	public List<Role> findAllRole() {
		return roleDao.findAllRole();
	}

	@Override
	@PrivilegeInject(chineseValue = "删除角色",englishKey = "deleteRole")
	public void deleteRole(String id) {
		roleDao.deleteRole(id);		
	}

	@Override
	@PrivilegeInject(chineseValue = "更改角色权限",englishKey = "updateOfRolePrivilege")
	public void updateOfRolePrivilege(String roleId,String description,List<Privilege> privilegeList) {		
		roleDao.updateOfRolePrivilege(roleId,description, privilegeList);
	}
	@Override
	public void updateRoleName(String name,String roleId){
		roleDao.updateRoleName(name, roleId);
	}
	@Override
	public List<Role> pagingSearchRole(int startIndex , int pagingSize){
		return roleDao.pagingSearchRole(startIndex, pagingSize);
	}
	
	/*****************************************************************************
	 * 用户查询管理
	 * @param user
	 *****************************************************************************/
	@Override
	public void addUser(User user) {
		userDao.addUser(user);		
	}

	@Override
	@PrivilegeInject(chineseValue = "查询用户",englishKey = "findUser")
	public User findUser(String id) {
		return userDao.findUser(id);
	}

	@Override
	public User findUser(String username, String password) {
		return userDao.findUser(username , password);
	}

	@Override
	@PrivilegeInject(chineseValue = "查询所有用户",englishKey = "findAllUser")
	public List<User> findAllUser(boolean state) {
		return userDao.findAllUser(state);
	}
	@Override
	public  List<User> searchLikeUser(Object conditionParam){
		return userDao.searchLikeUser(conditionParam);
	}
}
