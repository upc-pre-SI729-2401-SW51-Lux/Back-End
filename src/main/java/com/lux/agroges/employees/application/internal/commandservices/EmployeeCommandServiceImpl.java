package com.lux.agroges.employees.application.internal.commandservices;

import com.lux.agroges.employees.domain.model.aggregates.Employee;
import com.lux.agroges.employees.domain.model.commands.CreateEmployeeCommand;
import com.lux.agroges.employees.domain.model.valuebojects.EmailAddress;
import com.lux.agroges.employees.domain.services.EmployeeCommandService;
import com.lux.agroges.employees.infrastructure.persistence.jpa.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeCommandServiceImpl implements EmployeeCommandService {
    private final EmployeeRepository employeeRepository;

    public EmployeeCommandServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Long handle(CreateEmployeeCommand command) {
        var emailAddress = new EmailAddress(command.email());
        employeeRepository.findByEmail(emailAddress).map(employee -> {
            throw new IllegalArgumentException("Employee with email " + emailAddress + " already exists.");
        });
        var employee = new Employee(command.firstName(), command.lastName(), command.email(), command.workPosition(), command.salary(), command.phone(), command.age(), command.state());
        employeeRepository.save(employee);
        return employee.getId();
    }
}

