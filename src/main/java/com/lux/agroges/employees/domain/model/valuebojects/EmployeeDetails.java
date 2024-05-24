package com.lux.agroges.employees.domain.model.valuebojects;

public record EmployeeDetails(String workPosition, Number salary, String phone, Number age, String state) {
    public EmployeeDetails() {
        this(null, null, null, null, null);
    }

    public EmployeeDetails {
        if (workPosition == null || workPosition.isBlank())
            throw new IllegalArgumentException("Work position cannot be null or empty.");
        if (salary == null)
            throw new IllegalArgumentException("Salary cannot be null or empty.");
        if (phone == null || phone.isBlank())
            throw new IllegalArgumentException("Phone number cannot be null or empty.");
        if (age == null || age.intValue() <= 0)
            throw new IllegalArgumentException("Age cannot be null or negative.");
        if (state == null || state.isBlank())
            throw new IllegalArgumentException("State cannot be null or empty.");
    }

    public String getEmployeeDetails() {
        return String.format("%s %s %s %s %s", workPosition, salary, phone, age, state);
    }
}
