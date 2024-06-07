package com.lux.agroges.farming.application.internal.commandservices;

import com.lux.agroges.farming.domain.model.aggregates.Ruc;
import com.lux.agroges.farming.domain.model.commands.CreateRucCommand;
import com.lux.agroges.farming.domain.services.RucCommandService;
import com.lux.agroges.farming.infrastructure.persistence.jpa.RucRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class RucCommandServiceImpl implements RucCommandService {

    private final RucRepository rucRepository;

    public RucCommandServiceImpl(RucRepository rucRepository) {
        this.rucRepository = rucRepository;
    }
    @Override
    public Optional<Ruc> handle(CreateRucCommand command) {
        Ruc ruc = new Ruc(command);
        rucRepository.save(ruc);
        return Optional.of(ruc);
    }
}
