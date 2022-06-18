package com.techgeeknext.ebb.service.impl;

import com.techgeeknext.ebb.model.Employee;
import com.techgeeknext.ebb.service.EmployeeService;

import java.util.List;

@Service
@CacheConfig(cacheNames = "employees")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public void insertEmployee(Employee employee) {
        employeeDao.insertEmployee(employee);
    }

    @Override
    public void insertEmployees(List<Employee> employees) {
        employeeDao.insertEmployees(employees);
    }

    @Override
    @Cacheable()
    public List<Employee> getAllEmployees() {
        System.out.println("Employee Service Layer - Get All Employees");
        return employeeDao.getAllEmployees();

    }

    @Override
    public void getEmployeeById(String empId) {
        Employee employee = employeeDao.getEmployeeById(empId);
        System.out.println(employee);
    }
}
