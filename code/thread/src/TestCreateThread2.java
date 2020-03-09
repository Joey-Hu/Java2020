/**
 * @author: huhao
 * @time: 2020/3/9 15:16
 * @desc: 创建线程的第二种方法
 *
 */
public class TestCreateThread2 {
    public static void main(String[] args) {
        MyRunnable mr = new MyRunnable();
        // Runnable 就是一个任务，它的实现需要交给线程来实现
        Thread t1 = new Thread(mr);
        Thread t2 = new Thread(mr);

        t1.start();
        t2.start();
    }
}

class MyRunnable implements Runnable{

    @Override
    public void run() {
        for(int i=0; i<20; i++){
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}
