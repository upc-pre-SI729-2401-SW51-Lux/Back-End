package com.lux.agroges.employees.domain.services;

import com.lux.agroges.employees.domain.model.aggregates.Employee;
import com.lux.agroges.employees.domain.model.commands.CreateEmployeeCommand;
import com.lux.agroges.employees.domain.model.commands.DeleteEmployeeCommand;
import com.lux.agroges.employees.domain.model.commands.UpdateEmployeeDetailsCommand;

import java.util.Optional;

public interface EmployeeCommandService {
    Optional<Employee> handle(CreateEmployeeCommand command);
    void handle(DeleteEmployeeCommand command);
    Optional<Employee> handle(UpdateEmployeeDetailsCommand command);
}
