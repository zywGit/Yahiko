package com.inmobi.adinterface.config.shiro;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.Configuration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** 
* @Description: shiro 与 freemarker 整合ftl 页面标签 须写
* @Param:  
* @return:  
* @Author: Mr.Yan 
* @Date: 2018/12/10 
*/

@Component
public class FreeMarkerConfig implements InitializingBean {
    @Autowired
    private Configuration configuration;


    @Override
    public void afterPropertiesSet() throws Exception {
        // 加上这句后，可以在页面上使用shiro标签
        configuration.setSharedVariable("shiro", new ShiroTags());
        configuration.setNumberFormat("#");//防止页面输出数字,变成2,000
    }


}
