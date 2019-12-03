package com.inmobi.adinterface.config.interceptor;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
/** 
* @Description: 登录拦截器 
* @Param:  
* @return:  
* @Author: Mr.Yan 
* @Date: 2018/12/11 
*/

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    /**
     * 登录session key
     */
    public static final String SESSION_KEY = "user";

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    /**
     * 加载静态资源
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**")
                .addResourceLocations("classpath:/templates/");
        super.addResourceHandlers(registry);
    }
    /**
     * 对地址进行拦截
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // 排除配置
        addInterceptor.excludePathPatterns("/login");
        addInterceptor.excludePathPatterns("/**/**.js","/**/**.css","/**/**.ico","/**/**.jpg","/**/**.png");
        addInterceptor.excludePathPatterns("/layui2.4/**","/common/**","/web/**");
        addInterceptor.excludePathPatterns("/unRegister");
        addInterceptor.excludePathPatterns("/error");
        // 拦截配置
        addInterceptor.addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
