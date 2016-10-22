package com.walkerChen.estore.commonUtils;

import com.walkerChen.estore.bean.substance.User;
import sun.misc.BASE64Encoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;

/**
 * Created by cbh12 on 9/26/2016.
 */
public class ServletUtils {

    public void userAutoLogon(HttpServletRequest request, HttpServletResponse response){
        try{
            String username = request.getParameter("logonName");
            String password = request.getParameter("logonPwd");
            int deadline = Integer.parseInt(request.getParameter("deadline"));
            User  user = JdbcUtils.retrievalValidateLogonObject(User.class,new String[]{"username","password"}, new Object[]{username, password});
            if(user!=null){
                request.getSession().setAttribute("user", user);
                Cookie cookie = makeCookie(user,deadline);
                cookie.setMaxAge(deadline);//设置最大缓存时间
                cookie.setPath(request.getContextPath());//路径就是当前web工程
                response.addCookie(cookie);
            }
        }catch(Exception e){
            throw new SecurityException();
        }
    }
    private Cookie makeCookie(User user , int deadline){
        Long currentTime = System.currentTimeMillis();
        String finalCookieEncipherValue = user.getUsername()+":"+(currentTime+deadline)+":"+
                                          encipherValue(currentTime+deadline,user.getUsername(),user.getPassword());
        return new Cookie("autoLogon",finalCookieEncipherValue);
    }
    //提高加密方法的复用性
    public String encipherValue(Long deadline,String...arguments){
        try{
            StringBuilder stringBuilder = new StringBuilder();
            for(int i = 0 ; arguments!=null && i<arguments.length;i++){
                stringBuilder.append(arguments[i]+":");
            }
            if(deadline==null){
                stringBuilder.deleteCharAt(stringBuilder.length()-1);
            }else{
                stringBuilder.append(deadline);
            }
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            byte[] md5Encipher = messageDigest.digest(stringBuilder.toString().getBytes());
            BASE64Encoder base64Encoder = new BASE64Encoder();
            return base64Encoder.encode(md5Encipher);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
