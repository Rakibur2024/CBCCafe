package com.cbccafe.service.implementation;

import com.cbccafe.entity.Employee;
import com.cbccafe.payload.ResponseMessage;
import com.cbccafe.repository.EmployeeRepository;
import com.cbccafe.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepo;

    ResponseMessage responseMessage;

    @Override
    public ResponseEntity<ResponseMessage> addEmployee(@RequestBody Employee employee){
        if(employeeRepo.countByContactNo(employee.getContactNo()) > 0){
            responseMessage = new ResponseMessage("Employee already exists !");
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        } else {
            employee.setEnteredAt(LocalDate.now());
            Employee savedEmployee = employeeRepo.save(employee);
            responseMessage = new ResponseMessage("Employee info saved successfully !");
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
        }
    }

    public List<Employee> employeeList(){
        List<Employee> employeeList = employeeRepo.findAll();
        return employeeList;
    }

    public Optional<Employee> employeeById(Long id){
        Optional<Employee> employee = employeeRepo.findById(id);
        return employee;
    }

    @Override
    public ResponseEntity<ResponseMessage> updateEmployeeById(Employee employee, Long id){
        Integer employeeExistence = employeeRepo.countByContactNoAndIdNot(employee.getContactNo(),id);
        if(employeeExistence > 0){
            responseMessage = new ResponseMessage("Sorry! Employee info already exists.");
            return new ResponseEntity<ResponseMessage>(responseMessage,HttpStatus.BAD_REQUEST);
        } else {
            Optional<Employee> existingEmployeeOptional = employeeRepo.findById(id);
            if (existingEmployeeOptional.isPresent()) {
                Employee existingEmployee = existingEmployeeOptional.get();

                existingEmployee.setEmployeeName(employee.getEmployeeName());
                existingEmployee.setContactNo(employee.getContactNo());
                existingEmployee.setDesignation(employee.getDesignation());
                existingEmployee.setPaymentType(employee.getPaymentType());
                existingEmployee.setJoiningDate(employee.getJoiningDate());
                existingEmployee.setUpdatedAt(LocalDate.now());

                employeeRepo.save(existingEmployee);

                ResponseMessage responseMessage = new ResponseMessage("Employee info saved successfully !");
                return new ResponseEntity<>(responseMessage, HttpStatus.OK);
            } else {
                ResponseMessage responseMessage = new ResponseMessage("Sorry! Invalid ID Number");
                return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
            }
        }
    }

    public ResponseEntity<ResponseMessage> deleteEmployee(Long id){
        if(employeeRepo.countById(id) > 0){
            employeeRepo.deleteById(id);
            responseMessage = new ResponseMessage("Employee info deleted successfully !");
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
        } else {
            responseMessage = new ResponseMessage("Sorry ! Invalid ID");
            return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        }

    }
}
