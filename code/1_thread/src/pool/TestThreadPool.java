package pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: huhao
 * @time: 2020/3/10 14:06
 * @desc:
 */
public class TestThreadPool {
    public static void main(String[] args) {
        // 线程池（引用）--> Executors工厂类
        ExecutorService es = Executors.newFixedThreadPool(3);
        Runnable task = new MyTask();

        es.submit(task);
        es.submit(task);


    }
}

class MyTask implements Runnable{

    @Override
    public void run() {
        for(int i=0; i<10; i++){
            System.out.println(Thread.currentThread().getName() + "MyTask: " + i);
        }
    }
}