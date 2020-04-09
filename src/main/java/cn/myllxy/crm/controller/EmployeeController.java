package cn.myllxy.crm.controller;


import cn.myllxy.crm.domain.Employee;
import cn.myllxy.crm.query.EmployeeQuery;
import cn.myllxy.crm.service.IEmployeeService;
import cn.myllxy.crm.utils.AjaxResult;
import cn.myllxy.crm.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @PatchMapping("list")
    @ResponseBody
    public List<Employee> list() {
        return employeeService.selectAll();
    }

    @PatchMapping("/selectPageByQuery")
    @ResponseBody
    /* 这里注意RequestBody和RequestParm的区别 */
    public PageResult<Employee> selectPageByQuery(@RequestBody EmployeeQuery employeeQuery) {
        return employeeService.selectPageByQuery(employeeQuery);
    }
    @PutMapping("/save")
    @ResponseBody
    public AjaxResult save(@RequestBody Employee employee) {
        try {
            employeeService.save(employee);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "添加失败！" + e.getMessage());
        }
    }
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(@RequestBody Employee employee) {
        try {
            employeeService.update(employee);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "修改失败！" + e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id) {
        try {
            employeeService.delete(id);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "修改失败！" + e.getMessage());
        }
    }

    @PatchMapping("/batchDelete")
    @ResponseBody
    public AjaxResult batchDelete(@RequestBody List<Employee> employees) {
        try {
            employeeService.batchDelete(employees);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "删除失败！" + e.getMessage());
        }
    }

    /**
     * 查询单个对象
     *
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    @ResponseBody
    public Employee get(@PathVariable("id") Long id) {
        return employeeService.selectById(id);
    }

    @PostMapping("/getEmployee")
    @ResponseBody
    public List<Employee> getEmployee() {
        return employeeService.getEmployee();
    }


    /**
     * 通过name来找到employee对象
     *
     * @param name
     * @return
     */
    @GetMapping("/getEmployeeByName/{name}")
    @ResponseBody
    public Employee getEmployeeByName(@PathVariable("name") String name) {
        return employeeService.getEmployeeByName(name);
    }
}
