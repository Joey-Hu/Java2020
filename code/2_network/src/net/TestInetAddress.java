package net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: huhao
 * @time: 2020/3/12 9:52
 * @desc:
 */
public class TestInetAddress {
    public static void main(String[] args) throws UnknownHostException {
//        MyClass mc = MyClass.getMyClassInstance();

        // 获得本机ip地址对象
        InetAddress localhost = InetAddress.getLocalHost();

        // 获得任意主机地址对象 InetAddress.getByName()
        InetAddress baidu = InetAddress.getByName("www.baidu.com");

        // 一个服务器可能对应多个ip地址
        InetAddress[] adds = InetAddress.getAllByName("www.baidu.com");

        // 获取ip地址字符串
        System.out.println(localhost.getHostAddress());

        // 获得ip地址对应主机名称
        System.out.println(localhost.getHostName());

        System.out.println(baidu.getHostAddress());

        for(InetAddress address:adds){
            System.out.println(address.getHostAddress());
            System.out.println(address.getHostName());

        }
    }
}

//class MyClass{
//
//    private MyClass(){
//
//    }
//
//    public static MyClass getMyClassInstance(){
//        return new MyClass();
//    }
//}
