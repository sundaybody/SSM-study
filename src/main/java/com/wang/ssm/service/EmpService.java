package com.wang.ssm.service;

import com.wang.ssm.dao.EmpMapper;
import com.wang.ssm.pojo.Emp;
import com.wang.ssm.pojo.EmpExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 沐
 * @Description:
 * @create 2022-03-15 14:07
 */
@Service
public class EmpService {

    @Autowired
    EmpMapper empMapper;


    /** 
    *@author 沐
    *@Description:查询所有员工
    *@Date 2022/3/15 14:11
    *@Param 
    *@Return 
    */

    public List<Emp> getAll(){
        return empMapper.selectByExampleWithDept(null);
    }

    /**
     * 添加员工
     * @param emp
     */
    public void saveEmp(Emp emp){
        empMapper.insertSelective(emp);
    }

    /**
     * 检查是否存在empName的员工
     * @param empName
     * @return
     */
    public boolean checkEmp(String empName){
        EmpExample example = new EmpExample();
        example.createCriteria().andEmpNameEqualTo(empName);
        return empMapper.countByExample(example)==0;
    }

    /**
    *@author 沐
    *@Description:按照员工id查询员工
    *@Date 2022/3/17 15:43
    *@Param
    *@Return
    */
    public Emp getEmp(Integer id){
        Emp emp = empMapper.selectByPrimaryKey(id);
        return emp;
    }

    /**
     * 更新员工
     * @param emp
     */
    public void updateEmp(Emp emp){
        empMapper.updateByPrimaryKeySelective(emp);
    }

    /**
    *@author 沐
    *@Description:根据员工id删除员工
    *@Date 2022/3/17 19:01
    *@Param
    *@Return
    */
    public void deleteEmp(Integer id){
        empMapper.deleteByPrimaryKey(id);
    }

    /**
    *@author 沐
    *@Description:批量删除
    *@Date 2022/3/17 21:11
    *@Param
    *@Return
    */
    public void deleteBatch(List<Integer> ids){
        EmpExample example = new EmpExample();
        example.createCriteria().andEmpIdIn(ids);

        empMapper.deleteByExample(example);

    }

}
