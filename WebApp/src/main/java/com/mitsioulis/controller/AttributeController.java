package com.mitsioulis.controller;

import java.util.Map;
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

import com.mitsioulis.model.Attribute;
import com.mitsioulis.service.ModelService;

@RestController
@RequestMapping("employees/{employeeId}/attributes")
public class AttributeController {

	private ModelService modelService;
	
	public AttributeController(ModelService modelService) {
		this.modelService = modelService;
	}

	@GetMapping({"","/"})
	public ResponseEntity<Map<String, Attribute>> getAllAttributes(@PathVariable("employeeId")String employeeId) {
		return ResponseEntity.ok().body(modelService.getEmployeeService().getAllAttributesByEmployeeId(employeeId));
	}
	
	@GetMapping("/{attributeId}")
	public ResponseEntity<Optional<Attribute>> getAttributeById(@PathVariable("attributeId")String attributeId) {
		return Optional 
				.ofNullable(modelService.getAttributeService().getAttributeById(attributeId))
				.map(attribute -> ResponseEntity.ok().body(attribute))
				.orElseGet( () -> ResponseEntity.notFound().build());
	}

	@PostMapping({"","/"})
	public ResponseEntity<Optional<Attribute>> addAttributeToEmployee(@RequestBody Attribute attribute, 
			@PathVariable("employeeId") String employeeId){
		return Optional 
				.ofNullable(modelService.addAttributeToEmployee(attribute,employeeId))
				.map(attribute2 -> ResponseEntity.status(201).body(attribute2))
				.orElseGet( () -> ResponseEntity.status(422).build());
	}
	
	@PutMapping("/{attributeId}")
	public ResponseEntity<Optional<Attribute>> updateAttributeFromEmployeeId(@RequestBody Attribute attribute, 
			@PathVariable("employeeId") String employeeId, @PathVariable("attributeId") String attributeId){
		return Optional 
				.ofNullable(modelService.updateAttributeFromEmployee(attribute,attributeId, employeeId))
				.map(attribute2 -> ResponseEntity.status(201).body(attribute2))
				.orElseGet( () -> ResponseEntity.status(422).build());
	}
	
	@DeleteMapping("/{attributeId}")
	public ResponseEntity<Attribute> deleteAttributeFromEmployee(@PathVariable("attributeId") String attributeId,
			@PathVariable("employeeId") String employeeId){
		if (modelService.deleteAttributeFromEmployee(attributeId,employeeId))
			 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			return ResponseEntity.notFound().build();
	}
	
}
