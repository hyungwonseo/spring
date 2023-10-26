package com.dw.employeeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.employeeManagement.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
