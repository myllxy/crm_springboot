package cn.myllxy.crm.service.impl;


import cn.myllxy.crm.base.service.impl.BaseServiceImpl;
import cn.myllxy.crm.domain.Employee;
import cn.myllxy.crm.base.mapper.BaseMapper;
import cn.myllxy.crm.mapper.EmployeeMapper;
import cn.myllxy.crm.service.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class EmployeeServiceImpl extends BaseServiceImpl<Employee, Long> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public BaseMapper<Employee, Long> getBaseMapper() {
        return employeeMapper;
    }
    
    

    @Override
    public List<Employee> getEmployee() {
        return employeeMapper.getEmployee();
    }


    @Override
    public Employee getEmployeeByName(String name) {
        return employeeMapper.getEmployeeByName(name);
    }

}
