package com.cbccafe.controller;

import com.cbccafe.entity.Employee;
import com.cbccafe.payload.ResponseMessage;
import com.cbccafe.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<ResponseMessage> addEmployee(@Valid @RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Employee>> employeeList(){
        List<Employee> employeeList = employeeService.employeeList();
        return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
    }

    @GetMapping("/byid/{id}")
    public Optional<Employee> employeeById(@Param("id") Long id){
        return employeeService.employeeById(id);
    }

    @PutMapping("/update/byid/{id}")
    public ResponseEntity<ResponseMessage> updateEmployeeById(@Valid @RequestBody Employee employee, @Param("id") Long id){
        return employeeService.updateEmployeeById(employee,id);
    }

    @DeleteMapping("/delete/byid/{id}")
    public ResponseEntity<ResponseMessage> eventDeleteById(@Param("id") Long id){
        return employeeService.deleteEmployee(id);
    }
}
