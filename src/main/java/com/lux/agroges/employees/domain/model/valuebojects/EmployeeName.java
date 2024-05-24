package com.lux.agroges.employees.domain.model.valuebojects;

public record EmployeeName(String firstName, String lastName) {
    public EmployeeName() {
        this(null, null);
    }

    public EmployeeName {
        if (firstName == null || firstName.isBlank())
            throw new IllegalArgumentException("First name cannot be null or empty.");
        if (lastName == null || lastName.isBlank())
            throw new IllegalArgumentException("Last name cannot be null or empty.");
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}
