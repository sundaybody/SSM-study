package com.wang.ssm.service;

import com.wang.ssm.dao.DeptMapper;
import com.wang.ssm.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 沐
 * @Description:
 * @create 2022-03-16 17:15
 */
@Service
public class DeptService {

    @Autowired
    private DeptMapper deptMapper;

    public List<Dept> getDepts(){
        return deptMapper.selectByExample(null);
    }
}
