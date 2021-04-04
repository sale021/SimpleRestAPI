package com.demo.task.crudoverrest.crudapi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.task.crudoverrest.crudapi.model.Employee;
import com.demo.task.crudoverrest.crudapi.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * Retrieve all employees
	 * 
	 * @return list of employees
	 */
	@GetMapping
	public List<Employee> getAll() {
		return employeeService.getAll();
	}

	/**
	 * Retrieve an employee by the given id
	 * 
	 * @param id
	 * @return single employee
	 */
	@GetMapping("/{id}")
	public Employee getById(@PathVariable(value = "id") long id) {
		return employeeService.getById(id);
	}

	/**
	 * Retrieve all employees matching search parameters
	 * 
	 * @param searchParams
	 * @return list of employees
	 */
	@GetMapping("/search")
	public List<Employee> search(@RequestParam Map<String, String> searchParams) {
		return employeeService.search(searchParams);
	}

	/**
	 * Create new employee
	 * 
	 * @param employee
	 * @return newly created employee
	 */
	@PostMapping(value = { "/create", "/create/{parentId}" })
	public Employee createEmployee(@RequestBody Employee employee,
			@PathVariable(required = false, name = "parentId") Long parentId) {
		return employeeService.createEmployee(employee, parentId);
	}

	/**
	 * Update employee
	 * 
	 * @param employee
	 * @param id
	 * @return updated employee
	 */
	@PutMapping("/{id}")
	public Employee updateEmployee(@RequestBody Employee employee, @PathVariable("id") long id) {
		return employeeService.updateEmployee(employee, id);
	}

	/**
	 * Delete employee
	 * 
	 * @param id
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") long id) {
		employeeService.deleteEmployee(id);
		return ResponseEntity.ok().build();
	}
}
