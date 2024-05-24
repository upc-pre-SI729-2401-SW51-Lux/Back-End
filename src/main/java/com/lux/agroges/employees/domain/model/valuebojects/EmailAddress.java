package com.lux.agroges.employees.domain.model.valuebojects;

import jakarta.validation.constraints.Email;

public record EmailAddress(@Email String address) {
    public EmailAddress() {
        this(null);
    }
}
