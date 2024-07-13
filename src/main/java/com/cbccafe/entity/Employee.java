package com.cbccafe.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Employee name is required")
    @Column(name = "employee_name")
    private String employeeName;

    @NotBlank(message = "Contact no is required")
    @Column(name = "contact_no")
    private String contactNo;

    @NotBlank(message = "Designation is required")
    @Column(name = "designation")
    private String designation;

    @NotBlank(message = "Payment type is required")
    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "joining_date")
    @JsonFormat(pattern = "yyyy-MM-dd" , timezone = "UTC")
    private LocalDate joiningDate;

    @Column(name = "entered_at")
    @JsonFormat(pattern = "yyyy-MM-dd" , timezone = "UTC")
    private LocalDate enteredAt;

    @Column(name = "entered_by")
    private String enteredBy;

    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd" , timezone = "UTC")
    private LocalDate updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @OneToOne(mappedBy = "employee")
    private User user;

}
