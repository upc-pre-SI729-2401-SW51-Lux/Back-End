package com.lux.agroges.employees.domain.model.valueobjects;

public record EmployeeId(Long employeeId) {
    public EmployeeId {
        if (employeeId == null) {
            throw new IllegalArgumentException("EmployeeId cannot be null");
        }
    }
}
