package com.spring.rest.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.rest.model.Role;
import com.spring.rest.repository.RoleRepository;

@Service
public class RoleService {
	
	private RoleRepository roleRepository;

	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
//	Create new role
	public ResponseEntity<Object> createRole(Role role)
	{
		if(roleRepository.findByNameAndDescription(role.getName(), role.getDescription()).isPresent())
		{
			return ResponseEntity.badRequest().body("Role is already present, failed to create new Role.");
		}
		else {
			Role newRole= new Role();
			newRole.setName(role.getName());
			newRole.setDescription(role.getDescription());
			Role savedRole=roleRepository.save(newRole);
			if(roleRepository.findById(savedRole.getId()).isPresent())
				return ResponseEntity.accepted().body("New role created successfully.");
			else return ResponseEntity.unprocessableEntity().body("Failed to create new role");
		}
	}
//	Update Roles
	public ResponseEntity< Object> updateRole(Role role, long Id)
	{
		if(roleRepository.findById(Id).isPresent())
		{
			Role newRole= roleRepository.findById(Id).get();
			newRole.setDescription(role.getDescription());
			newRole.setName(role.getName());
			Role savedRole = roleRepository.save(newRole);
			if(roleRepository.findById(Id).isPresent())
				return ResponseEntity.accepted().body("Role Updated successfully.");
			else return ResponseEntity.badRequest().body("Failed to update.");
			
		}
		else return ResponseEntity.unprocessableEntity().body("Specified role not found.");
	}
	
//	Delete Role
	public ResponseEntity<Object> deleteRole(long Id)
	{
		if(roleRepository.findById(Id).isPresent())
		{
			roleRepository.deleteById(Id);
			if(roleRepository.findById(Id).isPresent())
				return ResponseEntity.unprocessableEntity().body("Failed to delete");
			else return ResponseEntity.ok().body("Deleted Successfully.");
		}
		else return ResponseEntity.badRequest().body("Role not found.");
	}
	
	
	
	
	
	
	
	
}
