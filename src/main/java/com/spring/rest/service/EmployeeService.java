package com.spring.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.rest.model.Employee;
import com.spring.rest.model.Role;
import com.spring.rest.repository.EmployeeRepository;
import com.spring.rest.repository.RoleRepository;

@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;
	private RoleRepository roleRepository;
	
	public EmployeeService(EmployeeRepository employeeRepository, RoleRepository roleRepository) {
		this.employeeRepository = employeeRepository;
		this.roleRepository = roleRepository;
	}
	
//	Create new employee
	public ResponseEntity<Object> createEmployee(Employee model)
	{
		Employee employee = new Employee();
		if(employeeRepository.findByEmail(model.getEmail()).isPresent())
		{
			return ResponseEntity.badRequest().body("Email is already present, failed to create new Employee.");
		}
		else {
			employee.setFirstName(model.getFirstName());
			employee.setLastName(model.getLastName());
			employee.setEmail(model.getEmail());
			employee.setDept(model.getDept());
			List<Role> role=model.getRoles();
//			int cnt=0;
			List<Role> tempRole = new ArrayList<>();
			for(Role rl: role)
			{
//				cnt++;
//				System.out.println("Role is :"+cnt+" ----->>> "+rl);
				if(roleRepository.findByNameAndDescription(rl.getName(), rl.getDescription()).isPresent())
				{
//					System.out.println("Role22222 is :"+cnt+" ----->>> "+roleRepository.findByNameAndDescription(rl.getName(), rl.getDescription()).get());
					tempRole.add(roleRepository.findByNameAndDescription(rl.getName(), rl.getDescription()).get());
				}
				else tempRole.add(rl);
				
			}
			employee.setRoles(tempRole);
			
			Employee savedEmployee = employeeRepository.save(employee);
			if(employeeRepository.findById(savedEmployee.getId()).isPresent())
			{
				return ResponseEntity.ok("Employee Created Successfully.");
			}
			else return ResponseEntity.unprocessableEntity().body("Failed to create Employee as Specified");
		}
	}
	
//	Update existing employee
	@Transactional
	public ResponseEntity<Object> updateEmployee(Employee employee, long Id)
	{
		if(employeeRepository.findById(Id).isPresent())
		{
			Employee updatedEmployee=employeeRepository.findById(Id).get();
			updatedEmployee.setFirstName(employee.getFirstName());
			updatedEmployee.setLastName(employee.getLastName());
			updatedEmployee.setEmail(employee.getEmail());
			updatedEmployee.setDept(employee.getDept());
			List<Role> role=employee.getRoles();
//			int cnt=0;
			List<Role> tempRole = new ArrayList<>();
			for(Role rl: role)
			{
//				cnt++;
//				System.out.println("Role is :"+cnt+" ----->>> "+rl);
				if(roleRepository.findByNameAndDescription(rl.getName(), rl.getDescription()).isPresent())
				{
//					System.out.println("Role22222 is :"+cnt+" ----->>> "+roleRepository.findByNameAndDescription(rl.getName(), rl.getDescription()).get());
					tempRole.add(roleRepository.findByNameAndDescription(rl.getName(), rl.getDescription()).get());
				}
				else tempRole.add(rl);	
			}
			updatedEmployee.setRoles(tempRole);
			Employee savedEmployee = employeeRepository.save(updatedEmployee);
			if(employeeRepository.findById(savedEmployee.getId()).isPresent())
				return ResponseEntity.ok("Employee information updated Successfully.");
			else return ResponseEntity.unprocessableEntity().body("Failed to update Employee.");
		}
		else return ResponseEntity.unprocessableEntity().body("Can't find the Employee.");
	}
	
//	Delete employee
	public ResponseEntity<Object> deleteEmployee(long Id)
	{
		if(employeeRepository.findById(Id).isPresent())
		{
			employeeRepository.deleteById(Id);
			if(employeeRepository.findById(Id).isPresent())
				return ResponseEntity.unprocessableEntity().body("Failed to delete the Emoloyee.");
			else return ResponseEntity.ok("Record Deleted Successfully.");
		}
		else return ResponseEntity.unprocessableEntity().body("Can'd find the Employee.");
	}
	
	
	
	
	
	
	
	
}
