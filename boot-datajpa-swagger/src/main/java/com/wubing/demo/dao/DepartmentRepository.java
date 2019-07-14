package com.wubing.demo.dao;

import com.wubing.demo.entity.DepartmentDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wubing
 * @date 2019/7/8
 * @description 部门dao层
 */
@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentDO, Long> {

    /**
     * 根据部门id删除部门
     *
     * @param id
     */
    @Modifying
    @Query(value = "DELETE from department WHERE deptId = ?1",nativeQuery = true)
    void deleteByDeptId(Integer id);

    /**
     * 查询一个单位下的子单位和员工
     *
     * @param deptId
     * @return
     */
    @Query(value = "SELECT * FROM department WHERE superDeptId = ?1",nativeQuery = true)
    List<DepartmentDO> listDept(Integer deptId);

    /**
     * 根据部门名称查询部门
     * @param deptName
     * @return
     */
    @Query(value = "SELECT * FROM department WHERE deptName like ?1%", nativeQuery = true)
    DepartmentDO getOneByName(String deptName);
}
