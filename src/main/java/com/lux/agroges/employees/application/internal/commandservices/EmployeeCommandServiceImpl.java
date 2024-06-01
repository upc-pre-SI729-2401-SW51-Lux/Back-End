package com.lux.agroges.employees.application.internal.commandservices;

import com.lux.agroges.employees.domain.model.aggregates.Employee;
import com.lux.agroges.employees.domain.model.commands.CreateEmployeeCommand;
import com.lux.agroges.employees.domain.model.commands.DeleteEmployeeCommand;
import com.lux.agroges.employees.domain.model.commands.UpdateEmployeeDetailsCommand;
import com.lux.agroges.employees.domain.model.valueobjects.EmployeeDetails;
import com.lux.agroges.employees.domain.services.EmployeeCommandService;
import com.lux.agroges.employees.infrastructure.persistence.jpa.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeCommandServiceImpl implements EmployeeCommandService {
    private final EmployeeRepository employeeRepository;

    public EmployeeCommandServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<Employee> handle(CreateEmployeeCommand command) {
        var id = new EmployeeDetails(command.idDocument(), command.workPosition(), command.salary(), command.phone(), command.age(), command.photoUrl());
        employeeRepository.findByIdDocument(id.getEmployeeId()).map(employee -> {
            throw new IllegalArgumentException("Employee with id " + id + " already exists.");
        });
        var employee = new Employee(command.firstName(), command.lastName(), command.email(), command.idDocument(), command.workPosition(), command.salary(), command.phone(), command.age(), command.photoUrl());
        employeeRepository.save(employee);
        return Optional.of(employee);
    }

    @Override
    public void handle(DeleteEmployeeCommand command) {
        if (!employeeRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Employee does not exists.");
        }
        try {
            employeeRepository.deleteById(command.id());
        } catch (Exception e){
            throw new IllegalArgumentException("Error while deleting course: " + e.getMessage());
        }
    }

    @Override
    public Optional<Employee> handle(UpdateEmployeeDetailsCommand command) {
        var result = employeeRepository.findByIdDocument(command.idDocument());
        if (result.isEmpty()) throw new IllegalArgumentException("Employee does not exists.");
        var employeeDetailsToUpdate = result.get();
        try {
            var updatedEmployeeDetails = employeeRepository.save(employeeDetailsToUpdate.updateEmployeeDetails(command.workPosition(), command.salary(), command.phone(), command.age(), command.photoUrl()));
            return Optional.of(updatedEmployeeDetails);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating employee details " + e.getMessage());
        }
    }
}

