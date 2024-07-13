package com.cbccafe.projection;

public interface UserProjection {
    Long getId();
    String getUserName();
    EmployeeProjection getEmployee();
}
