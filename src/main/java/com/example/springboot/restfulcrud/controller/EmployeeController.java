package com.example.springboot.restfulcrud.controller;

import com.example.springboot.restfulcrud.dao.DepartmentDao;
import com.example.springboot.restfulcrud.dao.EmployeeDao;
import com.example.springboot.restfulcrud.entities.Department;
import com.example.springboot.restfulcrud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @GetMapping("/emps")
    public String getEmps(ModelMap modelMap){
        Collection<Employee> emps = employeeDao.getAll();
        modelMap.addAttribute("emps",emps);
        return "emp/list";
    }


    //来到添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //查询出所以的部门
        Collection<Department>  depts= departmentDao.getDepartments();
        model.addAttribute("depts",depts);
        return "emp/add";
    }

    //添加员工
    //spring mvc自动将请求参数和入参对象的属性一一绑定，前提：请求参数的名字与javabean对象的属性名一样
    @PostMapping("/emp")
    public String addEmp(@Valid Employee employee, BindingResult bindingResult){
        //spring mvc默认的日期格式是2017/10/11的如果表单提交过来的日期格式不是按照spring mvc的默认格式会出现一个异常
        //在主配置文件中可以指定日期格式
        if(bindingResult.hasErrors()){
            System.out.println("bingingResult异常： "+bindingResult);
            bindingResult.getModel();
        }
        System.out.println(employee);
        employeeDao.save(employee);
        //ThymeleafViewResolver  createView(String viewName, Locale locale)
        // 视图解析器在解析指定的视图的时候如果视图名以redirect:开头
        //表示重定向,如果以forward:开头表示转发
        // /代表当前项目的根路径
        return "redirect:/emps";
    }

    //去编辑页面,编辑页面和添加页面用同一个
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        Collection<Department> departments = departmentDao.getDepartments();

        model.addAttribute("emp",employee);
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    //员工修改
    @PutMapping("/emp")
    public String eidtEmp(Employee employee){
        System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //删除员工
    @DeleteMapping("/emp/{id}")
    public String delEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
