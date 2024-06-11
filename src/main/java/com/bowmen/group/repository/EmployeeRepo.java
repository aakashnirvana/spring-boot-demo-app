package com.bowmen.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bowmen.group.entity.Employee;

@Repository
public class EmployeeRepo extends JpaRepository<Employee, Long> {
	

}
