package com.lux.agroges.employees.domain.model.commands;

public record CreateEmployeeCommand(String id, String name, String lastName, String workPosition, Number salary, String phone, Number age, String state, String email) {
}
