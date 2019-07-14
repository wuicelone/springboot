package com.wubing.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author wubing
 * @date 2019/7/9 16:30
 * @description 部门数据库实体类
 */
@Entity
@Data
@Table(name = "department",
        indexes = {
                @Index(name = "index_deptName", columnList = "deptName", unique = true)
        })
public class DepartmentDO {

    // 自增id
    @Id
    @GeneratedValue
    private Integer id;

    // 部门id
    @Column(nullable = false)
    private Integer deptId;

    // 部门名称
    @Column(nullable = false)
    private String deptName;

    // 上级id
    @Column(nullable = false)
    private Integer superDeptId;
}
