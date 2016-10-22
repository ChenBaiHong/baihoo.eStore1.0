package com.walkerChen.estore.controlServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walkerChen.estore.bean.substance.Category;
import com.walkerChen.estore.businessFactory.DaoFactory;
import com.walkerChen.estore.businessService.BusinessService;
import com.walkerChen.estore.commonUtils.ReflectUtils;
import com.walkerChen.estore.commonUtils.SecurityException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cbh12 on 9/27/2016.
 */
@WebServlet("/categoryServlet")
@SuppressWarnings("all")
public class categoryServlet extends HttpServlet {
    private BusinessService businessService= DaoFactory.newInstance().createDataAccessibleFactoryByInterface(BusinessService.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        if("searchCategoryByJSON".equals(method)){
            ReflectUtils.invokeMethodServlet(this.getClass(),method,request , response);
        }
    }
    private void searchCategoryByJSON(HttpServletRequest request, HttpServletResponse response){
        try{
            List<Category> categoryList = businessService.findAllCategory();
            List<String> categoryNames = new ArrayList<String>();
            for(Category category:categoryList){
                categoryNames.add(category.getName());
            }
            ObjectMapper om = new ObjectMapper();
            String categorys = om.writeValueAsString(categoryNames);
            response.setContentType("text/javascript");
            response.getWriter().println(categorys);
        }catch(Exception e){throw new java.lang.SecurityException(e);}
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
