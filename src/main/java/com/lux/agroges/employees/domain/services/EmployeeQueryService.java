package com.lux.agroges.employees.domain.services;

import com.lux.agroges.employees.domain.model.aggregates.Employee;
import com.lux.agroges.employees.domain.model.queries.GetAllEmployeesQuery;
import com.lux.agroges.employees.domain.model.queries.GetEmployeeByIdDocumentQuery;
import com.lux.agroges.employees.domain.model.queries.GetEmployeeByIdQuery;
import com.lux.agroges.employees.domain.model.queries.GetEmployeeByNameQuery;

import java.util.List;
import java.util.Optional;

public interface EmployeeQueryService {
    List<Employee> handle(GetAllEmployeesQuery query);
    Optional<Employee> handle(GetEmployeeByIdDocumentQuery query);
    Optional<Employee> handle(GetEmployeeByNameQuery query);
    Optional<Employee> handle(GetEmployeeByIdQuery query);
}
