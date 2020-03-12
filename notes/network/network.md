## Network

!["OSI.png"]()

### 1. InetAddress 类

表示互联网协议（IP）地址对象，封装了与该IP地址相关的所有信息，并提供获取信息的常用方法。

```java
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
// 对于方法返回值类型是对象本身的情况
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

```
### 2. Socket 编程
