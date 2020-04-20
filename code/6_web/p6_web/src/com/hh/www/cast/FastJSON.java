package com.hh.www.cast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hh.www.entity.Grade;
import com.hh.www.entity.Student;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author: huhao
 * @time: 2020/4/20 17:38
 * @desc:
 */
public class FastJSON {

    @Test
    public void fastJSON() {

        // JSON 字符串    实体类的属性名要和JSON字符串名称完全一致
        String  json1 =  "{'id':1,'name':'JAVAEE-1703','stus':[{'id':101,'name':'刘铭','age':16},{'id':102," +
                "'name':'刘二'," + "'age':18}]}";

        // JOSN 字符串转换为实体类对象（导包）
        // JSON.parseObject()
        Grade grade = JSON.parseObject(json1, Grade.class);

        // 获取stus数组，遍历
        ArrayList<Student> stus = grade.getStus();
        for (Student student : stus) {
            System.out.println(student);
        }

        System.out.println(grade);

        // 实体类转换为JSON字符串
        ArrayList<Student> newStudents = new ArrayList<Student>();
        newStudents.add(new Student(1, "张三丰", 300));
        newStudents.add(new Student(2, "张翠山", 32));
        newStudents.add(new Student(3, "张无忌", 20));

        Grade g = new Grade(2, "Java", newStudents);
        // JSON.toJSONString()
        String s = JSON.toJSONString(g, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
        System.out.println(s);

        //注意事项
        //1.1控制不序列化某些属性  @JSONField(serialize=false)  默认是true
        //1.2控制输出
        // SerializerFeature.PrettyFormat
        // SerializerFeature.WriteMapNullValue  null也输出
        // SerializerFeature.WriteNullStringAsEmpty   null输出""
        // 循环引用检测 SerializerFeature.DisableCircularReferenceDetect ---- 显示相同引用的对象



    }
}
