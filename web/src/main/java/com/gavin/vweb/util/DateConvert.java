package com.gavin.vweb.util;

import com.gavin.vweb.dto.Employee;
import com.gavin.vweb.vo.EmployeeVO;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * **********************************************************
 *
 * @Project: 时间转换
 * @Author : Gavincoder
 * @Mail : xunyegege@gmail.com
 * @Github : https://github.com/xunyegege
 * @ver : version 1.0
 * @Date : 2019-10-31 17:20
 * @description:
 ************************************************************/
@Component
public class DateConvert {

    //给vo
    public EmployeeVO dateToString(Employee employee, EmployeeVO employeeVO) {
        if (null != employee.getBegindate()) {
            employeeVO.setBegindate(new SimpleDateFormat("yyyy-MM-dd").format(employee.getBegindate()));
        }

        if (null != employee.getConversiontime()) {
            employeeVO.setConversiontime(new SimpleDateFormat("yyyy-MM-dd").format(employee.getConversiontime()));
        }
        if (null != employee.getNotworkdate()) {
            employeeVO.setNotworkdate(new SimpleDateFormat("yyyy-MM-dd").format(employee.getNotworkdate()));
        }
        if (null != employee.getBegincontract()) {
            employeeVO.setBegincontract(new SimpleDateFormat("yyyy-MM-dd").format(employee.getBegincontract()));
        }
        if (null != employee.getEndcontract()) {
            employeeVO.setEndcontract(new SimpleDateFormat("yyyy-MM-dd").format(employee.getEndcontract()));
        }

        return employeeVO;
    }



    //给dto
    public Employee stringToDate(Employee employee, EmployeeVO employeeVO) throws Exception {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (null != employeeVO.getBegindate()) {
            employee.setBegindate(simpleDateFormat.parse(employeeVO.getBegindate()));
        }

        if (null != employeeVO.getConversiontime()) {
            employee.setConversiontime(simpleDateFormat.parse(employeeVO.getConversiontime()));
        }
        if (null != employeeVO.getNotworkdate()) {
            employee.setNotworkdate(simpleDateFormat.parse(employeeVO.getNotworkdate()));
        }
        if (null != employeeVO.getBegincontract()) {
            employee.setBegincontract(simpleDateFormat.parse(employeeVO.getBegincontract()));
        }
        if (null != employeeVO.getEndcontract()) {
            employee.setEndcontract(simpleDateFormat.parse(employeeVO.getEndcontract()));
        }

        return employee;
    }

}
