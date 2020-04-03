package com.hh.www.dao;

import com.hh.www.entity.Dept;

import java.util.List;

/**
 * @author: huhao
 * @time: 2020/4/3 11:06
 * @desc:
 */
public interface DeptDao {

    /**
     * 获取所有部门信息
     * @return
     */
    List<Dept> getAll();

    /**
     * 插入部门信息
     * @param dept
     * @return
     */
    int insert(Dept dept);
}
