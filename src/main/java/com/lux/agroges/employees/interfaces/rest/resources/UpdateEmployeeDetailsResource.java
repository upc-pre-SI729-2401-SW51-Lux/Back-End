package com.lux.agroges.employees.interfaces.rest.resources;

public record UpdateEmployeeDetailsResource(
        String workPosition,
        Integer salary,
        String phone,
        Integer age,
        String photoUrl) {}
