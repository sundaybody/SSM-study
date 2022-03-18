package com.wang.ssm.dao;

import com.wang.ssm.pojo.Emp;
import com.wang.ssm.pojo.EmpExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmpMapper {
    long countByExample(EmpExample example);

    int deleteByExample(EmpExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert(Emp record);

    int insertSelective(Emp record);

    List<Emp> selectByExample(EmpExample example);


    Emp selectByPrimaryKey(Integer empId);
//  根据条件查询带部门信息的员工表
    List<Emp> selectByExampleWithDept(EmpExample example);
//  根据主键查询带部门信息的员工表
    Emp selectByPrimaryKeyWithDept(Integer empId);

    int updateByExampleSelective(@Param("record") Emp record, @Param("example") EmpExample example);

    int updateByExample(@Param("record") Emp record, @Param("example") EmpExample example);

    int updateByPrimaryKeySelective(Emp record);

    int updateByPrimaryKey(Emp record);
}