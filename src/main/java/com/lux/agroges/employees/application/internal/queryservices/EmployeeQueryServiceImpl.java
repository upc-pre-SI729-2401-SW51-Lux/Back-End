package com.lux.agroges.employees.application.internal.queryservices;

import com.lux.agroges.employees.domain.model.aggregates.Employee;
import com.lux.agroges.employees.domain.model.queries.GetAllEmployeesQuery;
import com.lux.agroges.employees.domain.model.queries.GetEmployeeByIdQuery;
import com.lux.agroges.employees.domain.model.queries.GetEmployeeByNameQuery;
import com.lux.agroges.employees.domain.services.EmployeeQueryService;
import com.lux.agroges.employees.infrastructure.persistence.jpa.repositories.EmployeeRepository;

import java.util.List;
import java.util.Optional;

public class EmployeeQueryServiceImpl implements EmployeeQueryService {
    @Override
    public List<Employee> handle(GetAllEmployeesQuery query) {
        return null;
    }

    @Override
    public Optional<Employee> handle(GetEmployeeByIdQuery query) {
        return Optional.empty();
    }

    @Override
    public Optional<Employee> handle(GetEmployeeByNameQuery query) {
        return Optional.empty();
    }
}
