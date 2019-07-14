package com.wubing.demo.service;

import com.wubing.demo.dao.EmployeeRepository;
import com.wubing.demo.entity.EmployeeDO;
import com.wubing.demo.entity.EmployeeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wubing
 * @date 2019/7/9 11:04
 * @description
 */
@Service
public class EmployeeService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * @param deptId
     * @return java.util.List<com.wubing.demo.entity.EmployeeDO>
     * @author wubing
     * @date 2019/7/9
     * @description 查询员工列表
     */
    public List<EmployeeDO> listEmployee(Integer deptId) {
        List<EmployeeDO> list = employeeRepository.listEmployee(deptId);
        return list;
    }

    /**
     * @param employeeDTO
     * @return void
     * @author wubing
     * @date 2019/7/9
     * @description 添加员工
     */
    @Transactional(rollbackFor = Exception.class)
    public void addEmployee(EmployeeDTO employeeDTO) throws Exception {
        // 数据封装
        EmployeeDO employeeDO = new EmployeeDO();
        employeeDO.setId(employeeDTO.getId());
        employeeDO.setDeptId(employeeDTO.getDeptId());
        employeeDO.setEmpId(employeeDTO.getEmpId());
        employeeDO.setEmpName(employeeDTO.getEmpName());
        employeeDO.setJob(employeeDTO.getJob());

        try {
            // 插入操作
            employeeRepository.save(employeeDO);
        } catch (Exception e) {
            logger.error("添加员工出现异常");
            throw new Exception(e);
        }
    }

    /**
     * @param name
     * @return com.wubing.demo.entity.EmployeeDTO
     * @author wubing
     * @date 2019/7/9
     * @description 根据员工名查询
     */
    public EmployeeDTO getEmployeeByName(String name) {
        EmployeeDO employeeDO = employeeRepository.getEmployeeByName(name);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employeeDO.getId());
        employeeDTO.setDeptId(employeeDO.getDeptId());
        employeeDTO.setEmpId(employeeDO.getEmpId());
        employeeDTO.setEmpName(employeeDO.getEmpName());
        employeeDTO.setJob(employeeDO.getJob());

        return employeeDTO;
    }

    /**
     * @param empId
     * @return void
     * @author wubing
     * @date 2019/7/9
     * @description 删除员工
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteEmployee(Integer empId) throws Exception {
        try {
            employeeRepository.deleteEmployee(empId);
        } catch (Exception e) {
            logger.error("删除员工出现异常");
            throw new Exception(e);
        }

    }

    /**
     * @param employeeDTO
     * @return void
     * @author wubing
     * @date 2019/7/9
     * @description 修改员工
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateEmployee(EmployeeDTO employeeDTO) throws Exception {
        EmployeeDO employeeDO = new EmployeeDO();
        employeeDO.setId(employeeDTO.getId());
        employeeDO.setDeptId(employeeDTO.getDeptId());
        employeeDO.setEmpId(employeeDTO.getEmpId());
        employeeDO.setEmpName(employeeDTO.getEmpName());
        employeeDO.setJob(employeeDTO.getJob());

        try {
            employeeRepository.saveAndFlush(employeeDO);
        } catch (Exception e) {
            logger.error("修改员工出现异常");
            throw new Exception(e);
        }

    }
}
