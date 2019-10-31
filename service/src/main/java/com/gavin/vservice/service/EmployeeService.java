package com.gavin.vservice.service;

import com.gavin.vservice.dto.Employee;
import com.gavin.vservice.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
 * @Date : 2019-10-31 14:46
 * @description:
 ************************************************************/
@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * @return com.gavin.vservice.dto.Employee
     * @throws
     * @description 通过id查用户信息
     * @author Gavin
     * @date 2019-10-31 15:04
     * @since
     */
    public Employee selectById(Integer id) {

        return employeeMapper.selectByPrimaryKey(id);

    }


    /**
     * @return
     * @throws
     * @description 增加员工或修改员工信息, 两个操作在一起
     * @author Gavin
     * @date 2019-10-31 15:04
     * @since
     */
    public boolean modifyEmployeeInfo(Employee employee) {

        //  先删除再插值
        employeeMapper.deleteByPrimaryKey(employee.getId());

        int i = employeeMapper.insertSelective(employee);

        return i == 1 ? true : false;

    }

    /**
     * @return
     * @throws
     * @description 批量删除
     * @author Gavin
     * @date 2019-10-31 15:23
     * @since
     */
    public boolean deleteEmployee(Integer... ids) {

        try {
            Arrays.asList(ids).stream().forEach(id -> employeeMapper.deleteByPrimaryKey(id));
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }


    /**
     * @return
     * @throws
     * @description 模糊查询用户信息
     * @author Gavin
     * @date 2019-10-31 15:45
     * @since
     */
    public List<Employee> selectByName(String name) {

        List<Employee> employees = employeeMapper.selectByName(name);

        return employees;

    }

    /**
     * @return
     * @throws
     * @description 模糊查询带分页, 返回总条数跟list
     * @author Gavin
     * @date 2019-10-31 15:54
     * @since
     */
    public HashMap<Integer, List<Employee>> selectByNameWithPage(String name, int start, int offset) {

        HashMap<Integer, List<Employee>> map = new HashMap<>();
        int size = employeeMapper.selectByName(name).size();

        List<Employee> list = employeeMapper.selectByNameWithPage(name, start, offset);

        map.put(size, list);

        return map;
    }

}
