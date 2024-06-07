package com.lux.agroges.employees.application.internal.queryservices;

import com.lux.agroges.employees.domain.model.aggregates.Employee;
import com.lux.agroges.employees.domain.model.queries.GetAllEmployeesQuery;
import com.lux.agroges.employees.domain.model.queries.GetEmployeeByIdDocumentQuery;
import com.lux.agroges.employees.domain.model.queries.GetEmployeeByIdQuery;
import com.lux.agroges.employees.domain.model.queries.GetEmployeeByNameQuery;
import com.lux.agroges.employees.domain.model.valueobjects.EmployeeName;
import com.lux.agroges.employees.domain.services.EmployeeQueryService;
import com.lux.agroges.employees.infrastructure.persistence.jpa.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeQueryServiceImpl implements EmployeeQueryService {
    private final EmployeeRepository employeeRepository;

    public EmployeeQueryServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> handle(GetAllEmployeesQuery query) {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> handle(GetEmployeeByIdDocumentQuery query) {
        return employeeRepository.findByIdDocument(query.idDocument());
    }

    @Override
    public Optional<Employee> handle(GetEmployeeByNameQuery query) {
        return employeeRepository.findByName(new EmployeeName(query.name()));
    }

    @Override
    public Optional<Employee> handle(GetEmployeeByIdQuery query) {
        return employeeRepository.findById(query.id());
    }
}
