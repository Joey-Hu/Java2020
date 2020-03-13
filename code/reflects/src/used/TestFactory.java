package used;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.XMLFormatter;

/**
 * @author: huhao
 * @time: 2020/3/13 20:25
 * @desc: 工厂模式
 */
public class TestFactory {

    public static void main(String[] args) throws Exception {

//        Object factory = factory(2);
//        System.out.println(factory);    // used.Lamp@1b6d3586
//
//        Object factory1 = factory("factory.Fan");
//        System.out.println(factory1);    // used.Fan@4554617c

        // 实现只需要更改配置文件就能创建类的对象
        FileReader fr = new FileReader("config.txt");
        BufferedReader br= new BufferedReader(fr);

        String className = br.readLine();
        Object factory = factory(className);
        System.out.println(factory);    // used.UDisk@1b6d3586

    }

    /**
     * 通用编程
     * @param className
     * @return
     */
    public static Object factory(String className) throws Exception {

        Class c = Class.forName(className);
        return c.newInstance();
    }

    /**
     * 简单工厂实现（不能后期增加）
     */
    public static Object factory(int i){

        Object o = new Object();
        if(i == 1){
            o = new Fan();
        }else if(i == 2){
            o = new Lamp();
        }else if(i == 3){
            o = new UDisk();
        }

        return o;
    }
}

interface USB{
    /**
     * 运作方法
     */
    void service();
}

class Fan implements USB {

    @Override
    public void service() {
        System.out.println("旋转");
    }
}

class Lamp implements USB {

    @Override
    public void service() {
        System.out.println("照明");
    }
}

class UDisk implements USB {

    @Override
    public void service() {
        System.out.println("插拔");
    }
}