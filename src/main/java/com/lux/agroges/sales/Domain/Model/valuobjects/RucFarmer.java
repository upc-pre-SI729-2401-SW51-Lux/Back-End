package com.lux.agroges.sales.Domain.Model.valuobjects;
import jakarta.persistence.Embeddable;

@Embeddable
public record RucFarmer(Long ruc) {
    public RucFarmer {
        if (ruc == null) {
            throw new IllegalArgumentException("Ruc cannot be null");
        }
    }

}
