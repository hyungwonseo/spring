package com.dw.employeeManagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dw.employeeManagement.model.Employee;

@RestController
public class EmployeeController {

	//localhost:8080/api/employee
	@PostMapping("/api/employee")
	public ResponseEntity<Employee> saveEmployee(
			@RequestBody Employee employee) {
		System.out.println(employee.getFirstName());
		return ResponseEntity.ok(employee);
	}
}







