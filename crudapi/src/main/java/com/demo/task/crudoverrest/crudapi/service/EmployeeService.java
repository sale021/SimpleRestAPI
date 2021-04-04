package com.demo.task.crudoverrest.crudapi.service;

import java.util.List;
import java.util.Map;

import com.demo.task.crudoverrest.crudapi.model.Employee;

public interface EmployeeService {

	/**
	 * Retrieve all employees
	 * 
	 * @return list of employees
	 */
	List<Employee> getAll();

	/**
	 * Retrieve an employee by the given id
	 * 
	 * @param id
	 * @return single employee
	 */
	Employee getById(long id);

	/**
	 * Retrieve all employees matching search parameters
	 * 
	 * @param searchParams
	 * @return list of employees
	 */
	List<Employee> search(Map<String, String> searchParams);

	/**
	 * Create new employee
	 * 
	 * @param employee
	 * @return newly created employee
	 */
	Employee createEmployee(Employee employee, Long parentId);

	/**
	 * Update employee
	 * 
	 * @param employee
	 * @param id
	 * @return updated employee
	 */
	Employee updateEmployee(Employee employee, long id);

	/**
	 * Delete employee
	 * 
	 * @param id
	 * @return the response entity
	 */
	void deleteEmployee(long id);
}
