package com.lux.agroges.employees.domain.model.valueobjects;

public record EmployeeName(String firstName, String lastName) {
    public EmployeeName() {
        this(null, null);
    }

    private static String extractFirstName(String name) {
        String[] parts = name.trim().split("\\s+");
        return parts[0];
    }

    private static String extractLastName(String name) {
        String[] parts = name.trim().split("\\s+");
        return parts[1];
    }

    public EmployeeName(String name) {
        this(extractFirstName(name), extractLastName(name));
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
