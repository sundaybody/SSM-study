package com.wang.ssm.controller;

import com.wang.ssm.pojo.Dept;
import com.wang.ssm.pojo.Msg;
import com.wang.ssm.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 沐
 * @Description:处理和部门有关的请求
 * @create 2022-03-16 17:13
 */
@Controller
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 返回所有部门的信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/depts")
    public Msg getDepts(){
//        查出所有部门信息
        List<Dept> list = deptService.getDepts();
        return Msg.success().add("depts",list);
    }

}
