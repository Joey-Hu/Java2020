package lock;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: huhao
 * @time: 2020/3/11 14:55
 * @desc:
 */
public class TestReentrantReadWriteLock {

    public static void main(String[] args) {
        Student s = new Student();

        Callable<Object> writeTask = new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                s.setValue(111);
                return null;
            }
        };

        Callable<Object> readTask = new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                s.getValue();
                return null;
            }
        };

        ExecutorService es = Executors.newFixedThreadPool(20);

        long start = System.currentTimeMillis();

        for(int i=0; i<2; i++){
            es.submit(writeTask);
        }

        for(int i=0; i<18; i++){
            // 这18次读操作在重入锁下不可以并发执行 21030
            // 这18次读操作在读写锁下可以并发执行 4013
            es.submit(readTask);
        }

        // 完成当前已经接受的任务并不再接受新任务，关闭线程池
        es.shutdown();

        while(true){
            System.out.println("is end?");
            // isTerminated() 判断线程池是否终止
            if(es.isTerminated()){
                break;
            }
        }

        System.out.println(System.currentTimeMillis() - start);
    }
}


class Student{

//    /**
//     * 重入锁
//     */
//    ReentrantLock locker = new ReentrantLock();

    /**
     * 读写锁
     */
    ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock rl = rwl.readLock();
    ReentrantReadWriteLock.WriteLock wl = rwl.writeLock();


    private int value;

    /**
     * 读操作
     * @return
     */
    public int getValue(){
//        locker.lock();
        rl.lock();
        try {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return this.value;

        }finally {
//            locker.unlock();
            rl.unlock();
        }
    }

    /**
     * 写操作
     */
    public void setValue(int value){
//        locker.lock();
        wl.lock();
        try {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.value = value;
        }finally {
//            locker.unlock();
            wl.unlock();
        }
    }
}

