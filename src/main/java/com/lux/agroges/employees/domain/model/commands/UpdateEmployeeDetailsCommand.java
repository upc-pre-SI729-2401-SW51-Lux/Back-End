package com.lux.agroges.employees.domain.model.commands;

public record UpdateEmployeeDetailsCommand(String idDocument, String workPosition, Integer salary, String phone, Integer age, String photoUrl) {
}
