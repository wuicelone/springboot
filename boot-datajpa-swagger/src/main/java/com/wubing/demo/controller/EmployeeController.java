package com.wubing.demo.controller;

import com.wubing.demo.common.RespModel;
import com.wubing.demo.entity.EmployeeDO;
import com.wubing.demo.entity.EmployeeDTO;
import com.wubing.demo.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wubing
 * @date 2019/7/9 11:07
 * @description 员工控制器
 */
@Api(tags = "员工控制器")
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeService employeeService;

    /**
     * @param employeeDTO
     * @return java.lang.String
     * @author wubing
     * @date 2019/7/9
     * @description 添加员工
     */
    @ApiOperation(value = "添加员工",notes = "添加员工")
    @PostMapping("/add")
    public RespModel addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            employeeService.addEmployee(employeeDTO);
            return new RespModel(true, "添加员工成功", "");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            return new RespModel(false, "添加员工失败", "");
        }

    }

    /**
     * @author wubing
     * @date 2019/7/9
     * @param deptId
     * @return com.wubing.demo.common.RespModel
     * @description
     */
    @ApiOperation(value = "获取员工列表",notes = "根据部门id获取")
    @ApiImplicitParam(name = "deptId",value = "部门Id",required = true)
    @GetMapping("/listEmployee")
    public RespModel listEmployee(@RequestParam Integer deptId) {
        if (!"".equals(deptId)) {
            List<EmployeeDO> list = employeeService.listEmployee(deptId);
            if (list.size() > 0) {
                return new RespModel(true, "查到员工了", list);
            } else {
                return new RespModel(false, "查不到员工", "");
            }
        } else {
            return new RespModel(false, "参数错误，查找员工失败", "");
        }
    }

    @ApiOperation(value = "根据员工名查询", notes = "根据员工名查询")
    @ApiImplicitParam(name = "name", value = "员工名称", required = true)
    @GetMapping(value = "/getEmployeeByName")
    public RespModel getEmployeeByName(@RequestParam String name) {
        if (!"".equals(name)) {
            EmployeeDTO employeeDTO = employeeService.getEmployeeByName(name);
            return new RespModel(true, "查到员工失败了", employeeDTO);
        } else {
            return new RespModel(false, "查不到员工失败", "");
        }
    }

    @ApiOperation(value = "删除员工", notes = "删除员工")
    @ApiImplicitParam(name = "empId", value = "员工Id", required = true)
    @DeleteMapping(value = "deleteEmployee")
    public RespModel deleteEmployee(@RequestParam Integer empId) {
        if (!"".equals(empId)) {
            try {
                employeeService.deleteEmployee(empId);
                return new RespModel(true, "删除员工成功", "");
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } finally {
                return new RespModel(false, "删除员工失败", "");
            }
        } else {
            return new RespModel(false, "参数错误，删除员工失败", "");
        }
    }

    @ApiOperation(value = "修改员工", notes = "修改员工")
    @PutMapping(value = "updateEmployee")
    public RespModel updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            employeeService.updateEmployee(employeeDTO);
            return new RespModel(true, "修改员工成功", "");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            return new RespModel(true, "修改员工失败", "");
        }
    }
}
