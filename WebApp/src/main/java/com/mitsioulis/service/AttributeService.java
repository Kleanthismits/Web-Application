package com.mitsioulis.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mitsioulis.model.Attribute;
import com.mitsioulis.repository.AttributeRepository;

@Service
public class AttributeService {

	private AttributeRepository attributeRepository;

	public AttributeService(AttributeRepository attributeRepository) {
		this.attributeRepository = attributeRepository;
	}
	
	public Optional<Attribute> getAttributeById(String attributeId) {
		return attributeRepository.findById(attributeId);
	}
	
	public Optional<Attribute> addAttribute(Attribute attribute) {
		if(attribute.getATTR_Name().equals(null) || attribute.getATTR_Value().equals(null)
				|| attribute.getATTR_Name().trim().equals("") || attribute.getATTR_Value().equals("")) {
			return null;
		}
		return Optional.ofNullable(attributeRepository.save(attribute));
	}
	
	
	public Optional<Attribute> updateAttribute(Attribute attribute, String attributeId) {
		if(attribute.getATTR_Name().equals(null) || attribute.getATTR_Value().equals(null)
				|| attribute.getATTR_Name().trim().equals("") || attribute.getATTR_Value().equals("")) {
			return null;
		}
		Optional<Attribute> attrOptional = attributeRepository.findById(attributeId);
		if (!attrOptional.isPresent())
			return null;
		attribute.setATRR_ID(attributeId);
		return Optional.ofNullable(attributeRepository.save(attribute));
	}
	
	public Boolean deleteAttribute(String attributeId) {
		Optional<Attribute> empOptional = attributeRepository.findById(attributeId);
		
		if (empOptional.isPresent()) {
			attributeRepository.delete(empOptional.get());
			return true;
		}
		return false;
	}
 	
}
