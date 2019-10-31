package com.gavin.vweb.controller;

import com.gavin.vcommon.easyExcel.model.EmployeeExcel;
import com.gavin.vcommon.easyExcel.util.ExcelUtil;
import com.gavin.vcommon.randomNumber.RandomNumberGenerator;
import com.gavin.vweb.dto.Employee;
import com.gavin.vweb.feign.EmployeeFeign;
import com.gavin.vweb.result.ReturnResult;
import com.gavin.vweb.result.ReturnResultContants;
import com.gavin.vweb.result.ReturnResultUtils;
import com.gavin.vweb.util.DateConvert;
import com.gavin.vweb.vo.EmployeeListVO;
import com.gavin.vweb.vo.EmployeeVO;
import com.gavin.vweb.vo.PageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
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
 * @Date : 2019-10-31 14:38
 * @description:
 ************************************************************/
@Api(tags = "人员信息管理")
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {


    @Autowired
    private EmployeeFeign employeeFeign;

    @Autowired
    private DateConvert dateConvert;


    @ApiOperation(value = "获取新员工的id")
    @PostMapping(value = "/getNewEmployeeId")
    public ReturnResult<Integer> getNewEmployeeId() {
        return ReturnResultUtils.returnSuccess(RandomNumberGenerator.generateNumber());
    }

    @ApiOperation(value = "获取单个用户的信息")
    @GetMapping(value = "/selectById")
    public ReturnResult<EmployeeVO> selectById(@ApiParam(value = "用户id")
                                               @RequestParam(value = "id") Integer id) {

        Employee employee = employeeFeign.selectById(id);
        EmployeeVO employeeVO = new EmployeeVO();
        BeanUtils.copyProperties(employee, employeeVO);

        EmployeeVO employeeVOFinal = dateConvert.dateToString(employee, employeeVO);
        return ReturnResultUtils.returnSuccess(employeeVOFinal);
    }

    @ApiOperation(value = "增加员工或修改员工信息, 两个操作在一起")
    @PostMapping("/modifyEmployeeInfo")
    public ReturnResult modifyEmployeeInfo(@Valid EmployeeVO employeeVO) throws Exception {
        //判断是否传来了id
        if (null == employeeVO.getId()) {
            return ReturnResultUtils.returnFail(ReturnResultContants.CODE_MODIFY_FAIL,
                                                ReturnResultContants.MSG_MODIFY_FAIL);
        }


        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeVO, employee);

        Employee employeFinal = dateConvert.stringToDate(employee, employeeVO);
        boolean result = employeeFeign.modifyEmployeeInfo(employeFinal);
        return result ? ReturnResultUtils.returnSuccess() :
               ReturnResultUtils.returnFail(ReturnResultContants.CODE_MODIFY_FAIL,
                                            ReturnResultContants.MSG_MODIFY_FAIL);
    }


    @ApiOperation(value = "删除员工")
    @PostMapping(value = "/deleteEmployee")
    public ReturnResult deleteEmployee(@ApiParam(value = "员工id,可传数组") @RequestParam(value = "ids[]") Integer... ids) {

        boolean result = employeeFeign.deleteEmployee(ids);
        return result ? ReturnResultUtils.returnSuccess() :
               ReturnResultUtils.returnFail(ReturnResultContants.CODE_DEL_EMPLOYEE_FAIL,
                                            ReturnResultContants.MSG_DEL_EMPLOYEE_FAIL);
    }


    @ApiOperation(value = "通过姓名模糊查询")
    @GetMapping(value = "/selectByName")
    public ReturnResult<EmployeeListVO> selectByName(@ApiParam(value = "姓名", required = false)
                                                     @RequestParam(value = "name") String name,
                                                     @Valid PageVo pageVo,
                                                     @ApiParam(value = "是否下载1下载,0不下载")
                                                     @RequestParam(value = "down") int down,
                                                     HttpServletResponse response) {
        if (null == name) {
            name = null;
        }

       if (1 == down) {


            List<Employee> empls = employeeFeign.selectByName(name);

            List<EmployeeExcel> list = new ArrayList<>();
            //转成empl的model

            empls.stream().forEach(em -> {
                EmployeeExcel excel = new EmployeeExcel();
                BeanUtils.copyProperties(em, excel);
                list.add(excel);
            });


            String fileName = "员工信息";
            String sheetName = "信息";

            ExcelUtil.writeExcel(response, list, fileName, sheetName, new EmployeeExcel());

        }

        HashMap<Integer, List<Employee>> map = employeeFeign.selectByNameWithPage(name, pageVo.getStart(), pageVo.getPageSize());

        EmployeeListVO employeeListVO = new EmployeeListVO();
        map.forEach((k, v) -> {
            employeeListVO.setTotalSize(k);
            List<EmployeeVO> list = new ArrayList<>();

            v.stream().forEach(em -> {
                EmployeeVO employeeVO = new EmployeeVO();
                BeanUtils.copyProperties(em, employeeVO);

                EmployeeVO vo = dateConvert.dateToString(em, employeeVO);

                list.add(vo);
            });

            employeeListVO.setEmpls(list);

        });

        employeeListVO.setStartPage(pageVo.getStartPage());
        employeeListVO.setPageSize(pageVo.getPageSize());

        return ReturnResultUtils.returnSuccess(employeeListVO);

    }

    @ApiOperation(value = "模糊查询员工信息,并生成excel表格供下载")  //,produces = "application/octet-stream" 显示下载按钮
    @GetMapping(value = "/writeExcel")
    public void writeExcel(@ApiParam(value = "姓名", required = false)
                           @RequestParam(value = "name", required = false) String name,
                           HttpServletResponse response) throws IOException {


        if (null == name) {
            name = null;
        }
        List<Employee> empls = employeeFeign.selectByName(name);

        List<EmployeeExcel> list = new ArrayList<>();
        //转成empl的model

        empls.stream().forEach(em -> {
            EmployeeExcel excel = new EmployeeExcel();
            BeanUtils.copyProperties(em, excel);
            list.add(excel);
        });




        String fileName = "员工信息";
        String sheetName = "信息";

       // ExcelUtil.writeExcelWithPath("gavin",list,fileName,sheetName,new EmployeeExcel());

       ExcelUtil.writeExcel(response, list, fileName, sheetName, new EmployeeExcel());

    }





}
