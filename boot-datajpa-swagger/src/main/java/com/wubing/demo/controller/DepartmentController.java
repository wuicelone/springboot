package com.wubing.demo.controller;

import com.wubing.demo.common.RespModel;
import com.wubing.demo.entity.DepartmentDTO;
import com.wubing.demo.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author wubing
 * @date 2019/7/8
 * @description 部门控制器
 */
@Api(tags = "部门控制器")
@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DepartmentService departmentService;

    /**
     * @param departmentDTO
     * @return java.lang.String
     * @author wubing
     * @date 2019/7/9
     * @description 添加部门
     */
    @ApiOperation(value = "添加部门", notes = "添加部门")
    @PostMapping(value = "/insert")
    public RespModel insertDept(@RequestBody DepartmentDTO departmentDTO) {
        try {
            departmentService.insertDept(departmentDTO);
            return new RespModel(true, "插入部门成功", "");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

        } finally {
            return new RespModel(false, "插入部门失败", "");
        }
    }

    /**
     * @param deptId
     * @return java.lang.String
     * @author wubing
     * @date 2019/7/9
     * @description 删除部门
     */
    @ApiOperation(value = "删除部门", notes = "根据部门id删除部门")
    @ApiImplicitParam(name = "deptId",value = "部门id", required = true)
    @DeleteMapping(value = "/delete")
    public RespModel deleteDept(@RequestParam Integer deptId) {
        if (!"".equals(deptId)) {
            Map<String, List> map = departmentService.listDeptAndEmployee(deptId);
            Boolean empty = map.isEmpty();
            if (empty) {
                try {
                    departmentService.deleteByDeptId(deptId);
                    return new RespModel(true, "删除部门成功", "");
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                } finally {
                    return new RespModel(false, "删除部门失败", "");
                }
            } else {
                return new RespModel(false, "删除部门失败，还有子元素", "");
            }
        } else {
            return new RespModel(false, "参数错误", "");
        }
    }

    /**
     * @param deptId
     * @return java.util.Map<java.lang.String, java.util.List>
     * @author wubing
     * @date 2019/7/9
     * @description 查询下级单位和员工
     */
    @ApiOperation(value = "查询下级单位和员工",notes = "根据部门id查询部门和员工")
    @GetMapping(value = "/listDeptAndEmployee")
    public RespModel listDeptAndEmployee(@RequestParam Integer deptId) {
        Map<String, List> map = departmentService.listDeptAndEmployee(deptId);
        Boolean empty = map.isEmpty();
        if (empty) {
            return new RespModel(false, "没有查询下级单位和员工", "");
        } else {
            return new RespModel(true, "查询下级单位和员工", map);
        }
    }

    /**
     * @param deptName
     * @return com.wubing.demo.entity.DepartmentDTO
     * @author wubing
     * @date 2019/7/9
     * @description
     */
    @ApiOperation(value = "根据部门名称查询部门", notes = "根据部门名称查询部门")
    @ApiImplicitParam(name = "deptName", value = "部门名称", required = true)
    @GetMapping(value = "/getByName")
    public RespModel getOneByName(@RequestParam String deptName) {
        if (deptName != null && !"".equals(deptName)) {
            DepartmentDTO departmentDTO = departmentService.getOneByName(deptName);
            return new RespModel(true, "查询部门成功", departmentDTO);
        } else {
            return new RespModel(false, "查询不到部门", "");
        }

    }

    /**
     * @param departmentDTO
     * @return java.lang.String
     * @author wubing
     * @date 2019/7/9
     * @description 更新部门信息
     */
    @ApiOperation(value = "更新部门信息", notes = "更新部门信息")
    @PutMapping("/update")
    public RespModel updateDept(@RequestBody DepartmentDTO departmentDTO) {
        try {
            departmentService.updateDept(departmentDTO);
            return new RespModel(true, "更新部门成功", "");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            return new RespModel(false, "更新部门失败", "");
        }
    }
}
