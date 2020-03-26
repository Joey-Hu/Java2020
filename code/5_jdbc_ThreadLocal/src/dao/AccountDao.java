package dao;

/**
 * @author: huhao
 * @time: 2020/3/26 15:09
 * @desc:
 */
public interface AccountDao {
    /**
     * 取钱
     * @param cardNo
     * @param money
     * @return
     */
    int takeMoney(int cardNo, double money);

    /**
     * 存钱
     * @param carNo
     * @param money
     * @return
     */
    int saveMoney(int carNo, double money);
}
