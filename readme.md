# Interview Homework

>This is simple Spring Boot REST application that demonstrates functionality of basic CRUD operations.

## Technologies

The following technologies were used for the purposes of this project

- Framework:  "Spring Boot 2.4.4"
- Build tool: "Maven"
- Language:   "Java 1.8"
- Packaging:  "Jar"
- Database:   "MySQL"

### Dependencies

The following dependencies that are used in project

- Spring Web   
Build web including RESTful applications using spring MVC. Uses Apache Tomcat as the default embedded container.
- MySQL Driver  
MySQL JDBC driver.
- Spring Data JPA  
Persist data in SQL with Java Persistence API using Spring Data and Hibernate.
- Spring Boot DevTools  
Provides fast application restarts and configurations for enhanced development experience.

___

## Project description

Project goal was to create REST application that handles CRUD operations based on the given data structure provided in the table below.

| ID            | Name          | Team        | Team Lead |
| ------------- | ------------- | ----------- | --------- |
| 123456        | Mirko         | Development | Mirko     |
| 987654        | Predrag       | Development | Mirko     |
| 654321        | Petar         | Development | Mirko     |
| 321654        | Vojislav      | Development | Mirko     |

<br/>

The project consists of the following packages

    .
    ├── ...
    ├── com.demo.task.crudoverrest.crudapi      # main package
    │   │
    │   ├── controller                          # controller package
    │   │   └──EmployeeController.java          # REST controller that exposes employee endpoints
    │   │
    │   ├── exception                           # exception package
    │   │   └──ResourceNotFoundException.java   # Exception class used if resource not present in db
    │   │
    │   ├── model                               # model package
    │   │   └──Employee.java                    # Employee model
    │   │
    │   ├── repository                          # repository package
    │   │   └──EmployeeRepository.java          # Repository interface extending JpaRepository
    │   │
    │   ├── service                             # service package
    │   │   ├──EmployeeService.java             # Employee Service interface
    │   │   └──EmployeeServiceImpl.java         # Employee Service implementation
    │   │
    │   └── CrudapiApplication.java             # main class (starting point for Spring Boot application) 
    │
    └── ...

___
## REST endpoint description  

Method that returns all employees
```
@GetMapping
public List<Employee> getAll() {
    return employeeService.getAll();
}
```
Method that returns an employee matching the provided id
```
@GetMapping("/{id}")
public Employee getById(@PathVariable(value = "id") long id) {
    return employeeService.getById(id);
}
```
Method that returns all employees matching the provided search parameters
```
@GetMapping("/search")
public List<Employee> search(@RequestParam Map<String, String> searchParams) {
    return employeeService.search(searchParams);
}
```
Method that creates a new employee with possibility to relate employee's superior during the creation
```
@PostMapping(value = { "/create", "/create/{parentId}" })
public Employee createEmployee(@RequestBody Employee employee,
        @PathVariable(required = false, name = "parentId") Long parentId) {
    return employeeService.createEmployee(employee, parentId);
}
```
Method that updates employee
```
@PutMapping("/{id}")
public Employee updateEmployee(@RequestBody Employee employee, @PathVariable("id") long id) {
    return employeeService.updateEmployee(employee, id);
}
```
Method that removes employee from the db
```
@DeleteMapping("/{id}")
public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") long id) {
    employeeService.deleteEmployee(id);
    return ResponseEntity.ok().build();
}
```
___

## Database settings

Database configuration is specified in application.properties file.  
In order to properly run the application it is neccessary to create database named "employees" and adjust database parameters.
