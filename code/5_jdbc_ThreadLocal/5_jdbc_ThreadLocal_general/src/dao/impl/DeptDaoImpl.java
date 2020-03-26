package dao.impl;

import dao.DeptDao;
import entity.Dept;
import utils.DBUtils;
import utils.DeptRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author: huhao
 * @time: 2020/3/26 16:58
 * @desc:
 */
public class DeptDaoImpl implements DeptDao {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public List<Dept> getAllDept() {

        List<Dept> depts = DBUtils.commonsSelect("Select * from dept;", new DeptRowMapper(), new Object[]{});
        return depts;
    }

    @Override
    public int add(Dept dept) {
        try {
            String sql = "INSERT INTO dept(deptno, dname, loc) VALUES(?, ?, ?);";

            int i = DBUtils.executeUpdate(sql, dept.getDeptno(), dept.getDname(), dept.getLoc());

            return i;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(null, preparedStatement, connection);
        }
        return 0;
    }

    @Override
    public int update(Dept dept) {
        try {
            String sql = "update  dept set dname=?,loc =? where deptno = ?";
            int i = DBUtils.executeUpdate(sql, dept.getDname(), dept.getLoc(), dept.getDeptno());
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(null, preparedStatement, connection);
        }
        return 0;
    }

    @Override
    public int delet(int deptNo) {
        try {
            String sql = "delete from dept where deptno = ?";
            int i = DBUtils.executeUpdate(sql, deptNo);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(null, preparedStatement, connection);
        }
        return 0;
    }
}
