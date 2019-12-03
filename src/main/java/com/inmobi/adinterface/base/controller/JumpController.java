package com.inmobi.adinterface.base.controller;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLDecoder;


/**
 * @program: jx
 * @description: 跳转页面控制层
 * @author: Mr.Yan
 * @create: 2018-09-19 15:59
 **/
@Controller
@RequestMapping(value = "/open")
public class JumpController {

    //  跳转 页面 公共方法  eg:/entryReport=entryReport
    //@RequiresRoles(value = {"system:user:view","system:role:view","system:menu:view"})
    @RequestMapping(value = "/{modelName:.*+}")
    public ModelAndView toEntryReport(@PathVariable("modelName") String modelName,
                                      ModelAndView mv) throws UnsupportedEncodingException {
        String viewName = "";
        String paramStr = "";
        modelName = URLDecoder.decode(modelName,"UTF-8");
        //  存在参数
        if (modelName.contains("@")) {
            String[] splitURL = modelName.split("@");
            //  存在 截取
            viewName = splitURL[0];
            paramStr = splitURL[1];
            if (viewName.contains("!")) {
                viewName = viewName.replace("!", "\\");
                mv.setViewName(viewName);
            }
            //  截取^ 参数 month=xx&name=xx
            String[] paramArr = paramStr.split("&");

            for (String paramBean : paramArr) {
                //  截取^ 参数 [month=xx][name=xx]
                // 以=结尾 month=
                String[] param = paramBean.split("=");
                System.out.println(paramBean);
                if(paramBean.endsWith("=")){
                    param = (String[]) arrayAddLength(param,1);
                    param[1] = "";
                }
                mv.addObject(param[0], URLDecoder.decode(param[1],"UTF-8"));
            }

        } else {
            viewName = modelName;
            if (viewName.contains("!")) {
                viewName = viewName.replace("!", "\\");
                mv.setViewName(viewName);
            }
        }
        return mv;
    }

    /**
     * 增加数组大小
     * @param oldArray
     * @param addLength
     * @return
     */
    public static Object arrayAddLength(Object oldArray,int addLength) {
        Class c = oldArray.getClass();
        if(!c.isArray()){
            return null;
        }
        Class componentType = c.getComponentType();
        int length = Array.getLength(oldArray);
        int newLength = length + addLength;
        Object newArray = Array.newInstance(componentType,newLength);
        System.arraycopy(oldArray,0,newArray,0,length);
        return newArray;
    }


}
