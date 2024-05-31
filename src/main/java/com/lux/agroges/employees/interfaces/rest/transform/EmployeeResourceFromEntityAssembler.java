package com.lux.agroges.employees.interfaces.rest.transform;

import com.lux.agroges.employees.domain.model.aggregates.Employee;
import com.lux.agroges.employees.interfaces.rest.resources.EmployeeResource;

public class EmployeeResourceFromEntityAssembler {
    public static EmployeeResource toResourceFromEntity(Employee employee) {
        return new EmployeeResource(
                employee.getId(),
                employee.getFullName(),
                employee.getEmailAddress(),
                employee.getEmployeeDetails());
    }
}
