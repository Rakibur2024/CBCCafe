package com.cbccafe.projection;

import java.time.LocalDate;

public interface EmployeeProjection {
    Long getId();
    String getEmployeeName();
    String getContactNo();
    String getDesignation();
    String getPaymentType();
    LocalDate getJoiningDate();
    LocalDate getEnteredAt();
    String getEnteredBy();
    LocalDate getUpdatedAt();
    String getUpdatedBy();
}
