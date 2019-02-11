package com.mitsioulis.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mitsioulis.model.Attribute;
import com.mitsioulis.model.Employee;
import com.mitsioulis.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	public Optional<Employee> getEmployeeById(String employeeId) {
		return employeeRepository.findById(employeeId);
	}
 	
	public Map<String, Attribute> getAllAttributesByEmployeeId(String employeeId) {
		Employee emp = getEmployeeById(employeeId).get();
		return emp.getAttributes();
    }
	
	public Optional<Employee> addEmployee(Employee employee) {
		if(employee.getEMP_Name().equals(null) || employee.getSupervisor().getEMP_ID().equals(null)
				|| employee.getEMP_Name().trim().equals("") || employee.getSupervisor().getEMP_ID().trim().equals("")) {
			return null;
		}
		return Optional.ofNullable(employeeRepository.save(employee));
		
	}
	
	public Optional<Employee> updateEmployee(Employee employee, String employeeId) {
		if(employee.getEMP_Name().equals(null) || employee.getEMP_Name().trim().equals("")) {
			return null;
		}
		Optional<Employee> empOptional = employeeRepository.findById(employeeId);
		Employee sup = empOptional.get().getSupervisor();
		if (!empOptional.isPresent())
			return null;
		Map<String, Attribute> attrs = empOptional.get().getAttributes();
		employee.setEMP_ID(employeeId);
		employee.setAttributes(attrs);
		Employee sup2 = employee.getSupervisor();
		if (sup2==null) {
			employee.setSupervisor(sup);
		}
		employeeRepository.save(employee);
		return Optional.ofNullable(employee);
	}
	
	public Boolean deleteEmployee(String employeeId) {
		Optional<Employee> empOptional = employeeRepository.findById(employeeId);
		
		if (empOptional.isPresent()) {
			employeeRepository.delete(empOptional.get());
			return true;
		}
		return false;
	}
}
