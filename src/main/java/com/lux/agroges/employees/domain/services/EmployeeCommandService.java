package com.lux.agroges.employees.domain.services;

import com.lux.agroges.employees.domain.model.commands.CreateEmployeeCommand;

public interface EmployeeCommandService {
    Long handle(CreateEmployeeCommand command);
}
