package hello;

/**
 * @author: huhao
 * @time: 2020/4/26 10:20
 * @desc:
 */
public class Application {

    public static void main(String[] args) {

        System.out.println("application");
        // 创建打印机对象
        MessagePrinter messagePrinter = new MessagePrinter();
        // 创建消息服务对象
        MessageService messageService = new MessageService();
        // 设置打印机对象的service属性
        messagePrinter.setMessageService(messageService);
        // 打印消息
        messagePrinter.printMessage();
    }
}
