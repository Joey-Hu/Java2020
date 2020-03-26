package utils;

import java.sql.ResultSet;

/**
 * @author: huhao
 * @time: 2020/3/26 17:17
 * @desc:
 */
public interface RowMapper<T> {

    T getRow(ResultSet resultSet);
}
