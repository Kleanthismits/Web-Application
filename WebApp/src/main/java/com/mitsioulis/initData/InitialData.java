package com.mitsioulis.initData;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.mitsioulis.model.Employee;
import com.mitsioulis.repository.AttributeRepository;
import com.mitsioulis.repository.EmployeeRepository;

@Component
public class InitialData implements ApplicationListener<ContextRefreshedEvent>{

	private EmployeeRepository employeeRepository;
	private AttributeRepository attributeRepository;
	
	public InitialData(EmployeeRepository employeeRepository, AttributeRepository attributeRepository) {
		this.employeeRepository = employeeRepository;
		this.attributeRepository = attributeRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//initData();
		
	}

	//Initializing data for table employee-attribute(for test purpose)
	private void initData() {
		
		Employee emp = employeeRepository.findById("82D58D49-72A2-42B0-A250-471E5C10D7D9").get();
		emp.getAttributes().put("3C86A592-823B-4B83-952F-F437D08F2EA8",(attributeRepository.findById("3C86A592-823B-4B83-952F-F437D08F2EA8")).get());
		employeeRepository.save(emp);
		
		Employee emp1 = employeeRepository.findById("8CEE7A83-A9EB-4170-B7E8-5D4F0440C074").get();
		emp1.getAttributes().put("70C311F5-B2B0-4118-A069-3AB9C3AC65E1",(attributeRepository.findById("70C311F5-B2B0-4118-A069-3AB9C3AC65E1")).get());
		employeeRepository.save(emp1);
		
		Employee emp2 = employeeRepository.findById("561E2D88-A747-460F-99E1-CFB1D3D8CA5C").get();
		emp2.getAttributes().put("82FF24BB-0180-40F9-B68E-15799556A5C2",(attributeRepository.findById("82FF24BB-0180-40F9-B68E-15799556A5C2")).get());
		employeeRepository.save(emp2);
		
		Employee emp3 = employeeRepository.findById("28106345-435B-4215-AECF-7C226C071E11").get();
		emp3.getAttributes().put("70C311F5-B2B0-4118-A069-3AB9C3AC65E1",(attributeRepository.findById("70C311F5-B2B0-4118-A069-3AB9C3AC65E1")).get());
		employeeRepository.save(emp3);
		
		Employee emp4 = employeeRepository.findById("2E3074E7-8FFB-4C5F-83AE-962812F93D08").get();
		emp4.getAttributes().put("34F8EAC6B-8B29-4716-A597-C8CDE3A3996D",(attributeRepository.findById("4F8EAC6B-8B29-4716-A597-C8CDE3A3996D")).get());
		employeeRepository.save(emp4);
		
		Employee emp5 = employeeRepository.findById("8CEE7A83-A9EB-4170-B7E8-5D4F0440C074").get();
		emp5.getAttributes().put("F27B9C58-FD9E-4EB1-9B09-E01FF7032CC8",(attributeRepository.findById("F27B9C58-FD9E-4EB1-9B09-E01FF7032CC8")).get());
		employeeRepository.save(emp5);
		
		Employee emp6 = employeeRepository.findById("82D58D49-72A2-42B0-A250-471E5C10D7D9").get();
		emp6.getAttributes().put("83382664-DA55-4C6D-8D18-ED79C26332A8",(attributeRepository.findById("83382664-DA55-4C6D-8D18-ED79C26332A8")).get());
		employeeRepository.save(emp6);
		
	}
}
