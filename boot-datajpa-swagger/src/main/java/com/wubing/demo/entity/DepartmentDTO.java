package com.wubing.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wubing
 * @date 2019/7/9 16:30
 * @description 部门业务实体类
 */
@ApiModel(description = "部门接口实体类")
@Data
public class DepartmentDTO implements Serializable {

    /**
     * 自增id
     */
    @ApiModelProperty(value = "自增id")
    private Integer id;

    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id")
    private Integer deptId;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String deptName;

    /**
     * 上级部门id
     */
    @ApiModelProperty(value = "上级部门")
    private Integer superDeptId;
}
