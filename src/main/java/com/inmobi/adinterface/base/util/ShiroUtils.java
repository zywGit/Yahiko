package com.inmobi.adinterface.base.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @program: authority
 * @description: shiro 公共类
 * @author: Mr.Yan
 * @create: 2018-12-10 14:03
 **/
public class ShiroUtils {
	public static Subject getSubjct()
	{
		return SecurityUtils.getSubject();
	}

	public static Session getSession()
	{
		return SecurityUtils.getSubject().getSession();
	}
	public static void logout()
	{
		getSubjct().logout();
	}
	/**
	 * 获取登陆用户的信息
	 * @return
	 */
	public static Object getSubjectUser(){
		try {
			Object obj = SecurityUtils.getSubject().getPrincipal();
			return obj;
		}
		catch (Exception e) {
			System.out.println("没有找到登陆用户！");
			return null;
		}
	}
	public static String getIp()
	{
		return getSubjct().getSession().getHost();
	}
}
