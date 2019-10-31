package com.gavin.vservice.controller;

import com.gavin.vservice.dto.Employee;
import com.gavin.vservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * **********************************************************
 *
 * @Project:
 * @Author : Gavincoder
 * @Mail : xunyegege@gmail.com
 * @Github : https://github.com/xunyegege
 * @ver : version 1.0
 * @Date : 2019-10-31 14:48
 * @description:
 ************************************************************/
@RestController
@RequestMapping(value = "/service")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/selectById")
    public Employee selectById(@RequestParam(value = "id") Integer id) {
        return employeeService.selectById(id);
    }

    @PostMapping("/modifyEmployeeInfo")
    public boolean modifyEmployeeInfo(@RequestBody Employee employee) {
        return employeeService.modifyEmployeeInfo(employee);
    }



    /**
     * 但是这样子定义在方便的同时也是要付出一定的代价的，
     * 那就是String...args只能定义在形参的最后一个参数，
     * 而String[] args就没有这样的限制了。
     */
    @PostMapping(value = "/deleteEmployee")
    boolean deleteEmployee(@RequestParam(value = "ids[]") Integer... ids) {
        return employeeService.deleteEmployee(ids);

    }

    @GetMapping(value = "/selectByName")
    public List<Employee> selectByName(@RequestParam(value = "name") String name){
        List<Employee> employees = employeeService.selectByName(name);
        return employees;
    }

    @GetMapping(value = "/selectByNameWithPage")
    public HashMap<Integer, List<Employee>> selectByNameWithPage(@RequestParam(value = "name") String name,
                                                                 @RequestParam(value = "start") int start,
                                                                 @RequestParam(value = "offset") int offset){
        return employeeService.selectByNameWithPage(name, start, offset);
    }

}
