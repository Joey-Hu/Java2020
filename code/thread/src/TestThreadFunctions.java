/**
 * @author: huhao
 * @time: 2020/3/9 15:38
 * @desc:
 */
public class TestThreadFunctions {
    public static void main(String[] args) throws InterruptedException {
        MyRunnable task = new MyRunnable();
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        // 1. 线程休眠
        Thread.sleep(1500);
    }
}

