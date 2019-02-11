package com.mitsioulis.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mitsioulis.model.Attribute;
import com.mitsioulis.model.Employee;

@Service
public class ModelService {

	private AttributeService attributeService;
	private EmployeeService employeeService;

	public ModelService(AttributeService attributeService, EmployeeService employeeService) {
		this.attributeService = attributeService;
		this.employeeService = employeeService;
	}

	public Optional<Attribute> addAttributeToEmployee(Attribute attribute, String employeeId) {
		if(attribute.getATTR_Name().equals(null) || attribute.getATTR_Value().equals(null)
				|| attribute.getATTR_Name().trim().equals("") || attribute.getATTR_Value().equals("")) {
			return null;
		}
		Optional<Employee> empOptional = employeeService.getEmployeeById(employeeId);
		Employee emp = null;
		String empName = null;
		if (empOptional.isPresent()) {
			emp = empOptional.get();
			empName = empOptional.get().getEMP_Name();
			attributeService.addAttribute(attribute);
		}
		Attribute newAttr = attributeService.getAttributeById(attribute.getATRR_ID()).get();
		emp.getAttributes().put(newAttr.getATRR_ID(), newAttr);
		employeeService.updateEmployee(emp, employeeId);
		return Optional.ofNullable(attribute);
	}
	
	public Optional<Attribute> updateAttributeFromEmployee(Attribute attribute, String attributeId, String employeeId) {
		if(attribute.getATTR_Name().equals(null) || attribute.getATTR_Value().equals(null)
				|| attribute.getATTR_Name().trim().equals("") || attribute.getATTR_Value().equals("")) {
			return null;
		}
		Optional<Attribute> attrOptional = attributeService.getAttributeById(attributeId);
		if (!attrOptional.isPresent())
			return null;
		attribute.setATRR_ID(attributeId);
		attributeService.updateAttribute(attribute, attributeId);
		return attrOptional;
	}

	public boolean deleteAttributeFromEmployee(String attributeId, String employeeId) {
		Optional<Employee> empOptional = employeeService.getEmployeeById(employeeId);
		if (empOptional.isPresent()) {
			empOptional.get().getAttributes().remove(attributeId);

			employeeService.updateEmployee(empOptional.get(), employeeId);
			return true;
		}
		return false;
	}

	public AttributeService getAttributeService() {
		return attributeService;
	}

	public void setAttributeService(AttributeService attributeService) {
		this.attributeService = attributeService;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

}
