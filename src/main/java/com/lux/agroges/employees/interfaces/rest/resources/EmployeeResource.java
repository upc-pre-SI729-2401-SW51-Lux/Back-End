package com.lux.agroges.employees.interfaces.rest.resources;

public record EmployeeResource(
        Long id,
        String name,
        String email,
        String employeeDetails) {}
