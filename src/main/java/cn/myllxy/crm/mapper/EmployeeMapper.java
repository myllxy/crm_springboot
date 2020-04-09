package cn.myllxy.crm.mapper;

import cn.myllxy.crm.base.mapper.BaseMapper;
import cn.myllxy.crm.domain.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper extends BaseMapper<Employee, Long> {
    Employee selectByUsername(String username);

    List<Employee> getEmployee();

    Employee getEmployeeByName(String name);
}