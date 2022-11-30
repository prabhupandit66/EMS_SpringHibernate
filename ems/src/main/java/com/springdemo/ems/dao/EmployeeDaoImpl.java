package com.springdemo.ems.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.springdemo.ems.entity.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDAO {
	
	//Inject EntityManager same like we do for SessionFactory in Hibernate
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> findAllEmployee() {
		
		//get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//create a query and fetch
		List<Employee> employees = currentSession.createQuery("from Employee", Employee.class).getResultList();
		
		return employees;
	}

	@Override
	public Employee findById(int employeeId) {

		//get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//get employee
		Employee employee = currentSession.get(Employee.class, employeeId);
		
		return employee;
	}

	@Override
	public void save(Employee employee) {
		
		//get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(employee);
	}

	@Override
	public void deleteById(int theId) {

		//get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//delete object with P.Key
		Query theQuery = currentSession.createQuery("delete from Employee where id= :employeeId");
				
		theQuery.setParameter("employeeId", theId);
				
		theQuery.executeUpdate();
		
		
		
	}

	@Override
	public List<Employee> searchEmployee(String searchName) {
		
		//get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		Query theQuery = null;
		
		//search only if searchName is emply
		if (searchName != null && searchName.trim().length() > 0) {
			//search for the name
			theQuery = currentSession.createQuery("from Employee where lower(firstName) like:theName or lower(lastName) like:theName", Employee.class);
			//set
			theQuery.setParameter("theName", "%" + searchName.toLowerCase() + "%");
			
		} else {
			//searchName is empty...get all customers
			theQuery = currentSession.createQuery("from Customer order by firstName", Employee.class);
		}
				
				//execute query and get result list
				List<Employee> employees = theQuery.getResultList();
				
				//return the results
				return employees;
	}
				
	}

