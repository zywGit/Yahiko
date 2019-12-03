package com.inmobi.adinterface.base.controller;

import eu.bitwalker.useragentutils.UserAgent;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.SQLReady;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author : Mr.Yan
 * @program : Spring-Sky
 * @create : 2019-04-01 16:31
 * @description : 公共Controller 控制层基类
 */
public class BaseController {

    /**
     * 类转换器
     * @param request
     * @param binder
     * @throws Exception
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor dateEditor = new CustomDateEditor(format, true);
        binder.registerCustomEditor(Date.class, dateEditor);

    }

    protected Object getAttribute(String attributeName) {
        return this.getRequest().getAttribute(attributeName);
    }

    protected void setAttribute(String attributeName, Object object) {
        this.getRequest().setAttribute(attributeName, object);
    }

    protected Object getSession(String attributeName) {
        return this.getRequest().getSession(true).getAttribute(attributeName);
    }

    protected void setSession(String attributeName, Object object) {
        this.getRequest().getSession(true).setAttribute(attributeName, object);
    }

    protected HttpServletRequest getRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        return ((ServletRequestAttributes) ra).getRequest();
    }

    protected HttpServletResponse getResponse() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        return ((ServletRequestAttributes) ra).getResponse();
    }

    protected HttpSession getSession() {
        return this.getRequest().getSession(true);
    }

    protected String getParameter(String paraName) {
        return this.getRequest().getParameter(paraName);
    }


    /**
     * 设置 cookie
     *
     * @param cookieName
     * @param value
     * @param age
     */
    protected void setCookie(String cookieName, String value, int age) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setMaxAge(age);
        // cookie.setHttpOnly(true);
        cookie.setPath("/");
        this.getResponse().addCookie(cookie);
    }

    /**
     * 获取所有cookie 数组
     *
     * @return
     */
    protected Cookie[] getCookie() {
        return this.getRequest().getCookies();//这样便可以获取一个cookie数组
    }

    /**
     * 获取指定cookie
     *
     * @param cookieName
     * @return
     */
    protected Cookie getCookie(String cookieName) {
        Cookie[] cookies = this.getCookie();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if (name.equals(cookieName)) {
                return cookie;
            }
        }
        return null;
    }

    /**
     * 方法名称: getUUID<br>
     * 描述：获得唯一标识(目前用于验证表单提交唯一性)<br>
     */
    protected String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取服务器ip地址
     *
     * @return
     */

    protected String getIp() {
        InetAddress address;
        String serverIpAddress = null;
        try {
            address = InetAddress.getLocalHost(); //获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
            serverIpAddress = address.getHostAddress();//192.168.0.121
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return serverIpAddress;
    }

    /**
     * 获取浏览器名称
     *
     * @return
     */
    public String getBrowser() {
        try {
            final UserAgent userAgent = UserAgent.parseUserAgentString(this.getRequest().getHeader("User-Agent"));
            return userAgent.getBrowser().getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "unKnow";
    }

    /** 验证是否是移动端 */
    public boolean JudgeIsMoblie() {
        HttpServletRequest request = this.getRequest();
        boolean isMoblie = false;
        String[] mobileAgents = { "iphone", "android","ipad", "phone", "mobile", "wap", "netfront", "java", "opera mobi",
                "opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod",
                "nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
                "docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",
                "techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem",
                "wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
                "pantech", "gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320",
                "240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac",
                "blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs",
                "kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi",
                "mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port",
                "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem",
                "smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v",
                "voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
                "Googlebot-Mobile" };
        if (request.getHeader("User-Agent") != null) {
            String agent=request.getHeader("User-Agent");
            for (String mobileAgent : mobileAgents) {
                if (agent.toLowerCase().indexOf(mobileAgent) >= 0&&agent.toLowerCase().indexOf("windows nt")<=0 &&agent.toLowerCase().indexOf("macintosh")<=0) {
                    isMoblie = true;
                    break;
                }
            }
        }
        return isMoblie;
    }

}
