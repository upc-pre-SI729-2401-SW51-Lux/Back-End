package com.lux.agroges.employees.domain.model.valueobjects;

public record EmployeeDetails(String idDocument, String workPosition, Integer salary, String phone, Integer age, String state) {
    public EmployeeDetails() {
        this(null,null, null, null, null, null);
    }

    public EmployeeDetails {
        if (idDocument == null || idDocument.isBlank())
            throw new IllegalArgumentException("Identification document cannot be null or empty.");
        if (workPosition == null || workPosition.isBlank())
            throw new IllegalArgumentException("Work position cannot be null or empty.");
        if (salary == null || salary.toString().isBlank())
            throw new IllegalArgumentException("Salary cannot be null or empty.");
        if (phone == null || phone.isBlank())
            throw new IllegalArgumentException("Phone number cannot be null or empty.");
        if (age == null || age <= 0 || age.toString().isBlank())
            throw new IllegalArgumentException("Age cannot be null or negative.");
        if (state == null || state.isBlank())
            throw new IllegalArgumentException("State cannot be null or empty.");
    }

    public String getEmployeeId(){
        return String.format("%s", idDocument);
    }

    public String getEmployeeDetails() {
        return String.format("%s %s %s %s %s %s", idDocument, workPosition, salary, phone, age, state);
    }
}
