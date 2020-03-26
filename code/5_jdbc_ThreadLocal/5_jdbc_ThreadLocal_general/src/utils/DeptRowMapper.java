package utils;

import entity.Dept;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: huhao
 * @time: 2020/3/26 17:17
 * @desc:
 */
public class DeptRowMapper implements RowMapper<Dept> {
    @Override
    public Dept getRow(ResultSet resultSet) {
        Dept dept = new Dept();

        try {
            dept.setDeptno(resultSet.getInt("deptno"));
            dept.setDname(resultSet.getString("dname"));
            dept.setLoc(resultSet.getString("loc"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dept;
    }
}
