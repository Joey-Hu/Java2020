/**
 * @author: huhao
 * @time: 2020/3/9 14:56
 * @desc: 创建线程的第一种方法
 * 1. 继承 Thread 类
2. 覆盖 run() 方法
3. 创建子类对象
4. 调用 start() 方法
 */
public class TestCreateThread {
    public static void main(String[] args) {
        MyThread1 mt1 = new MyThread1();
        MyThread2 mt2 = new MyThread2();

//        mt.run()    // 方法调用 ERROR
        mt1.start();    // 启动线程的正确方法，用于多线程
        mt2.start();
    }
}

class MyThread1 extends Thread{
    @Override
    public void run(){
        for(int i=0; i<20; i++){
            System.out.println("MyThread1: " + i);
        }
    }
}

class MyThread2 extends Thread{
    @Override
    public void run(){
        for(int i=0; i<20; i++){
            System.out.println("MyThread2: " + i);
        }
    }
}