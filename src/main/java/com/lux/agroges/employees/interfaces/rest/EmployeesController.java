package com.lux.agroges.employees.interfaces.rest;

import com.lux.agroges.employees.domain.model.queries.GetAllEmployeesQuery;
import com.lux.agroges.employees.domain.model.queries.GetEmployeeByIdDocumentQuery;
import com.lux.agroges.employees.domain.services.EmployeeCommandService;
import com.lux.agroges.employees.domain.services.EmployeeQueryService;
import com.lux.agroges.employees.interfaces.rest.resources.CreateEmployeeResource;
import com.lux.agroges.employees.interfaces.rest.resources.EmployeeResource;
import com.lux.agroges.employees.interfaces.rest.transform.CreateEmployeeCommandFromResourceAssembler;
import com.lux.agroges.employees.interfaces.rest.transform.EmployeeResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class EmployeesController {
    private final EmployeeCommandService employeeCommandService;
    private final EmployeeQueryService employeeQueryService;

    public EmployeesController(EmployeeCommandService employeeCommandService, EmployeeQueryService employeeQueryService) {
        this.employeeCommandService = employeeCommandService;
        this.employeeQueryService = employeeQueryService;
    }

    @PostMapping
    public ResponseEntity<EmployeeResource> createEmployee(@RequestBody CreateEmployeeResource createEmployeeResource) {
        var createEmployeeCommand = CreateEmployeeCommandFromResourceAssembler.toCommandFromResource(createEmployeeResource);
        var employee = employeeCommandService.handle(createEmployeeCommand);
        if (employee.isEmpty()) { return ResponseEntity.badRequest().build(); }
        var employeeResource = EmployeeResourceFromEntityAssembler.toResourceFromEntity(employee.get());
        return new ResponseEntity<>(employeeResource, HttpStatus.CREATED);
    }

    @GetMapping("/{idDocument}")
    public ResponseEntity<EmployeeResource> getEmployeeByIdDocument(@PathVariable String idDocument) {
        var getEmployeeByIdDocumentQuery = new GetEmployeeByIdDocumentQuery(idDocument);
        var employee = employeeQueryService.handle(getEmployeeByIdDocumentQuery);
        if (employee.isEmpty()) { return ResponseEntity.notFound().build(); }
        var employeeResource = EmployeeResourceFromEntityAssembler.toResourceFromEntity(employee.get());
        return ResponseEntity.ok(employeeResource);
    }

    public ResponseEntity<List<EmployeeResource>> getAllEmployees() {
        var getAllEmployeesQuery = new GetAllEmployeesQuery();
        var employees = employeeQueryService.handle(getAllEmployeesQuery);
        var employeeResources = employees.stream()
                .map(EmployeeResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(employeeResources);
    }
}
