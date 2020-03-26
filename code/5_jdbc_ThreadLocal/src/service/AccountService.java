package service;

/**
 * @author: huhao
 * @time: 2020/3/26 15:23
 * @desc:
 */
public interface AccountService {

    /**
     * 转账界面
     * @param fromCard
     * @param toCard
     * @param money
     */
    void transfer(int fromCard, int toCard, double money);
}
