package com.lux.agroges.employees.domain.model.commands;

public record CreateEmployeeCommand(String idDocument, String firstName, String lastName, String email, String workPosition, Integer salary, String phone, Integer age, String photoUrl) {
}
