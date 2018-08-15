package com.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.util.Map;

/**
 * @Author:fjc
 * @Description:
 * @Date: 2018/7/10
 **/
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }
    /**
     * 获取当前系统 Web 环境下的Spring applicationContext.
     * @return
     */
    public static ApplicationContext getWebApplicationContext(){
//        return ContextLoader.getCurrentWebApplicationContext();
        return applicationContext;
    }

    /**
     * 根据servletContext 获取Spring applicationContext.
     * @param servletContext
     * @return
     */
    public static ApplicationContext getWebApplicationContext(ServletContext servletContext){
        return WebApplicationContextUtils.getWebApplicationContext(servletContext);
    }

    /**
     * 根据ID获取当前系统 Web 环境下的Spring管理的bean.
     * @param id
     * @return
     */
    public static Object getBean(String id){
        return getWebApplicationContext().getBean(id);
    }

    /**
     * 根据类型获取当前系统 Web 环境下的Spring管理的bean.
     * @param clazz
     * @return
     */
    public static Map getBeanOfType(Class clazz){
        return getWebApplicationContext().getBeansOfType(clazz);
    }


}
