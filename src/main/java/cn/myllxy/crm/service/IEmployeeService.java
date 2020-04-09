package cn.myllxy.crm.service;


import cn.myllxy.crm.base.service.IBaseService;
import cn.myllxy.crm.domain.Employee;

import java.util.List;

public interface IEmployeeService extends IBaseService<Employee, Long> {
    List<Employee> getEmployee();

    Employee getEmployeeByName(String name);
}
