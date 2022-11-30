package com.springdemo.ems.service;

import java.util.List;
import com.springdemo.ems.entity.Employee;

public interface EmployeeService {
	
	public List<Employee> findAllEmployee();
	
	public Employee findById(int employeeId);
	
	public void save(Employee theEmployee);
	
	public void deleteById(int theId);
	
	public List<Employee> searchEmployee(String searchName);
}
