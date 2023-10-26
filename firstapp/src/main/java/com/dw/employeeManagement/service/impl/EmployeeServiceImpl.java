package com.dw.employeeManagement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dw.employeeManagement.model.Employee;
import com.dw.employeeManagement.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	// 직원정보를 새로 생성한다
	@Override
    public Employee saveEmployee(Employee employee) {
		System.out.println(employee.getFirstName());
    	return employee;
    }
	// 전체 직원정보를 조회한다
	@Override
	public List<Employee> getAllEmployees() {
		return null;
	}
	// ID로 직원 한명의 정보를 조회한다
	@Override
	public Employee getEmployeeById(long id) {
		return null;
	}
	// ID로 직원 한명의 정보를 업데이트한다
	@Override
	public Employee updateEmployeeById(Employee employee, long id) {
		return null;
	}
	// ID로 직원 한명의 정보를 삭제한다
	@Override
	public void deleteEmployee(long id) {
		
	}
}
