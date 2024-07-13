package com.cbccafe.service;

import com.cbccafe.entity.Employee;
import com.cbccafe.payload.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public ResponseEntity<ResponseMessage> addEmployee(@RequestBody Employee employee);
    public List<Employee> employeeList();
    public Optional<Employee> employeeById(Long id);
    public ResponseEntity<ResponseMessage> updateEmployeeById(Employee employee, Long id);
    public ResponseEntity<ResponseMessage> deleteEmployee(Long id);
}
