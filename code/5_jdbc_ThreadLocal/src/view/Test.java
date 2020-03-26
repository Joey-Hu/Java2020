package view;

import service.AccountService;
import service.impl.AccountServiceImpl;

import java.util.Scanner;

/**
 * @author: huhao
 * @time: 2020/3/26 15:51
 * @desc:
 */
public class Test {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("欢迎登陆转账系统：");
        System.out.println("请输入卡号：");
        int fromCard = sc.nextInt();
        System.out.println("请输入对方卡号：");
        int toCard = sc.nextInt();

        System.out.println("请输入转账金额：");
        double money = sc.nextDouble();

        AccountService accountService = new AccountServiceImpl();
        accountService.transfer(fromCard, toCard, money);

    }
}
