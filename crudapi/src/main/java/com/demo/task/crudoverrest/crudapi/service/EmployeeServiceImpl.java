package com.demo.task.crudoverrest.crudapi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.task.crudoverrest.crudapi.exception.ResourceNotFoundException;
import com.demo.task.crudoverrest.crudapi.model.Employee;
import com.demo.task.crudoverrest.crudapi.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final String EMPLOYEE_NOT_FOUND_WITH_ID = "Employee not found with id: ";

	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * @inheritDoc
	 */
	@Override
	public List<Employee> getAll() {
		return this.employeeRepository.findAll();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Employee getById(long id) {
		return this.employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(EMPLOYEE_NOT_FOUND_WITH_ID + id));
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public List<Employee> search(Map<String, String> searchParams) {
		List<Employee> employees = this.employeeRepository.search(
				searchParams.get("id") != null ? Long.valueOf(searchParams.get("id")) : null, searchParams.get("name"),
				searchParams.get("team"));
		if (employees.isEmpty()) {
			throw new ResourceNotFoundException("No employees found for the given criteria");
		} else {
			return employees;
		}
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Employee createEmployee(Employee employee, Long parentId) {
		if (parentId != null) {
			Employee superior = this.employeeRepository.findById(parentId)
					.orElseThrow(() -> new ResourceNotFoundException(EMPLOYEE_NOT_FOUND_WITH_ID + parentId));
			employee.setSuperior(superior);
			return this.employeeRepository.save(employee);
		} else {
			return this.employeeRepository.save(employee);
		}
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Employee updateEmployee(Employee employee, long id) {
		Employee existingEmployee = this.employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(EMPLOYEE_NOT_FOUND_WITH_ID + id));
		existingEmployee.setName(employee.getName());
		existingEmployee.setTeam(employee.getTeam());
		return this.employeeRepository.save(existingEmployee);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void deleteEmployee(long id) {
		Employee existingEmployee = this.employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(EMPLOYEE_NOT_FOUND_WITH_ID + id));
		this.employeeRepository.delete(existingEmployee);
	}

}
