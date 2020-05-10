package com.hh.factory;


import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

/**
 * 一个创建Bean对象的工厂
 *
 * Bean：在计算机英语中，有可重用组件的含义。
 * JavaBean：用java语言编写的可重用组件。
 *      javabean >  实体类
 *
 *   它就是创建我们的service和dao对象的。
 *
 *   第一个：需要一个配置文件来配置我们的service和dao
 *           配置的内容：唯一标识=全限定类名（key=value)
 *   第二个：通过读取配置文件中配置的内容，反射创建对象
 *
 *   配置文件可以是xml也可以是properties
 */
public class BeanFactory {

    private static Properties properties;

    /**
     * 定义一个Map,用于存放我们要创建的对象。我们把它称之为容器
     */
    private static HashMap<String, Object> beans;

    /*使用静态代码块为Properties对象赋值*/
    static {
        properties = new Properties();
        InputStream resourceAsStream = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
        try {
            properties.load(resourceAsStream);
            // 实例化容器
            beans = new HashMap<String, Object>();
            // 获取配置文件中所有的key
            Enumeration<Object> keys = properties.keys();
            while (keys.hasMoreElements()){
                String key = keys.nextElement().toString();
                String beanPath = properties.getProperty(key);
                Object bean = Class.forName(beanPath).newInstance();
                beans.put(key, bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据Bean的名称获取bean对象
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName){

        return beans.get(beanName);
    }

    /**
     * 根据Bean的名称获取bean对象
     * @param beanName
     * @return
     */
    /*public static Object getBean(String beanName){

        Object bean = null;
        String beanPath = properties.getProperty(beanName);

        try {
            bean = Class.forName(beanPath).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }*/
}
