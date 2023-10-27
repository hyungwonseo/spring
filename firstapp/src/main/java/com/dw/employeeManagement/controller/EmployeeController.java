package com.dw.employeeManagement.controller;

import java.util.List;

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

import com.dw.employeeManagement.model.Employee;
import com.dw.employeeManagement.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	//localhost:8080/api/employee
	// 직원정보를 새로 생성한다
	@PostMapping()
	public ResponseEntity<Employee> saveEmployee(
			@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(
				employeeService.saveEmployee(employee),
				HttpStatus.CREATED);
	}
	
	// 전체 직원정보를 조회한다
	@GetMapping()
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();		
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	
	// ID로 직원 한명의 정보를 조회한다
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(
			@PathVariable("id") long id) {
		return new ResponseEntity<Employee>(
				employeeService.getEmployeeById(id), HttpStatus.OK);
	}
	
	// ID로 직원 한명의 정보를 업데이트한다
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployeeById(
			@RequestBody Employee employee, @PathVariable long id) {
		return new ResponseEntity<Employee>(
				employeeService.updateEmployeeById(employee, id),
				HttpStatus.OK);
	}
	
	// ID로 직원 한명의 정보를 삭제한다
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<String>("Employee " + id + " deleted successfully"
				, HttpStatus.OK);
	}
	
}







