package com.spring.rest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.model.Role;
import com.spring.rest.repository.RoleRepository;
import com.spring.rest.service.RoleService;

@RestController
public class RoleController {
	
	private RoleService roleService;
	private RoleRepository roleRepository;
	public RoleController(RoleService roleService, RoleRepository roleRepository) {
		this.roleService = roleService;
		this.roleRepository = roleRepository;
	}
	
	@GetMapping("roles/all")
	public List<Role> getAllRoles()
	{
		return roleRepository.findAll();
	}
	@GetMapping("roles/{Id}")
	public Role getRole(@PathVariable (value = "Id") long Id)
	{
		if(roleRepository.findById(Id).isPresent())
			return roleRepository.findById(Id).get();
		else return null;
	}
	@PostMapping("roles/create")
	public ResponseEntity<Object>createRole(@RequestBody Role role)
	{
		return roleService.createRole(role);
	}
	
	@PutMapping("roles/{Id}/update")
	public ResponseEntity<Object> updateRoles(@PathVariable (value = "Id")long Id, @RequestBody Role role)
	{
		return roleService.updateRole(role, Id);
	}
	@DeleteMapping("roles/{Id}/delete")
	public ResponseEntity<Object> deleteRole(@PathVariable (value = "Id") long Id)
	{
		return roleService.deleteRole(Id);
	}
	

}
