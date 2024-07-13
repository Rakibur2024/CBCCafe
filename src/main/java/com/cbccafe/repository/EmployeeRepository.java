package com.cbccafe.repository;

import com.cbccafe.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Integer countByContactNo(String contactNo);

    Integer countById(Long id);
    Integer countByContactNoAndIdNot(String contactNo, Long id);
}
