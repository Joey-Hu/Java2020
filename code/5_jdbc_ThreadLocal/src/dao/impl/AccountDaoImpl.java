package dao.impl;

import com.alibaba.druid.pool.DruidDataSource;
import dao.AccountDao;
import util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author: huhao
 * @time: 2020/3/26 15:11
 * @desc: 只负责编译sql语句、执行sql语句！不关心结果！
 */
public class AccountDaoImpl implements AccountDao {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    @Override
    public int takeMoney(int cardNo, double money) {
        connection = DruidUtil.getConnection();
        String sql = "UPDATE account SET money = money - ? WHERE id = ?;";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, money);
            preparedStatement.setInt(2, cardNo);
            int i = preparedStatement.executeUpdate();
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DruidUtil.closeAll(null, preparedStatement, null);
        }
        return 0;
    }

    @Override
    public int saveMoney(int cardNo, double money) {
        connection = DruidUtil.getConnection();
        String sql = "UPDATE account SET money = money + ? WHERE id = ?;";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, money);
            preparedStatement.setInt(2, cardNo);
            int i = preparedStatement.executeUpdate();
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DruidUtil.closeAll(null, preparedStatement, null);
        }
        return 0;
    }
}
