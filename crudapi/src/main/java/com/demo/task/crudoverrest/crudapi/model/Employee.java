package com.demo.task.crudoverrest.crudapi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employee")
public class Employee {

	/**
	 * Unique identifier of the employee
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * Full name of the employee
	 */
	@Column(name = "name")
	private String name;

	/**
	 * Employee's working department
	 */
	@Column(name = "team")
	private String team;

	/**
	 * Employee's subordinates
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "superior")
	private Set<Employee> subordinates = new HashSet<>();

	/**
	 * Employee's superior
	 */
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "superior_id")
	private Employee superior;

	public Employee() {
	}

	public Employee(String name, String team) {
		super();
		this.name = name;
		this.team = team;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Set<Employee> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(Set<Employee> subordinates) {
		this.subordinates = subordinates;
	}

	public Employee getSuperior() {
		return superior;
	}

	public void setSuperior(Employee superior) {
		this.superior = superior;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", team=" + team + ", superior=" + superior + "]";
	}

}
