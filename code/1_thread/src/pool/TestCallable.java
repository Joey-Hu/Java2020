package pool;

import java.util.concurrent.*;

/**
 * @author: huhao
 * @time: 2020/3/10 14:55
 * @desc:
 */
public class TestCallable {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(3);

        Callable<Integer> task1 = new MyTask1();
        Callable<Integer> task2 = new MyTask2();

        Future<Integer> ret1 = es.submit(task1);

        Future<Integer> ret2 = es.submit(task2);

        try {
            System.out.println("The result is: " + (ret1.get() + ret2.get()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}

class MyTask1 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {

        Integer sum = 0;

        for(int i=1; i<=50; i++){
            sum += i;
        }

        return sum;
    }
}

class MyTask2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {

        Integer sum = 0;

        for(int i=51; i<=100; i++){
            sum += i;
        }

        return sum;
    }
}

