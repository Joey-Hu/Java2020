package communication;

/**
 * @author: huhao
 * @time: 2020/3/10 8:51
 * @desc:
 */
public class ProducerAndConsummer {
    public static void main(String[] args) {
        MyStack ms = new MyStack();

        Producer tp = new Producer(ms);
        Thread p = new Thread(tp);

        Consummer cp = new Consummer(ms);
        Thread c = new Thread(cp);

        p.start();
        c.start();
    }
}

class Producer implements Runnable{

    private MyStack ms;

    public Producer(MyStack ms) {
        this.ms = ms;
    }

    @Override
    public void run(){
        for(char ch = 'A'; ch<='Z'; ch++){
            ms.add(ch + " ");
        }
    }
}

class Consummer implements Runnable{

    private MyStack ms;

    public Consummer(MyStack ms) {
        this.ms = ms;
    }

    @Override
    public void run(){
        for(int i=0; i<26; i++){
            ms.remove();
        }
    }
}

/**
 * 容器
 */
class MyStack{
    private String[] values = new String[]{"", "", "", "", ""};
    private int size = 0;

    public synchronized void add(String str){

        // 唤醒对方线程
        this.notifyAll();

        if(values.length == size){
            System.out.println("满了");
            try {
                // 线程挂起
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(str + "入栈");

        values[size] = str;
        size ++;
    }

    public synchronized void remove(){

        // 唤醒对方线程
        this.notifyAll();

        if(size == 0){
            System.out.println("空了");
            try {
                // 线程挂起
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(values[size-1] + "出栈");

        values[size-1] = "";
        size --;
    }
}
