package com.mitsioulis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitsioulis.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
