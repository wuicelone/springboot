package com.wubing.demo.entity;

import lombok.Data;

import javax.persistence.*;


/**
 * @author wubing
 * @date 2019/7/9
 * @description 员工数据库实体类
 */
@Entity
@Data
@Table(name = "employee",
        indexes = {
                @Index(name = "index_empName", columnList = "empName", unique = false)
        })
public class EmployeeDO {

    // 自增id
    @Id
    @GeneratedValue
    private Integer id;

    // 员工id
    @Column(nullable = false)
    private Integer empId;

    // 员工名称
    @Column(nullable = false, length = 20)
    private String empName;

    // 部门id
    @Column(nullable = false)
    private Integer deptId;

    // 职位
    @Column(nullable = false)
    private String job;
}
