package com.bowmen.group.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.bowmen.group.entity.Employee;
import com.bowmen.group.service.EmployeeService;

@RestController
@RequestMapping("employee")
public class EmployeeController {

	@Autowired
	EmployeeService service;

	@GetMapping
	public ResponseEntity<?> getAllEmployee() {
		List<Employee> employees = service.getAllEmployees();
		if (employees == null || employees.isEmpty())
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

		else
			return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);

	}

	@GetMapping("{id}")
	public ResponseEntity<?> getEmployee(@PathVariable("id") long id) {
		Optional<Employee> employee = service.findById(id);

		if (employee.isEmpty())
			return new ResponseEntity<Object>("Employee Not found", HttpStatus.NOT_FOUND);

		else
			return new ResponseEntity<Employee>(employee.get(), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<?> saveEmployee(@RequestBody Employee entity) {

		return new ResponseEntity<Employee>(service.addEmployee(entity), HttpStatus.CREATED);
	}

	@PutMapping("{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable long id, @RequestBody Employee entity) {
		Employee employee = service.updateEmployee(id, entity);

		if (employee == null)
			return new ResponseEntity<Object>("Employee Not found", HttpStatus.NOT_FOUND);

		else
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable long id) {

		String result = service.deleteEmployee(id);
		if (result.contains("Not"))
			return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);
	}

}
