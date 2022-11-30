package com.springdemo.ems.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springdemo.ems.entity.Employee;
import com.springdemo.ems.exception.EmployeeNotFoundException;
import com.springdemo.ems.service.EmployeeService;

@Controller
@RequestMapping("/ems")
public class EmployeeController {
	
	//inject employee service
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	//get all employees
	/*@GetMapping("/employees")
	public List<Employee> findAllEmployee(){
		return employeeService.findAllEmployee();
		
	}*/
	
	@GetMapping("/employees")
	public String findAllEmployee(Model model){
		List<Employee> list =  employeeService.findAllEmployee();
		model.addAttribute("employees",list);
		return "index";
		
	}
	
	@GetMapping("/employees/{employeeId}")
	public Employee findById(@PathVariable int employeeId) {

		Employee employee = employeeService.findById(employeeId);
			if (employee == null) {
				throw new EmployeeNotFoundException("Employee id not found : " + employeeId);
			}
			return employee;
			
		}
	
	@GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "addEmployee";
    }
	
	@PostMapping("/saveEmployee")
    public String save(@Valid Employee employee, BindingResult theBindingResult, Model model) {
		
		if (theBindingResult.hasErrors()) {
			return "addEmployee"; 
		}
        // save employee to database
        employeeService.save(employee);
        return "redirect:/ems/employees";
    }
	
	 @GetMapping("/showFormForUpdate/{employeeId}")
	    public String showFormForUpdate(@PathVariable int employeeId, Model model) {

	        // get employee from the service
	        Employee employee = employeeService.findById(employeeId);

	        // set employee as a model attribute to pre-populate the form
	        model.addAttribute("employee", employee);
	        
	        return "updateEmployee";
	    }
	 
	 @GetMapping("/deleteEmployee/{employeeId}")
	 public String deleteEmployee(@PathVariable int employeeId) {
		 
		 Employee employee = employeeService.findById(employeeId);
			if (employee == null) {
				throw new EmployeeNotFoundException("Employee id not found : " + employeeId);
			}
		 employeeService.deleteById(employeeId);
		 return "redirect:/ems/employees";
	 }
	 
	 @GetMapping("/search")
	private String searchEmployee(@RequestParam("searchName") String searchName, Model theModel) {
			
			//get Customers from DAO
			List<Employee> theEmployee = employeeService.searchEmployee(searchName);
			
			//add customers to the model
			theModel.addAttribute("employees", theEmployee);
			
			return "index";
		}
	 
	
	}


