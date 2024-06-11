package com.bowmen.group.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bowmen.group.entity.Employee;
import com.bowmen.group.repository.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;

	public Employee addEmployee(Employee employee) {

		return employeeRepo.save(employee);

	}

	public List<Employee> getAllEmployees() {

		return employeeRepo.findAll();
	}

	public Optional<Employee> findById(long id) {

		return employeeRepo.findById(id);
	}

	public Employee updateEmployee(long id, Employee employee) {
		Optional<Employee> empOptional = employeeRepo.findById(id);
		if (empOptional.isPresent()) {
			Employee emp = empOptional.get();
			emp.setEmpName(employee.getEmpName());
			return employeeRepo.save(emp);
		}
		return null;

	}

	public String deleteEmployee(long id) {
		Optional<Employee> empOptional = employeeRepo.findById(id);
		if (empOptional.isPresent()) {
			employeeRepo.deleteById(id);
			return "Employee delete Sucessfully";

		} else
			return "Employee Not Found";
	}

}
