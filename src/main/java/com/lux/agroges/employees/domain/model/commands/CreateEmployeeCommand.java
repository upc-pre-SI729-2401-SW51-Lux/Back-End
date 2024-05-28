package com.lux.agroges.employees.domain.model.commands;

public record CreateEmployeeCommand(String id, String firstName, String lastName, String email, String workPosition, Integer salary, String phone, Integer age, String state) {
}
