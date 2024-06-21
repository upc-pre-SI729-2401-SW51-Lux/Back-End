package com.lux.agroges.adviser.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Value;

@Value
@Embeddable
public class CompanyRuc {
    String ruc;

    protected CompanyRuc() {
        this.ruc = null;
    }

    public CompanyRuc(String ruc) {
        if (ruc == null) {
            throw new IllegalArgumentException("RUC cannot be null");
        }
        this.ruc = ruc;
    }
}
