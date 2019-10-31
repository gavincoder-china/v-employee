package com.gavin.vcommon.easyExcel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

/**
 * **********************************************************
 *
 * @Project:
 * @Author : Gavincoder
 * @Mail : xunyegege@gmail.com
 * @Github : https://github.com/xunyegege
 * @ver : version 1.0
 * @Date : 2019-10-31 16:24
 * @description:
 ************************************************************/
@Data
public class EmployeeExcel extends BaseRowModel {


    /**
     * 员工编号
     */
    @ExcelProperty(value = {"员工编号"} ,index = 0)
    private Integer id;

    /**
     * 员工姓名
     */
    @ExcelProperty(value = {"员工姓名"} ,index = 1)

    private String name;

    /**
     * 性别
     */
    @ExcelProperty(value = {"性别"} ,index = 2)

    private String gender;

    /**
     * 出生日期
     */
    @ExcelProperty(value = {"出生日期"} ,index = 3)

    private Date birthday;

    /**
     * 身份证号
     */
    @ExcelProperty(value = {"身份证号"} ,index = 4)

    private String idcard;

    /**
     * 婚姻状况
     */
    @ExcelProperty(value = {"婚姻状况"} ,index = 5)

    private Object wedlock;

    /**
     * 民族
     */
    @ExcelProperty(value = {"民族"} ,index = 6)

    private Integer nationid;

    /**
     * 籍贯
     */
    @ExcelProperty(value = {"籍贯"} ,index = 7)

    private String nativeplace;

    /**
     * 政治面貌
     */
    @ExcelProperty(value = {"政治面貌"} ,index = 8)

    private Integer politicid;

    /**
     * 邮箱
     */
    @ExcelProperty(value = {"邮箱"} ,index = 9)

    private String email;

    /**
     * 电话号码
     */
    @ExcelProperty(value = {"电话号码"} ,index = 10)

    private String phone;

    /**
     * 联系地址
     */
    @ExcelProperty(value = {"联系地址"} ,index = 11)

    private String address;

    /**
     * 所属部门
     */
    @ExcelProperty(value = {"所属部门"} ,index = 12)

    private Integer departmentid;

    /**
     * 职称ID
     */
    @ExcelProperty(value = {"职称ID"} ,index = 13)

    private Integer joblevelid;

    /**
     * 职位ID
     */
    @ExcelProperty(value = {"职位ID"} ,index = 14)

    private Integer posid;

    /**
     * 聘用形式
     */
    @ExcelProperty(value = {"聘用形式"} ,index = 15)

    private String engageform;

    /**
     * 最高学历
     */
    @ExcelProperty(value = {"最高学历"} ,index = 16)
    private Object tiptopdegree;

    /**
     * 所属专业
     */
    @ExcelProperty(value = {"所属专业"} ,index = 17)

    private String specialty;

    /**
     * 毕业院校
     */
    @ExcelProperty(value = {"毕业院校"} ,index = 18)

    private String school;

    /**
     * 入职日期
     */
    @ExcelProperty(value = {"入职日期"} ,index = 19)

    private Date begindate;

    /**
     * 在职状态
     */
    @ExcelProperty(value = {"在职状态"} ,index = 20)

    private Object workstate;

    /**
     * 工号
     */
    @ExcelProperty(value = {"工号"} ,index = 21)

    private String workid;

    /**
     * 合同期限
     */
    @ExcelProperty(value = {"合同期限"} ,index = 22)

    private Double contractterm;

    /**
     * 转正日期
     */
    @ExcelProperty(value = {"转正日期"} ,index = 23)

    private Date conversiontime;

    /**
     * 离职日期
     */
    @ExcelProperty(value = {"离职日期"} ,index = 24)

    private Date notworkdate;

    /**
     * 合同起始日期
     */
    @ExcelProperty(value = {"合同起始日期"} ,index = 25)
    private Date begincontract;

    /**
     * 合同终止日期
     */
    @ExcelProperty(value = {"合同终止日期"} ,index = 26)
    private Date endcontract;

    /**
     * 工龄
     */
    @ExcelProperty(value = {"工龄"} ,index = 27)
    private Integer workage;

}
