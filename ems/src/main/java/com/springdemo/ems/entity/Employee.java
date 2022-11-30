package com.springdemo.ems.entity;

import javax.validation.constraints.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="first_name")
	@NotNull(message="must not be null")
	@Size(min=2,message="first name is required")
	private String firstName;
	
	@Column(name="last_name")
	@NotNull(message="must not be null")
	@Size(min=2,message="last name is required")
	private String lastName;
	
	@Column(name="email")
	@Email(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", flags=Pattern.Flag.CASE_INSENSITIVE)
	private String email;
	
	@Column(name="designation")
	@NotNull(message="must not be null")
	@Size(min=5,message="designation is required")
	private String designation;
	
	public Employee() {
		
	}
	public Employee(String firstName, String lastName, String email, String designation) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.designation = designation;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", designation=" + designation + "]";
	}
	
	

}
