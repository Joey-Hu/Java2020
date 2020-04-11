package com.hh.www.service.impl;

import com.hh.www.beans.Dept;
import com.hh.www.dao.DeptDao;
import com.hh.www.dao.impl.DeptDaoImpl;
import com.hh.www.service.DeptService;

import java.util.List;

/**
 * @author: huhao
 * @time: 2020/4/11 18:33
 * @desc:
 */
public class DeptServiceImpl implements DeptService {

    private DeptDao deptDao = new DeptDaoImpl();

    @Override
    public List<Dept> getAllDept() {
        return deptDao.getAllDept();
    }
}
