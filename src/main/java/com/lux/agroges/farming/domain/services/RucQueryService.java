package com.lux.agroges.farming.domain.services;

import com.lux.agroges.farming.domain.model.aggregates.Ruc;
import com.lux.agroges.farming.domain.model.queries.GetRucByIdQuery;

import java.util.List;
import java.util.Optional;

public interface RucQueryService {

    List<Ruc> getAll();
    Optional<Ruc> getRucById(GetRucByIdQuery query);
}
