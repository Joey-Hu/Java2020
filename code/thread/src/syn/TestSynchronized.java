package syn;

/**
 * @author: huhao
 * @time: 2020/3/9 16:27
 * @desc:
 */
public class TestSynchronized {
    public static void main(String[] args) {
        Account acc = new Account("123", "456", 1000);

        Husband h = new Husband(acc);
        Wife w = new Wife(acc);

        Thread t1 = new Thread(h);
        Thread t2 = new Thread(w);

        t1.start();
        t2.start();

    }
}

class Husband implements Runnable{
    private Account acc;

    public Husband(Account acc) {
        this.acc = acc;
    }

    @Override
    public void run() {
        acc.withdrawal("123", "456", 800);
    }
}

class Wife implements Runnable{
    private Account acc;

    public Wife(Account acc) {
        this.acc = acc;
    }

    @Override
    public void run() {
            acc.withdrawal("123", "456", 800);
    }
}

class Account{

    private String cardNo;
    private String password;
    private int balance;

    public Account(String cardNo, String password, int balance) {

        this.cardNo = cardNo;
        this.password = password;
        this.balance = balance;
    }

    public void withdrawal(String num, String pwd, int money){

        synchronized (this){
            System.out.println("请稍后...");

            if(cardNo.equals(num) && password.equals(pwd)){
                if(money < balance){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    balance -= money;
                    System.out.println("取款成功, 当前余额为：" + balance);
                }else{
                    System.out.println("余额不足。");
                }
            }else{
                System.out.println("账户密码错误。");
            }
        }
    }
}
