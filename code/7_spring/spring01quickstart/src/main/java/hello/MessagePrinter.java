package hello;

/**
 * @author: huhao
 * @time: 2020/4/26 10:19
 * @desc:
 */
public class MessagePrinter {

    private MessageService messageService;


    public void printMessage(){
        System.out.println(this.messageService.getMessage());
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
}
