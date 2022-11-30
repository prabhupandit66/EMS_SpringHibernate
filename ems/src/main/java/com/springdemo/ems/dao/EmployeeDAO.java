package com.springdemo.ems.dao;

import java.util.List;
import java.util.Optional;

import com.springdemo.ems.entity.Employee;

public interface EmployeeDAO {
	
	public List<Employee> findAllEmployee();
	
	public Employee findById(int employeeId);
	
	public void save(Employee theEmployee);
	
	public void deleteById(int theId);
	
	public List<Employee> searchEmployee(String searchName);

}
