package com.lux.agroges.employees.domain.model.commands;

public record CreateEmployeeCommand(String id, String name, String lastName, String email, String workPosition, Integer salary, String phone, Integer age, String state) {
}
