package com.wubing.demo.dao;

import com.wubing.demo.entity.EmployeeDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wubing
 * @date 2019/7/9 10:58
 * @description
 */
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDO, Long> {

    /**
     * 查询一个部门下的所有员工
     *
     * @param deptId
     * @return
     */
    @Query(value = "SELECT * FROM employee WHERE deptId = ?1",nativeQuery = true)
    List<EmployeeDO> listEmployee(Integer deptId);

    /**
     * 根据员工名查询
     *
     * @param name
     * @return
     */
    @Query(value = "SELECT * FROM employee WHERE empName like ?1%", nativeQuery = true)
    EmployeeDO getEmployeeByName(String name);

    /**
     * 删除员工
     * @param empId
     */
    @Modifying
    @Query(value = "DELETE FROM employee WHERE empId = ?1", nativeQuery = true)
    void deleteEmployee(Integer empId);
}
