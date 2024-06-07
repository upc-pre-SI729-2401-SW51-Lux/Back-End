package com.lux.agroges.employees.interfaces.rest;

import com.lux.agroges.employees.domain.model.commands.DeleteEmployeeCommand;
import com.lux.agroges.employees.domain.model.queries.GetAllEmployeesQuery;
import com.lux.agroges.employees.domain.model.queries.GetEmployeeByIdDocumentQuery;
import com.lux.agroges.employees.domain.services.EmployeeCommandService;
import com.lux.agroges.employees.domain.services.EmployeeQueryService;
import com.lux.agroges.employees.interfaces.rest.resources.CreateEmployeeResource;
import com.lux.agroges.employees.interfaces.rest.resources.EmployeeResource;
import com.lux.agroges.employees.interfaces.rest.resources.UpdateEmployeeDetailsResource;
import com.lux.agroges.employees.interfaces.rest.transform.CreateEmployeeCommandFromResourceAssembler;
import com.lux.agroges.employees.interfaces.rest.transform.EmployeeResourceFromEntityAssembler;
import com.lux.agroges.employees.interfaces.rest.transform.UpdateEmployeeDetailsCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/employees", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Employees", description = "Employees Management Endpoints")
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

    @GetMapping
    public ResponseEntity<List<EmployeeResource>> getAllEmployees() {
        var getAllEmployeesQuery = new GetAllEmployeesQuery();
        var employees = employeeQueryService.handle(getAllEmployeesQuery);
        var employeeResources = employees.stream()
                .map(EmployeeResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(employeeResources);
    }

    @PutMapping("/{idDocument}")
    public ResponseEntity<EmployeeResource> updateEmployeeDetails(@PathVariable String idDocument, @RequestBody UpdateEmployeeDetailsResource updateEmployeeDetailsResource) {
        var updateEmployeeDetailsCommand = UpdateEmployeeDetailsCommandFromResourceAssembler.toCommandFromResource(idDocument, updateEmployeeDetailsResource);
        var updatedEmployeeDetails = employeeCommandService.handle(updateEmployeeDetailsCommand);
        if (updatedEmployeeDetails.isEmpty()) { return ResponseEntity.badRequest().build(); }
        var employeeResource = EmployeeResourceFromEntityAssembler.toResourceFromEntity(updatedEmployeeDetails.get());
        return ResponseEntity.ok(employeeResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        var deleteEmployeeCommand = new DeleteEmployeeCommand(id);
        employeeCommandService.handle(deleteEmployeeCommand);
        return ResponseEntity.ok("Employee deleted successfully.");
    }
}
