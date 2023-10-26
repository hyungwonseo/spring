package com.dw.employeeManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dw.employeeManagement.model.Employee;
import com.dw.employeeManagement.service.EmployeeService;

@RestController
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	//localhost:8080/api/employee
	// 직원정보를 새로 생성한다
	@PostMapping("/api/employee")
	public ResponseEntity<Employee> saveEmployee(
			@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(
				employeeService.saveEmployee(employee),
				HttpStatus.CREATED);
	}
}







