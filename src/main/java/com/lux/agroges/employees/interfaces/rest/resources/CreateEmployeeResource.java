package com.lux.agroges.employees.interfaces.rest.resources;

public record CreateEmployeeResource(
        String firstName,
        String lastName,
        String email,
        String idDocument,
        String workPosition,
        Integer salary,
        String phone,
        Integer age,
        String photoUrl
) {}
