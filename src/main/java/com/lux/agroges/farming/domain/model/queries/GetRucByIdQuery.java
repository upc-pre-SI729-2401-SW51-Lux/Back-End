package com.lux.agroges.farming.domain.model.queries;

public record GetRucByIdQuery(Long id) {

    public GetRucByIdQuery {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }
}
