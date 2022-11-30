package com.springdemo.ems.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.springdemo.ems.dao.EmployeeDAO;
import com.springdemo.ems.entity.Employee;
import com.springdemo.ems.exception.EmployeeNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	//Inject Dependency employee dao
	private EmployeeDAO employeeDAO;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;

		}

	@Override
	@Transactional
	public List<Employee> findAllEmployee() {
		List<Employee> list =  employeeDAO.findAllEmployee();
		if(list.size() > 0) {
		      return list;
		    } else {
		      return new ArrayList<Employee>();
		    }
	}

	@Override
	@Transactional
	public Employee findById(@PathVariable int employeeId){
		
		return employeeDAO.findById(employeeId);
	}

	@Override
	@Transactional
	public void save(Employee theEmployee) {
		
		employeeDAO.save(theEmployee);
		
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		employeeDAO.deleteById(theId);
		
	}

	@Override
	@Transactional
	public List<Employee> searchEmployee(String searchName) {
		
		return employeeDAO.searchEmployee(searchName);
	}
	
}
