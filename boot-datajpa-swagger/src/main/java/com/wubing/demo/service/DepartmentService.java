package com.wubing.demo.service;

import com.wubing.demo.dao.DepartmentRepository;
import com.wubing.demo.dao.EmployeeRepository;
import com.wubing.demo.entity.DepartmentDO;
import com.wubing.demo.entity.DepartmentDTO;
import com.wubing.demo.entity.EmployeeDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wubing
 * @date 2019/7/8
 * @description 部门service层
 */
@Service
public class DepartmentService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    /**
     * @param departmentDTO
     * @return void
     * @author wubing
     * @date 2019/7/9
     * @description 添加部门
     */
    @Transactional(rollbackFor = Exception.class)
    public void insertDept(DepartmentDTO departmentDTO) throws Exception {
        DepartmentDO departmentDO = new DepartmentDO();
        departmentDO.setId(departmentDTO.getId());
        departmentDO.setDeptId(departmentDTO.getDeptId());
        departmentDO.setDeptName(departmentDTO.getDeptName());
        departmentDO.setSuperDeptId(departmentDTO.getSuperDeptId());

        try {
            departmentRepository.save(departmentDO);
        } catch (Exception e) {
            logger.error("添加部门出现异常");
            throw new Exception(e);
        }
    }


    /**
     * @param deptId
     * @return void
     * @author wubing
     * @date 2019/7/9
     * @description 删除部门
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteByDeptId(Integer deptId) throws Exception {
        try {
            departmentRepository.deleteByDeptId(deptId);
        } catch (Exception e) {
            logger.error("删除部门出现异常");
            throw new Exception(e);
        }
    }


    /**
     * @param deptId
     * @return java.util.Map<java.lang.String, java.util.List>
     * @author wubing
     * @date 2019/7/9
     * @description 查询一个单位下的子元素
     */
    public Map<String, List> listDeptAndEmployee(Integer deptId) {
        List<DepartmentDO> deptList = departmentRepository.listDept(deptId);
        List<EmployeeDO> empList = employeeRepository.listEmployee(deptId);
        if (deptList.size() > 0 || empList.size() > 0) {
            Map<String, List> map = new HashMap<>(16);
            map.put("deptList", deptList);
            map.put("empList", empList);
            return map;
        } else {
            return new HashMap<>(16);
        }
    }

    /**
     * @param departmentDTO
     * @return void
     * @author wubing
     * @date 2019/7/9
     * @description 更新部门信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateDept(DepartmentDTO departmentDTO) throws Exception {
        DepartmentDO departmentDO = new DepartmentDO();
        departmentDO.setId(departmentDTO.getId());
        departmentDO.setDeptId(departmentDTO.getDeptId());
        departmentDO.setDeptName(departmentDTO.getDeptName());
        departmentDO.setSuperDeptId(departmentDTO.getSuperDeptId());

        try {
            departmentRepository.saveAndFlush(departmentDO);
        } catch (Exception e) {
            logger.error("更新部门出现异常");
            throw new Exception(e);
        }

    }

    /**
     * @param deptName
     * @return com.wubing.demo.entity.DepartmentDTO
     * @author wubing
     * @date 2019/7/9
     * @description 根据部门名称查询部门
     */
    public DepartmentDTO getOneByName(String deptName) {
        DepartmentDO departmentDO = departmentRepository.getOneByName(deptName);

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(departmentDO.getId());
        departmentDTO.setDeptId(departmentDO.getDeptId());
        departmentDTO.setDeptName(departmentDO.getDeptName());
        departmentDTO.setSuperDeptId(departmentDO.getSuperDeptId());

        return departmentDTO;
    }
}
