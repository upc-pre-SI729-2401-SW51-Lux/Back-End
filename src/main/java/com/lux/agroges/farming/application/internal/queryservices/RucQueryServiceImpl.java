package com.lux.agroges.farming.application.internal.queryservices;

import com.lux.agroges.farming.domain.model.aggregates.Ruc;
import com.lux.agroges.farming.domain.model.queries.GetRucByIdQuery;
import com.lux.agroges.farming.domain.services.RucQueryService;
import com.lux.agroges.farming.infrastructure.persistence.jpa.RucRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RucQueryServiceImpl implements RucQueryService {

    private final RucRepository rucRepository;

    public RucQueryServiceImpl(RucRepository rucRepository) {
        this.rucRepository = rucRepository;
    }

    @Override
    public List<Ruc> getAll() {
        return rucRepository.getAll();
    }

    @Override
    public Optional<Ruc> getRucById(GetRucByIdQuery query) {
        return rucRepository.findRucById(query.id());
    }
}
