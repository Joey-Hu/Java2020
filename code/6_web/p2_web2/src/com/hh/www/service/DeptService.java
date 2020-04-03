package com.hh.www.service;

import com.hh.www.entity.Dept;

import java.util.List;

/**
 * @author: huhao
 * @time: 2020/4/3 11:20
 * @desc:
 */
public interface DeptService {

    List<Dept> getAllDept();

    int Insert(Dept dept);
}
