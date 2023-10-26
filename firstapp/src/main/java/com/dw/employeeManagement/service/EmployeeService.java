package com.dw.employeeManagement.service;

import java.util.List;

import com.dw.employeeManagement.model.Employee;

public interface EmployeeService {
	
	// 직원정보를 새로 생성한다
	Employee saveEmployee(Employee employee);
	// 전체 직원정보를 조회한다
	List<Employee> getAllEmployees();
	// ID로 직원 한명의 정보를 조회한다
	Employee getEmployeeById(long id);
	// ID로 직원 한명의 정보를 업데이트한다
	Employee updateEmployeeById(Employee employee, long id);
	// ID로 직원 한명의 정보를 삭제한다
	void deleteEmployee(long id);
}






