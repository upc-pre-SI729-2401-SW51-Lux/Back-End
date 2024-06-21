package com.lux.agroges.employees.interfaces.acl;

import com.lux.agroges.employees.domain.model.commands.CreateEmployeeCommand;
import com.lux.agroges.employees.domain.model.queries.GetEmployeeByNameQuery;
import com.lux.agroges.employees.domain.services.EmployeeCommandService;
import com.lux.agroges.employees.domain.services.EmployeeQueryService;

public class EmployeesContextFacade {
    private final EmployeeCommandService employeeCommandService;
    private final EmployeeQueryService employeeQueryService;

    public EmployeesContextFacade(EmployeeCommandService employeeCommandService, EmployeeQueryService employeeQueryService) {
        this.employeeCommandService = employeeCommandService;
        this.employeeQueryService = employeeQueryService;
    }

    public String createEmployee(String idDocument, String firstName, String lastName, String email, String workPosition, Integer salary, String phone, Integer age, String photoUrl) {
        var createEmployeeCommand = new CreateEmployeeCommand(idDocument, firstName, lastName, email, workPosition, salary, phone, age, photoUrl);
        var employee = employeeCommandService.handle(createEmployeeCommand);
        if (employee.isEmpty()) return null;
        return employee.get().getIdDocument();
    }

    public String fetchEmployeeIdDocumentByName(String name) {
        var getEmployeeByNameQuery = new GetEmployeeByNameQuery(name);
        var employee = employeeQueryService.handle(getEmployeeByNameQuery);
        if (employee.isEmpty()) return null;
        return employee.get().getIdDocument();
    }
}
