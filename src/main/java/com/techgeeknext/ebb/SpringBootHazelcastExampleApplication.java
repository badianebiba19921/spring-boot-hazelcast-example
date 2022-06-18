package com.techgeeknext.ebb;

import com.techgeeknext.ebb.model.Employee;
import com.techgeeknext.ebb.service.EmployeeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableCaching
public class SpringBootHazelcastExampleApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootHazelcastExampleApplication.class, args);

		EmployeeService employeeService = context.getBean(EmployeeService.class);
		// Insert Employees in the Table
		Employee emp= new Employee();
		emp.setEmpId("111");
		emp.setEmpName("John");

		Employee emp1= new Employee();
		emp1.setEmpId("222");
		emp1.setEmpName("Miler");

		Employee emp2= new Employee();
		emp2.setEmpId("333");
		emp2.setEmpName("Nick");

		employeeService.insertEmployee(emp);

		List<Employee> employees = new ArrayList<>();
		employees.add(emp1);
		employees.add(emp2);
		employeeService.insertEmployees(employees);

		System.out.println("Main Class - First Time retrieving Employee Record from Service Class");
		employeeService.getAllEmployees().forEach(employee-> System.out.println(employee.toString()));

		System.out.println("Main Class - Second Time onwards retrieving Employee Record from Hazelcast");
		employeeService.getAllEmployees().forEach(employee-> System.out.println(employee.toString()));
	}
}
