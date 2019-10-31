package com.gavin.vweb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * **********************************************************
 *
 * @Project:
 * @Author : Gavincoder
 * @Mail : xunyegege@gmail.com
 * @Github : https://github.com/xunyegege
 * @ver : version 1.0
 * @Date : 2019-10-31 16:06
 * @description:
 ************************************************************/
@Data
@ApiModel
public class EmployeeListVO implements Serializable {
    private static final long serialVersionUID = 1067434326536145086L;

    @ApiModelProperty(value = "起始页")
    private int startPage;

    @ApiModelProperty(value = "每页条数")
    private int pageSize;

    @ApiModelProperty(value = "总条数")
    private int totalSize;

    @ApiModelProperty(value = "用户信息列表")
    private List<EmployeeVO> empls;
}
