package com.inmobi.adinterface.config.interceptor;


import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
* @Description: 登录拦截器
* @Param:  
* @return:  
* @Author: Mr.Yan 
* @Date: 2018/12/11 
*/

public class SecurityInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object)
            throws Exception {
        return true;
    }
}
