package com.wubing.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wubing
 * @date 2019/7/9 16:30
 * @description 员工业务实体类
 */
@ApiModel(description = "员工接口实体类")
@Data
public class EmployeeDTO implements Serializable {

    // 自增id
    @ApiModelProperty("自增id")
    private Integer id;

    // 员工id
    @ApiModelProperty("员工id")
    private Integer empId;

    // 员工姓名
    @ApiModelProperty("员工姓名")
    private String empName;

    // 部门id
    @ApiModelProperty("部门id")
    private Integer deptId;

    // 职位
    @ApiModelProperty("职位")
    private String job;
}
