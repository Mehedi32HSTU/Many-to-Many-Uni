package com.spring.rest.controller;

import java.util.List;

import org.hibernate.annotations.Parent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.model.Employee;
import com.spring.rest.repository.EmployeeRepository;
import com.spring.rest.service.EmployeeService;

@RestController
public class EmployeeController {
	
	private EmployeeService employeeService;
	private EmployeeRepository employeeRepository;
	
	public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository) {
		this.employeeService = employeeService;
		this.employeeRepository = employeeRepository;
	}
	
	@GetMapping("employees/all")
	public List<Employee> getAllEmployees()
	{
		return employeeRepository.findAll();
	}
	
	@GetMapping("employees/{Id}")
	public Employee getEmployee(@PathVariable (value="Id") long Id)
	{
		if(employeeRepository.findById(Id).isPresent())
			return employeeRepository.findById(Id).get();
		else return null;
	}
	
	@PostMapping("employees/create")
	public ResponseEntity<Object> createEmployee(@RequestBody Employee employee)
	{
		return employeeService.createEmployee(employee);
	}
	
	@PutMapping("employees/{Id}/update")
	public ResponseEntity<Object> updateEmployee(@PathVariable (value = "Id") long Id, @RequestBody Employee employee)
	{
		return employeeService.updateEmployee(employee, Id);
	}
	@DeleteMapping("employees/{Id}/delete")
	public ResponseEntity<Object> deleteEmployee(@PathVariable (value = "Id") long Id)
	{
		return employeeService.deleteEmployee(Id);
	}
	
	
	

}
