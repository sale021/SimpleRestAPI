package com.demo.task.crudoverrest.crudapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.task.crudoverrest.crudapi.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT e FROM Employee e WHERE (:id is null or e.id = :id) and (:name is null or e.name = :name) and (:team is null"
			+ " or e.team = :team)")
	List<Employee> search(@Param("id") Long id, @Param("name") String name, @Param("team") String team);
}
