package com.walkerChen.estore.bean.page;

import com.walkerChen.estore.bean.substance.Product;

import java.util.List;



public class PageBean {
	private List<Product> listProduct;
	private int totalRecode;
	private int pageSize;
	private int totalPage;
	private  int currentpage ;
	private int[] pagination;
	public List<Product> getListProduct() {
		return listProduct;
	}
	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}
	public int getTotalRecode() {
		return totalRecode;
	}
	public void setTotalRecode(int totalRecode) {
		this.totalRecode = totalRecode;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	//总共几页数据是根据数据库查询的总记录除以用户给定的每页多少条记录
	public int getTotalPage() {
		if(this.totalRecode%this.pageSize!=0){
			return this.totalPage=this.totalRecode/this.pageSize+1;
		}else{
			return this.totalPage=this.totalRecode/this.pageSize;
		}
	}

	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public int[] getPagination() {
		int startPage;
		int endPage;
		if(this.totalPage<10){
			startPage=1;
			endPage=this.totalPage;
		}else{
			startPage=this.currentpage-4;
			endPage=this.currentpage+5;
			if(startPage<1){
				startPage=1;
				endPage=10;
			}if(endPage>this.totalPage){
				endPage=this.totalPage;
				startPage=this.totalPage-9;
			}
		}
		pagination = new int[endPage-startPage+1]; //防止出现空指针异常，10-1+1
		int index=0;
		for(;startPage<=endPage;startPage++){
			pagination[index++]=startPage;
		}
		return pagination;
	}
}
