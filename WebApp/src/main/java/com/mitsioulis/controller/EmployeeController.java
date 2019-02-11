package com.mitsioulis.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitsioulis.model.Employee;
import com.mitsioulis.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping({"","/"})
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> emps = employeeService.getAllEmployees();
		return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(),HttpStatus.OK);
		}
	
	@GetMapping("/{employeeId}")
	public Optional<ResponseEntity<Employee>> getEmployeeById(@PathVariable("employeeId")String employeeId){
		return Optional 
				.ofNullable(employeeService.getEmployeeById(employeeId)
				.map(employee -> ResponseEntity.ok().body(employee))
				.orElseGet( () -> ResponseEntity.notFound().build()) );
		}
	
	@PostMapping({"","/"})
	public Optional<ResponseEntity<Employee>> addEmployee(@RequestBody Employee employee) {
		return Optional 
				.ofNullable(employeeService.addEmployee(employee)
				.map(employee2 -> ResponseEntity.status(201).body(employee2))
				.orElseGet( () -> ResponseEntity.status(422).build()) );
	}
	
	@PutMapping("/{employeeId}")
	public Optional<ResponseEntity<Employee>> updateEmployee(@RequestBody Employee employee, 
			@PathVariable("employeeId") String employeeId)  {
		return Optional 
				.ofNullable(employeeService.updateEmployee(employee, employeeId)
				.map(employee2 -> ResponseEntity.ok().body(employee2))
				.orElseGet( () -> ResponseEntity.status(422).build()) );
	}
	
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("employeeId") String employeeId) {
		if (employeeService.deleteEmployee(employeeId))
		 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return ResponseEntity.notFound().build();
	}
	

	
	
	
}
