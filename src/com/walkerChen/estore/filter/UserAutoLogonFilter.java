package com.walkerChen.estore.filter;

import com.walkerChen.estore.bean.substance.User;
import com.walkerChen.estore.commonUtils.JdbcUtils;
import com.walkerChen.estore.commonUtils.ServletUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Created by cbh12 on 9/26/2016.
 */
@WebFilter("/UserAutoLogonFilter")
@SuppressWarnings("all")
public class UserAutoLogonFilter implements Filter {
    public void destroy() {
    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //1. 先检查用户是否已登陆，没登陆才自动登陆
        User user  = (User)request.getAttribute("user");
        if(user != null){
            chain.doFilter(request , response);
            return;
        }
        //2. 没登陆，再执行自动登陆逻辑
        Cookie cookie=null;
        Cookie[] cookies = request.getCookies();
        for(int i = 0 ;cookies!=null && i<cookies.length;i++){
            if(cookies[i].getName().equals("autoLogon")){
                cookie=cookies[i];
            }
        }
        //2.1 看用户有没有带自动登陆的cookie
        if(cookie==null){
            chain.doFilter(request , response);
            return ;
        }
        String encipherCookieValue = cookie.getValue();
        if(encipherCookieValue==null){
            chain.doFilter(request , response);
            return ;
        }
        //2.2 用户带了自动登陆的cookie，检查cookie的有效性
        String[] arraysValue = encipherCookieValue.split(":");
        if(arraysValue.length!=3){
            chain.doFilter(request , response);
            return ;
        }
        String username = arraysValue[0];
        User userByRetrieval = JdbcUtils.retrievalValidateLogonObject(User.class,new String[]{username},new Object[]{username});
        if(userByRetrieval==null){
            chain.doFilter(request , response);
            return ;
        }
        //2.3 cookie有效，检查cookie的时间有效性
        String deadlineStr = arraysValue[1];
        Long deadline = Long.parseLong(deadlineStr);
        if(System.currentTimeMillis()>deadline){
            chain.doFilter(request , response);
            return ;
        }
        //2.4 检查是否与加密的cookie值相同， 以防cookie的时间被更改过
        String encipherValue = arraysValue[2];
        if(!encipherCookieValue.equals(new ServletUtils().encipherValue(deadline,
                userByRetrieval.getUsername(),userByRetrieval.getPassword()))){
            chain.doFilter(request , response);
            return ;
        }
        //3 执行登陆
        request.getSession().setAttribute("user", userByRetrieval);//用户是否已登陆，没登陆才自动登陆,因此session的user有的还回去！
        System.out.println(userByRetrieval+"================================>怀抱九州，拦明月！");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config)
            throws ServletException {

    }

}
