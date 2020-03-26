package service.impl;

import dao.AccountDao;
import dao.impl.AccountDaoImpl;
import service.AccountService;
import util.DruidUtil;

/**
 * @author: huhao
 * @time: 2020/3/26 15:24
 * @desc:
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao = new AccountDaoImpl();

    //在一个业务里！调用了两次DAO   执行了两个SQL语句！
    @Override
    public void transfer(int fromCard, int toCard, double money) {

        try {
            // 事务操作在 工具类中实现
            // 开启事务
            System.out.println("开启事务提交！");
            DruidUtil.start();
            // sql执行
            int takeMoney = accountDao.takeMoney(fromCard, money);
//            int i = 10/0;
            int saveMoney = accountDao.saveMoney(toCard, money);

            // commit
            System.out.println("事务提交成功。");
            DruidUtil.commit();

            // 打印结果
            if(takeMoney == 1 && saveMoney == 1){
                System.out.println("交易成功！");
            }else{
                System.out.println("交易失败！");
            }
        } catch (Exception e) {
            // 失败回滚 catch
            System.out.println("出现了异常！事务回滚！");
            DruidUtil.rollBack();
            e.printStackTrace();
        } finally {
            // 释放资源
            DruidUtil.close();
        }
    }



}
