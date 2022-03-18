package com.wang.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.ssm.pojo.Emp;
import com.wang.ssm.pojo.Msg;
import com.wang.ssm.service.EmpService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 沐
 * @Description:处理员工CRUD请求 后端需要进行校验JSR303
 * @create 2022-03-15 14:02
 */
@Controller
public class EmpController {

    @Autowired
    EmpService empService;


    /**
     * 导入jackson包
     *
     * @param pn
     * @return
     */
    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpWithJson(
            @RequestParam(value = "pn", defaultValue = "1") Integer pn
    ) {
        //      引入PageHelper分页插件
//       在查询之前只需要调用,传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
//      startPage后面紧跟的这个查询就是一个分页查询
        List<Emp> emps = empService.getAll();
//      使用pageInfo包装查询后的结果，只需要将pageinfo交给页面就可以了
//      pageInfo封装了详细的信息，包括有我们查询出来的数据 navigatePages：导航分页的页码数
        PageInfo pageInfo = new PageInfo(emps, 5);

        return Msg.success().add("pageInfo", pageInfo);
    }



    /**
     * 检查用用户名是否可用
     *
     * @param empName
     * @return true:代表当前姓名可用 false: 代表代表当前姓名不可用
     */
    @RequestMapping("/checkemp")
    @ResponseBody
    public Msg checkEmp(@RequestParam("empName") String empName) {
//     1.1 先判断用户名是否是合法的表达式
        String regx = "(^[a-zA_Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
//  判断此字符串是否与给定的正则表达式匹配。以str .matches( regex )形式调用此方法会产生与表达式完全相同的结果
        if (!empName.matches(regx)) {
            return Msg.fail().add("va_msg", "用户名必须是2-5位中文或6-16英文和数字的组合");
        }
//    1.2    数据库用户名校验
        boolean checkEmp = empService.checkEmp(empName);
        if (checkEmp) {
            return Msg.success();
        }
        return Msg.fail().add("va_msg", "用户名已存在");
    }

    /**
     * 添加并保存员工请求
     * 1.支持JSR303校验
     * 2.
     *
     * @return
     */
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Emp emp, BindingResult result) { //@Valid 标识封装的数据进行正则校验
        if (result.hasErrors()) {
//            校验失败，在模态框中显示校验失败的错误信息
            Map<String,Object> map=new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                System.out.println("错误的字段名为："+fieldError.getField());
                System.out.println("错误信息："+fieldError.getDefaultMessage());
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields",map);

        }
        empService.saveEmp(emp);
        return Msg.success();
    }

    /**
    *@author 沐
    *@Description:根据id查询员工
    *@Date 2022/3/17 17:14
    *@Param
    *@Return
    */
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id") Integer id){
        Emp emp = empService.getEmp(id);
        return Msg.success().add("emp",emp);
    }

    /**
    *@author 沐
    *@Description:更新并保存员工数据
    *@Date 2022/3/17 17:14
    *@Param
    *@Return
    * 如果直接发送ajax=put形式的请求存在问题
    *
     * 请求体中有数据；但是employee对象封装不上
     * 我们要能支持发送PUT之类的请求还要封装请求体中的数据
     * 配置上FormContentFilter
     * 作用：能够处理PUT和DELETE请求 将请求体中的数据包装成一个map
     */
    @ResponseBody
    @RequestMapping(value = "/emp/{empId}",method = RequestMethod.PUT)
    public Msg saveEmp(Emp emp){
        empService.updateEmp(emp);
        return Msg.success();
    }

    /**
     * 单个批量二合一
     * 批量：1-2-3
     * 单个：1
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/emp/{ids}",method = RequestMethod.DELETE)
    public Msg deleteEmpById(@PathVariable("ids")String ids){
        if (ids.contains("-")){
            List<Integer> del_ids=new ArrayList<>();
            String[] str_ids = ids.split("-");
//          组装id的数组集合
            for(String str :str_ids){
                del_ids.add(Integer.parseInt(str));
            }
            empService.deleteBatch(del_ids);
        }else {
            empService.deleteEmp(Integer.parseInt(ids));
        }
        return Msg.success();
    }


}
