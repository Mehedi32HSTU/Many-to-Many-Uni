package com.spring.rest.model;

import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String email;
	private String dept;
	
	@ManyToMany(targetEntity = Role.class, cascade = CascadeType.ALL)
	List<Role> roles;

	public Employee() {
		
	}

	public Employee(String firstName, String lastName, String email, String dept) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dept = dept;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
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

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	

}
