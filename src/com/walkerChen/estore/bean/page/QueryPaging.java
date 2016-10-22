package com.walkerChen.estore.bean.page;

/**
 * 查询分页
 */
public class QueryPaging {
	private int currentPage=1;
	private int pageSize=3;//当前页面的大小
	private int startIndex;//开始查询下标，默认是0
	private String whereCondition;//category_id , 数据库查询的条件
	private String conditionVariable;//数据库查询条件的参数?
	private String whereAssociate;//数据库查询的条件和查询参数的联系条件 where category_id=?
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	//数据库的查询的开始下表是用户根据当前网页的查询的结果
	public int getStartIndex() {
		startIndex=(this.currentPage-1)*this.pageSize;
		return startIndex;
	}
	public String getWhereCondition() {
		return whereCondition;
	}
	public void setWhereCondition(String whereCondition) {
		this.whereCondition = whereCondition;
	}
	public String getConditionVariable() {
		return conditionVariable;
	}
	public void setConditionVariable(String conditionVariable) {
		this.conditionVariable = conditionVariable;
	}
	public String getWhereAssociate() {
		if(whereCondition!=null){
			whereAssociate="where "+whereCondition+"=?"; //以下联系起来就是完整的表达式 where category_id=?
		}
		return whereAssociate;
	}
}
